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

import me.dotalw.esscore.lib.utils.vars.ICacheable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Configuration<T extends ICacheable<T>> {
    private T type;
    private final static int VERSION = 10;
    private JavaPlugin plugin;
    private File configuration;
    private YamlConfiguration yamlConfiguration;
    private String filename, versionPath;
    private boolean updatable;
    private LinkedHashMap<T, Object> cache = new LinkedHashMap<>();

    public Configuration(JavaPlugin plugin, String filename) {
        this(plugin, filename, false, false, null);
    }

    public Configuration(JavaPlugin plugin, String filename, boolean updatable, boolean copyResource) {
        this(plugin, filename, updatable, copyResource, "configuration.version");
    }

    public Configuration(JavaPlugin plugin, String filename, boolean updatable, boolean copyResource, String versionPath) {
        this.plugin = plugin;
        this.filename = filename;
        this.updatable = updatable;
        this.versionPath = versionPath;
        this.configuration = new File(plugin.getDataFolder(), filename);
        init(copyResource);
    }

    private void init(boolean copyResource) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
            if (copyResource)
                plugin.saveResource(filename, false);
            plugin.getLogger().info(String.format("Created configuration file (%s)", filename));
        }

        reload();
    }

    public void update() {
        update(VERSION);
    }

    public void update(int version) {
        if (!isUpdatable())
            return;

        YamlConfiguration current = getConfig();
        if (getConfig().getInt(versionPath) == version)
            return;

        if (!configuration.exists()) {
            plugin.getLogger().info(String.format("Couldn't find %s, creating a new one!", filename));
            plugin.saveResource(filename, true);
            reload();
            return;
        } else if (!getConfig().isSet(versionPath)) {
            plugin.getLogger().warning(String.format("Configuration file (%s) is deprecated and no longer works, replacing it with the latest version.", filename));
            plugin.saveResource(filename, true);
            reload();
            return;
        }

        plugin.getLogger().info(String.format("Configuration file (%s) is outdated, updating it now.", filename));
        plugin.saveResource(filename, true);
        reload();
        getConfig().getKeys(false).forEach(key -> getConfig().getConfigurationSection(key).getKeys(true).stream().filter(k -> current.get(concat(key, k)) != null).forEach(k -> set(concat(key, k), current.get(concat(key, k)))));
        getConfig().set(versionPath, version);
        save();
        reload();
        plugin.getLogger().info(String.format("Configuration file (%s) got updated.", filename));
    }

    private String concat(String s1, String s2) {
        return s1 + "." + s2;
    }

    private void debug(YamlConfiguration c) {
        for (String s: c.getKeys(false)) {
            plugin.getLogger().warning(s);
            for (String s1: c.getConfigurationSection(s).getKeys(true))
                plugin.getLogger().info("- " + s1 + ": " + c.get(s+"."+s1));
        }
    }

    private void loadCache() {
        cache.clear();
        getConfig().getKeys(true).stream().filter(s -> type.getNameByKey(s) != null).forEachOrdered(s -> cache.put(type.getNameByKey(s), get(s)));
    }
    
    public void reload() {
        yamlConfiguration = YamlConfiguration.loadConfiguration(configuration);
        loadCache();
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

    public boolean isUpdatable() {
        return updatable;
    }

    public void set(String path, Object value) {
        getConfig().set(path, value);
    }

    public boolean isSet(String path) {
        return getConfig().isSet(path);
    }

    public Object get(String path) {
        return getConfig().get(path);
    }

    public Object get(T key) {
        return cache.get(key);
    }

    public String getString(String path) {
        return getConfig().getString(path);
    }

    public String getString(T key) {
        return (String) get(key);
    }

    public int getInt(String path) {
        return getConfig().getInt(path);
    }

    public int getInt(T key) {
        return (int) get(key);
    }

    public long getLong(String path) {
        return getConfig().getLong(path);
    }

    public long getLong(T key) {
        return (long) get(key);
    }

    public Double getDouble(String path) {
        return getConfig().getDouble(path);
    }

    public double getDouble(T key) {
        return (double) get(key);
    }

    public float getFloat(String path) {
        return (float) get(path);
    }

    public float getFloat(T key) {
        return (float) get(key);
    }

    public Boolean getBool(String path) {
        return getConfig().getBoolean(path);
    }

    public boolean getBool(T key) {
        return (boolean) get(key);
    }

    public List<?> getList(String path) {
        return getConfig().getList(path);
    }

    public List<?> getList(T key) {
        return (List<?>) get(key);
    }

    public ConfigurationSection getSection(String path) {
        return getConfig().getConfigurationSection(path);
    }

    public ConfigurationSection getSection(T key) {
        return (ConfigurationSection) get(key);
    }
}