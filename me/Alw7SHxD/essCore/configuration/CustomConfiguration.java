package me.Alw7SHxD.essCore.configuration;

import me.Alw7SHxD.essCore.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/*
 * (C) Copyright 2017 Alw7SHxD.
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
public class CustomConfiguration {
    private Core core;
    protected FileConfiguration config;
    private File file;

    public CustomConfiguration(Core core, String fileName) {
        this.core = core;
        create(fileName);
    }

    public CustomConfiguration(Core core, String fileName, String folderName) {
        this.core = core;
        create(fileName, folderName);
    }

    private void create(String fileName) {
        file = new File(core.getDataFolder(), fileName);
        debug(file);
    }

    private void create(String fileName, String folderName) {
        if (!new File(core.getDataFolder(), folderName).exists())
            new File(core.getDataFolder(), folderName).mkdir();
        file = new File(core.getDataFolder(), folderName + File.separator + fileName);
        debug(file);
    }

    private void debug(File file) {
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (Exception e) {
                core.getLogger().warning("An error occurred, please report this error to the author.");
                e.printStackTrace();
            }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        try {
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
