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

import com.google.gson.GsonBuilder;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest {
    public GoPluginApiResponse execute(GoPluginApiRequest request) {
        HashMap<String, Object> validationResult = new HashMap<>();
        int responseCode = DefaultGoPluginApiResponse.SUCCESS_RESPONSE_CODE;
        Map configMap = (Map) new GsonBuilder().create().fromJson(request.requestBody(), Object.class);
        HashMap errorMap = new HashMap();
        validateRequiredFor(configMap, errorMap, contentstack.gocd.plugins.s3.TaskPlugin.REGION);
        validateRequiredFor(configMap, errorMap, contentstack.gocd.plugins.s3.TaskPlugin.BUCKET);
        validateRequiredFor(configMap, errorMap, contentstack.gocd.plugins.s3.TaskPlugin.SOURCE_FILE_PATH);
        validateRequiredFor(configMap, errorMap, contentstack.gocd.plugins.s3.TaskPlugin.DESTINATION_PATH);
        validationResult.put("errors", errorMap);
        return new DefaultGoPluginApiResponse(responseCode, TaskPlugin.GSON.toJson(validationResult));
    }

    private void validateRequiredFor(Map configMap, HashMap errorMap, String key) {
        if (!configMap.containsKey(key) || ((Map) configMap.get(key)).get("value") == null || ((String) ((Map) configMap.get(key)).get("value")).trim().isEmpty()) {
            errorMap.put(key, key + " cannot be empty");
        }
    }
}
