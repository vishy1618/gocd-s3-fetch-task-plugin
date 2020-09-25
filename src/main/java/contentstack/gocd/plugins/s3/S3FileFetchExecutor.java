/*
 * Copyright 2020 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package contentstack.gocd.plugins.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.thoughtworks.go.plugin.api.task.JobConsoleLogger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class S3FileFetchExecutor {
    public Result execute(TaskConfig taskConfig, Context context, JobConsoleLogger console) {
        console.printLine("Executing task...");
        try {
            return runCommand(context, taskConfig, console);
        } catch (Exception e) {
            return new Result(false, "Failed to download file from URL: " + taskConfig.getSourceFilePath(), e);
        }
    }

    private Result runCommand(Context context, TaskConfig taskConfig, JobConsoleLogger console) throws IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(taskConfig.getRegion())
                .build();

        console.printLine("Built s3 client...");
        S3Object s3object = s3Client.getObject(taskConfig.getBucket(), taskConfig.getSourceFilePath());
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        console.printLine("Downloading file...");
        FileUtils.copyInputStreamToFile(inputStream, new File(context.getWorkingDir() + "/" + taskConfig.getDestinationPath()));
        console.printLine("File downloaded to path: " + context.getWorkingDir() + "/" + taskConfig.getDestinationPath());
        return new Result(true, "File downloaded to path: " + context.getWorkingDir() + "/" + taskConfig.getDestinationPath());
    }
}
