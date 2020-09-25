/*
 * Copyright 2017 ThoughtWorks, Inc.
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

import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.HashMap;

// TODO: change this to allow configuration options in your configuration
public class GetConfigRequest {

    public GoPluginApiResponse execute() {
        HashMap<String, Object> config = new HashMap<>();

        HashMap<String, Object> region = new HashMap<>();
        region.put("display-order", "0");
        region.put("display-name", "Region");
        region.put("required", true);
        config.put(TaskPlugin.REGION, region);

        HashMap<String, Object> bucket = new HashMap<>();
        bucket.put("display-order", "1");
        bucket.put("display-name", "Bucket");
        bucket.put("required", true);
        config.put(TaskPlugin.BUCKET, bucket);

        HashMap<String, Object> sourceFilePath = new HashMap<>();
        sourceFilePath.put("display-order", "2");
        sourceFilePath.put("display-name", "SourceFilePath");
        sourceFilePath.put("required", true);
        config.put(TaskPlugin.SOURCE_FILE_PATH, sourceFilePath);

        HashMap<String, Object> destinationPath = new HashMap<>();
        destinationPath.put("display-order", "3");
        destinationPath.put("display-name", "DestinationPath");
        destinationPath.put("required", true);
        config.put(TaskPlugin.DESTINATION_PATH, destinationPath);

        return DefaultGoPluginApiResponse.success(TaskPlugin.GSON.toJson(config));
    }
}
