package me.Alw7SHxD.EssCore;

import me.Alw7SHxD.EssCore.API.EssEconomy;
import me.Alw7SHxD.EssCore.API.EssPlayer;
import me.Alw7SHxD.EssCore.commands.RegisterCommands;
import me.Alw7SHxD.EssCore.listeners.RegisterListeners;
import me.Alw7SHxD.EssCore.util.ConfigCache;
import me.Alw7SHxD.EssCore.util.Runnable;
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
    private boolean usingPlaceholderAPI = false;
    private SpigotUpdater spigotUpdater = new SpigotUpdater(this);
    private boolean hookedWithVault = false;
    private boolean permsHookedWithVault = false;
    private Lists Lists;
    private EssEconomy essEconomy;
    private VaultHook vaultHook;
    private Metrics metrics = null;

    public void onEnable() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        saveDefaultConfig();
        configCache.load();

        spigotUpdater.check(getServer().getConsoleSender());
        Runnable runnable = new Runnable(this);
        runnable.asyncOneSecond();

        if (getConfigCache().getBoolean("metrics")) {
            try {
                metrics = new Metrics(this);
                getLogger().info("Sending bStats metrics anonymously!");
            } catch (Exception e){
                getLogger().severe("Couldn't send anonymous data to bStats!");
            }
        }

        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            this.essEconomy = new EssEconomy(this);
            this.vaultHook = new VaultHook(this);
            vaultHook.hookEconomy();
            this.hookedWithVault = true;
            this.permsHookedWithVault = vaultHook.setup();
        }

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.usingPlaceholderAPI = true;
            getLogger().info("[API] Detected PlaceholderAPI.");
        }

        this.Lists = new Lists(this);
        this.Lists.startup();

        new RegisterListeners(this);
        new RegisterCommands(this);

        if (!getConfigCache().getString("version").equals("7.3"))
            getLogger().severe(String.format("Your configuration file is outdated (Current: %s) Please remove your old config.yml file and restart the server.", getConfigCache().getString("EssCore")));

        checkBalances();
    }

    public void onDisable() {
        if (hookedWithVault)
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

    public Metrics getMetrics() {
        return metrics;
    }

    public boolean isUsingPlaceholderAPI() {
        return usingPlaceholderAPI;
    }

    public SpigotUpdater getSpigotUpdater() {
        return spigotUpdater;
    }

    public boolean isHookedWithVault() {
        return hookedWithVault;
    }

    public boolean isPermsHookedWithVault() {
        return permsHookedWithVault;
    }

    public me.Alw7SHxD.EssCore.util.vars.Lists getLists() {
        return Lists;
    }

    public VaultHook getVaultHook() {
        return vaultHook;
    }
}
