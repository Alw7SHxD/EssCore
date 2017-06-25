package me.Alw7SHxD.essCore;

import me.Alw7SHxD.essCore.API.EssPlaceholders;
import me.Alw7SHxD.essCore.API.UpdateChecker;
import me.Alw7SHxD.essCore.commands.RegisterCommands;
import me.Alw7SHxD.essCore.listeners.RegisterListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
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
public class Core extends JavaPlugin {
    private UpdateChecker updateChecker = new UpdateChecker(this);
    public boolean usingPlaceholderAPI = false;
    public lists lists;

    public void onEnable() {
        if(!getDataFolder().exists()) getDataFolder().mkdir();
        saveDefaultConfig();

        updateChecker.check(this, getDescription().getVersion());

        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new EssPlaceholders(this).hook();
            this.usingPlaceholderAPI = true;
            getLogger().info("detected PlaceholderAPI.");
        }

        this.lists = new lists(this);
        this.lists.startup();

        new RegisterListeners(this);
        new RegisterCommands(this);
    }

    public void onDisable() {
        getLogger().info("essCore v" + getDescription().getVersion() + " has been disabled.");
    }
}
