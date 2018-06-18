package me.Alw7SHxD.EssCore;

import me.Alw7SHxD.EssCore.API.EssEconomy;
import me.Alw7SHxD.EssCore.API.EssPlayer;
import me.Alw7SHxD.EssCore.commands.RegisterCommands;
import me.Alw7SHxD.EssCore.listeners.RegisterListeners;
import me.Alw7SHxD.EssCore.util.ConfigCache;
import me.Alw7SHxD.EssCore.util.Runnable;
import me.Alw7SHxD.EssCore.util.hooks.PlaceholderApiHook;
import me.Alw7SHxD.EssCore.util.hooks.VaultHook;
import me.Alw7SHxD.EssCore.util.updaters.SpigotUpdater;
import me.Alw7SHxD.EssCore.util.vars.Lists;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
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
public class Core extends JavaPlugin {
    private ConfigCache configCache = new ConfigCache(this);
    public boolean usingPlaceholderAPI = false;
    public SpigotUpdater spigotUpdater = new SpigotUpdater(this);
    public boolean hookedWithVault = false;
    public Lists Lists;
    private EssEconomy essEconomy;
    private VaultHook vaultHook;

    public void onEnable() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        saveDefaultConfig();
        configCache.load();

        spigotUpdater.check(getServer().getConsoleSender());
        Runnable runnable = new Runnable(this);
        runnable.asyncOneSecond();

        if(configCache.getBoolean("metrics"))
            new Metrics(this);

        if(getServer().getPluginManager().isPluginEnabled("Vault")) {
            this.essEconomy = new EssEconomy(this);
            this.vaultHook = new VaultHook(this);
            vaultHook.hook();
            this.hookedWithVault = true;
        }

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderApiHook(this).hook();
            this.usingPlaceholderAPI = true;
            getLogger().info("[API] Detected PlaceholderAPI.");
        }

        this.Lists = new Lists(this);
        this.Lists.startup();

        new RegisterListeners(this);
        new RegisterCommands(this);

        if (getConfig().getDouble("EssCore") != 7.3 || getConfig().getDouble("essCore") != 7.3)
            getLogger().info("Your configuration file is outdated, please remove your old config.yml file.");

        checkBalances();
    }

    public void onDisable() {
        if(hookedWithVault)
            vaultHook.unHook();

        getLogger().info("EssCore v" + getDescription().getVersion() + " has been disabled.");
    }

    private void checkBalances() {
        if (getServer().getOnlinePlayers().size() != 0)
            for (Player player : getServer().getOnlinePlayers())
                new EssPlayer(player).setBalance();
    }

    public EssEconomy getEssEconomy() {
        return essEconomy;
    }

    public ConfigCache getConfigCache() {
        return configCache;
    }
}
