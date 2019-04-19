/*
 *    Copyright (C) 2011-2019 dotalw.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package me.dotalw.esscore.lib.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Configuration {
    private final static int VERSION = 10;

    private JavaPlugin plugin;
    private File configuration;
    private YamlConfiguration yamlConfiguration;

    public Configuration(JavaPlugin plugin, String filename, boolean copyResource) {
        super();
        this.plugin = plugin;
        this.configuration = new File(plugin.getDataFolder(), filename);

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir()
            if (copyResource)
                plugin.saveResource(filename, false);
        }

        reload();
    }

    public void update() {
        YamlConfiguration current = getConfig();
        if (getConfig().getInt("configuration.version") == VERSION)
            return;

        if (!configuration.exists()) {
            plugin.getLogger().info(String.format("Couldn't find %s, creating a new one!", configuration.getName()));
            plugin.saveResource(configuration.getName(), true);
            return;
        } else if (!getConfig().isSet("configuration.version")) {
            plugin.getLogger().warning(String.format("Configuration file (%s) is deprecated and no longer works, replacing it with the latest version.", configuration.getName()));
            plugin.saveResource(configuration.getName(), true);
            return;
        }

        plugin.getLogger().info(String.format("Configuration file (%s) is outdated, updating it now.", configuration.getName()));
        plugin.saveResource(configuration.getName(), true);
        getConfig().getKeys(false).stream().flatMap(key -> getConfig().getConfigurationSection(key).getKeys(true).stream()).forEach(k -> getConfig().set(k, current.get(k)));
        getConfig().set("configuration.version", VERSION);
        save();
        reload();
        plugin.getLogger().info(String.format("Configuration file (%s) got updated.", configuration.getName()));
    }

    public void reload() {
        yamlConfiguration = YamlConfiguration.loadConfiguration(configuration);
    }

    // Sadly, still doesn't save comments....
    public void save() {
        try {
            yamlConfiguration.save(configuration);
        } catch (IOException ignored) {}
    }

    public YamlConfiguration getConfig() {
        return yamlConfiguration;
    }
}