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

package org.apache.shardingsphere.infra.util.yaml;

import org.apache.shardingsphere.infra.util.yaml.fixture.YamlShortcutsFixture;
import org.junit.Test;
import org.yaml.snakeyaml.constructor.ConstructorException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public final class YamlEngineTest {
    
    @Test
    public void assertUnmarshalWithFile() throws IOException {
        URL url = getClass().getClassLoader().getResource("yaml/fixture-rule.yaml");
        assertNotNull(url);
        YamlShortcutsFixture actual = YamlEngine.unmarshal(new File(url.getFile()), YamlShortcutsFixture.class);
        assertThat(actual.getName(), is("test"));
    }
    
    @Test
    public void assertUnmarshalWithYamlBytes() throws IOException {
        URL url = getClass().getClassLoader().getResource("yaml/fixture-rule.yaml");
        assertNotNull(url);
        StringBuilder yamlContent = new StringBuilder();
        try (
                FileReader fileReader = new FileReader(url.getFile());
                BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while (null != (line = reader.readLine())) {
                yamlContent.append(line).append(System.lineSeparator());
            }
        }
        YamlShortcutsFixture actual = YamlEngine.unmarshal(yamlContent.toString().getBytes(), YamlShortcutsFixture.class);
        assertThat(actual.getName(), is("test"));
    }
    
    @Test
    public void assertUnmarshalWithYamlContentClassType() {
        YamlShortcutsFixture actual = YamlEngine.unmarshal("name: test", YamlShortcutsFixture.class);
        assertThat(actual.getName(), is("test"));
    }
    
    @Test
    public void assertUnmarshalWithYamlContentClassTypeSkipMissingProperties() {
        YamlShortcutsFixture actual = YamlEngine.unmarshal("name: test\nnotExistsField: test", YamlShortcutsFixture.class, true);
        assertThat(actual.getName(), is("test"));
    }
    
    @Test
    public void assertUnmarshalProperties() {
        Properties actual = YamlEngine.unmarshal("password: pwd", Properties.class);
        assertThat(actual.getProperty("password"), is("pwd"));
    }
    
    @Test
    public void assertMarshal() {
        YamlShortcutsFixture actual = new YamlShortcutsFixture();
        actual.setName("test");
        assertThat(YamlEngine.marshal(actual), is("name: test" + System.lineSeparator()));
    }
    
    @Test(expected = ConstructorException.class)
    public void assertUnmarshalInvalidYaml() throws IOException {
        URL url = getClass().getClassLoader().getResource("yaml/accepted-class.yaml");
        assertNotNull(url);
        StringBuilder yamlContent = new StringBuilder();
        try (
                FileReader fileReader = new FileReader(url.getFile());
                BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while (null != (line = reader.readLine())) {
                yamlContent.append(line).append(System.lineSeparator());
            }
        }
        YamlEngine.unmarshal(yamlContent.toString(), Object.class);
    }
    
    @Test
    public void assertMarshalCollection() {
        YamlShortcutsFixture actual = new YamlShortcutsFixture();
        actual.setName("test");
        YamlShortcutsFixture actualAnother = new YamlShortcutsFixture();
        actualAnother.setName("test");
        StringBuilder res = new StringBuilder("- !FIXTURE");
        res.append(System.lineSeparator()).append("  name: test").append(System.lineSeparator()).append("- !FIXTURE")
                .append(System.lineSeparator()).append("  name: test").append(System.lineSeparator());
        assertThat(YamlEngine.marshal(Arrays.asList(actual, actualAnother)), is(res.toString()));
    }
}
