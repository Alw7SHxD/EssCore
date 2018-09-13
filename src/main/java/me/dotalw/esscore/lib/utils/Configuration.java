package me.dotalw.esscore.lib.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * EssCore (2017) made by dotalw (C) 2011-2018
 */
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
            plugin.getDataFolder().mkdir();
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
            plugin.getLogger().warning("Your configuration file is deprecated and no longer works, replacing it with the latest version.");
            plugin.saveResource(configuration.getName(), true);
            return;
        }

        plugin.getLogger().info("Your configuration file is outdated, updating it now...");
        plugin.saveResource(configuration.getName(), true);
        getConfig().getKeys(false).stream().flatMap(key -> getConfig().getConfigurationSection(key).getKeys(true).stream()).forEach(k -> getConfig().set(k, current.get(k)));
        getConfig().set("configuration.version", VERSION);
        save();
        reload();
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