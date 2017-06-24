package me.Alw7SHxD.essCore.configuration;

import com.sun.org.apache.xerces.internal.xs.StringList;
import me.Alw7SHxD.essCore.Core;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/*
 * (C) Copyright 2017 Alw7SHxD.
 *
 * essCore is licensed under the Apache License, Version 2.0 (the "License");
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
public class Data extends CustomConfiguration {

    public Data(Core core, String fileName) {
        super(core, fileName + ".yml", "Data");
    }

    public void set(String path, Object value) {
        config.set(path, value);
    }

    public boolean isSet(String path) {
        return config.isSet(path);
    }

    public ConfigurationSection getConfigurationSection(String path){
        return config.getConfigurationSection(path);
    }

    public boolean sectionExists(String path){
        return getConfigurationSection(path) != null;
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public double getDouble(String path) {
        return config.getDouble(path);
    }

    public List<String> getList(String path){
        return config.getStringList(path);
    }

    public int getInt(String path){
        return config.getInt(path);
    }

    public float getFloat(String path) {
        return (float) config.getDouble(path);
    }
}
