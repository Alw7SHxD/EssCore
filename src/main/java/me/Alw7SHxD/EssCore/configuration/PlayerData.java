package me.Alw7SHxD.EssCore.configuration;

/*
 * (C) Copyright 2017 Alw7SHxD.
 *
 * EssCore is licensed under the Apache License, Version 2.0 (the "License");
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;

public class PlayerData {

    private final JavaPlugin plugin;
    private File configFile;
    private FileConfiguration fileConfiguration;

    public PlayerData(OfflinePlayer player, JavaPlugin plugin) {
        if (plugin == null)
            throw new IllegalArgumentException("Plugin cannot be null");
        if (player == null)
            throw new IllegalArgumentException("Player cannot be null");
        this.plugin = plugin;
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
        File dataFolder = new File(plugin.getDataFolder().getPath() + File.separator + "EssPlayer");
        this.configFile = new File(dataFolder, player.getUniqueId() + ".yml");
        if (!dataFolder.exists()) dataFolder.mkdir();
        if (!configFile.exists())
            if (player.isOnline()) saveDefaultYaml();
    }

    public void reloadYaml() {
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        final InputStream defConfigStream = plugin.getResource(configFile.getPath());
        if (defConfigStream == null) {
            return;
        }

        final YamlConfiguration defConfig;
        final byte[] contents;
        defConfig = new YamlConfiguration();
        try {
            contents = ByteStreams.toByteArray(defConfigStream);
        } catch (final IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Unexpected failure reading config.yml", e);
            return;
        }

        final String text = new String(contents, Charset.defaultCharset());
        if (!text.equals(new String(contents, Charsets.UTF_8))) {
            plugin.getLogger()
                    .warning("Default system encoding may have misread config.yml from plugin jar");
        }

        try {
            defConfig.loadFromString(text);
        } catch (final InvalidConfigurationException e) {
            plugin.getLogger().log(Level.SEVERE, "Cannot load configuration from jar", e);
        }
        fileConfiguration.setDefaults(defConfig);
    }

    public FileConfiguration getYaml() {
        if (fileConfiguration == null) {
            this.reloadYaml();
        }
        return fileConfiguration;
    }

    public void saveYaml() {
        if (fileConfiguration == null || configFile == null) {
            return;
        } else {
            try {
                getYaml().save(configFile);
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
            }
        }
    }

    public void saveDefaultYaml() {
        if (!configFile.exists()) {
            Path file = configFile.toPath();
            try {
                Files.createFile(file);
            } catch (FileAlreadyExistsException x) {
                Bukkit.getLogger().log(Level.INFO, "Tried to create a new Player Config, but it already existed! ");
            } catch (IOException x) {
                if (Bukkit.broadcast(ChatColor.DARK_RED + "AN ERROR OCCURRED WHILE ATTEMPTING TO CREATE FILE: " + this.configFile.getName(), "esscore.debug") == 0) {
                    Bukkit.getLogger().log(Level.SEVERE, "AN ERROR OCCURRED WHILE ATTEMPTING TO CREATE FILE: " + this.configFile.getName(), x);
                }
            }

        }
    }

    public void modifyYaml() {
        saveYaml();
        reloadYaml();
    }
}