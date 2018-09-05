package me.dotalw.esscore;

import co.aikar.commands.BukkitCommandManager;
import me.dotalw.esscore.lib.api.EssAPI;
import me.dotalw.esscore.lib.commands.RegisterCommands;
import me.dotalw.esscore.lib.handlers.RegisterHandlers;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *    Copyright (C) 2011-2018 dotalw.
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
public final class EssCore extends JavaPlugin {
    private EssAPI api;

    @Override
    public void onEnable() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);
        api = new EssAPI(commandManager);

        register(commandManager);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void register(BukkitCommandManager manager) {
        new RegisterHandlers(this);
        new RegisterCommands(this, manager);
    }

    public EssAPI getAPI() {
        return api;
    }
}
