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

package org.apache.shardingsphere.infra.config.props;

import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public final class ConfigurationPropertiesTest {
    
    @Test
    public void assertGetValue() {
        ConfigurationProperties actual = new ConfigurationProperties(createProperties());
        assertTrue(actual.getValue(ConfigurationPropertyKey.SQL_SHOW));
        assertTrue(actual.getValue(ConfigurationPropertyKey.SQL_SIMPLE));
        assertThat(actual.getValue(ConfigurationPropertyKey.KERNEL_EXECUTOR_SIZE), is(20));
        assertThat(actual.getValue(ConfigurationPropertyKey.MAX_CONNECTIONS_SIZE_PER_QUERY), is(20));
        assertTrue(actual.getValue(ConfigurationPropertyKey.CHECK_TABLE_METADATA_ENABLED));
        assertTrue(actual.getValue(ConfigurationPropertyKey.SQL_FEDERATION_ENABLED));
        assertThat(actual.getValue(ConfigurationPropertyKey.PROXY_FRONTEND_DATABASE_PROTOCOL_TYPE), is("PostgreSQL"));
        assertThat(actual.getValue(ConfigurationPropertyKey.PROXY_FRONTEND_FLUSH_THRESHOLD), is(20));
        assertTrue(actual.getValue(ConfigurationPropertyKey.PROXY_HINT_ENABLED));
    }
    
    private Properties createProperties() {
        Properties result = new Properties();
        result.setProperty(ConfigurationPropertyKey.SQL_SHOW.getKey(), Boolean.TRUE.toString());
        result.setProperty(ConfigurationPropertyKey.SQL_SIMPLE.getKey(), Boolean.TRUE.toString());
        result.setProperty(ConfigurationPropertyKey.KERNEL_EXECUTOR_SIZE.getKey(), "20");
        result.setProperty(ConfigurationPropertyKey.MAX_CONNECTIONS_SIZE_PER_QUERY.getKey(), "20");
        result.setProperty(ConfigurationPropertyKey.CHECK_TABLE_METADATA_ENABLED.getKey(), Boolean.TRUE.toString());
        result.setProperty(ConfigurationPropertyKey.SQL_FEDERATION_ENABLED.getKey(), Boolean.TRUE.toString());
        result.setProperty(ConfigurationPropertyKey.PROXY_FRONTEND_DATABASE_PROTOCOL_TYPE.getKey(), "PostgreSQL");
        result.setProperty(ConfigurationPropertyKey.PROXY_FRONTEND_FLUSH_THRESHOLD.getKey(), "20");
        result.setProperty(ConfigurationPropertyKey.PROXY_HINT_ENABLED.getKey(), Boolean.TRUE.toString());
        return result;
    }
    
    @Test
    public void assertGetDefaultValue() {
        ConfigurationProperties actual = new ConfigurationProperties(new Properties());
        assertFalse(actual.getValue(ConfigurationPropertyKey.SQL_SHOW));
        assertFalse(actual.getValue(ConfigurationPropertyKey.SQL_SIMPLE));
        assertThat(actual.getValue(ConfigurationPropertyKey.KERNEL_EXECUTOR_SIZE), is(0));
        assertThat(actual.getValue(ConfigurationPropertyKey.MAX_CONNECTIONS_SIZE_PER_QUERY), is(1));
        assertFalse(actual.getValue(ConfigurationPropertyKey.CHECK_TABLE_METADATA_ENABLED));
        assertFalse(actual.getValue(ConfigurationPropertyKey.SQL_FEDERATION_ENABLED));
        assertThat(actual.getValue(ConfigurationPropertyKey.PROXY_FRONTEND_DATABASE_PROTOCOL_TYPE), is(""));
        assertThat(actual.getValue(ConfigurationPropertyKey.PROXY_FRONTEND_FLUSH_THRESHOLD), is(128));
        assertFalse(actual.getValue(ConfigurationPropertyKey.PROXY_HINT_ENABLED));
    }
}
