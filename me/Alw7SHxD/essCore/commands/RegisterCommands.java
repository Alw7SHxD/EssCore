package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.Core;
import org.bukkit.command.CommandExecutor;

import java.util.HashMap;

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

public class RegisterCommands {
    public RegisterCommands(Core core) {
        try {
            HashMap<String, CommandExecutor> commands = new HashMap<>();

            commands.put("esscore", new CommandEss(core));
            commands.put("broadcast", new CommandBroadcast(core));
            commands.put("clearchat", new CommandClearChat(core));
            commands.put("fly", new CommandFly(core));
            commands.put("mute", new CommandMute(core));
            commands.put("unmute", new CommandUnMute(core));
            commands.put("nickname", new CommandNick(core));
            commands.put("warp", new CommandWarp(core));
            commands.put("warps", new CommandWarps(core));
            commands.put("setwarp", new CommandSetWarp(core));
            commands.put("delwarp", new CommandDelWarp(core));
            commands.put("freeze", new CommandFreeze(core));
            commands.put("unfreeze", new CommandUnFreeze(core));
            commands.put("suicide", new CommandSuicide());
            commands.put("spawn", new CommandSpawn(core));
            commands.put("setspawn", new CommandSetSpawn(core));
            commands.put("delspawn", new CommandDelSpawn(core));
            commands.put("vanish", new CommandVanish(core));
            commands.put("heal", new CommandHeal(core));
            commands.put("feed", new CommandFeed(core));
            commands.put("workbench", new CommandCraftingTable(core));
            commands.put("enderchest", new CommandEnderChest(core));
            commands.put("openinv", new CommandOpenInv(core));
            commands.put("sethome", new CommandSetHome(core));
            commands.put("delhome", new CommandDelHome(core));
            commands.put("home", new CommandHome(core));
            commands.put("homes", new CommandHome(core));

            for (String command : commands.keySet())
                core.getCommand(command).setExecutor(commands.get(command));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
