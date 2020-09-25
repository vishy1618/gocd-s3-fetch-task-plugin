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


import java.util.Map;

// TODO: edit this to map to the fields in your task configuration
public class TaskConfig {
    private final String region;
    private final String bucket;
    private final String sourceFilePath;
    private final String destinationPath;

    public TaskConfig(Map config) {
        region = getValue(config, TaskPlugin.REGION);
        bucket = getValue(config, TaskPlugin.BUCKET);
        sourceFilePath = getValue(config, TaskPlugin.SOURCE_FILE_PATH);
        destinationPath = getValue(config, TaskPlugin.DESTINATION_PATH);
    }

    private String getValue(Map config, String property) {
        return (String) ((Map) config.get(property)).get("value");
    }

    public String getRegion() {
        return region;
    }

    public String getBucket() {
        return bucket;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }
}
