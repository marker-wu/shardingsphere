/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.shadow.api.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.rule.function.DistributedRuleConfiguration;
import org.apache.shardingsphere.infra.config.rule.scope.DatabaseRuleConfiguration;
import org.apache.shardingsphere.shadow.api.config.datasource.ShadowDataSourceConfiguration;
import org.apache.shardingsphere.shadow.api.config.table.ShadowTableConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shadow rule configuration.
 */
@Getter
@Setter
public final class ShadowRuleConfiguration implements DatabaseRuleConfiguration, DistributedRuleConfiguration {
    
    private String defaultShadowAlgorithmName;
    
    private Map<String, ShadowDataSourceConfiguration> dataSources = new LinkedHashMap<>();
    
    private Map<String, ShadowTableConfiguration> tables = new LinkedHashMap<>();
    
    private Map<String, ShardingSphereAlgorithmConfiguration> shadowAlgorithms = new LinkedHashMap<>();
}
