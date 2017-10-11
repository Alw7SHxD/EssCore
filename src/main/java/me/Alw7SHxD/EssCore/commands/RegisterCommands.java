package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.Core;
import org.bukkit.command.CommandExecutor;

import java.util.HashMap;

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

public class RegisterCommands {
    public RegisterCommands(Core core) {
        try {
            HashMap<String, CommandExecutor> commands = new HashMap<>();

            commands.put("balance", new ComBalance(core, true));
            commands.put("broadcast", new ComBroadcast(core));
            commands.put("clearchat", new ComClearChat(core));
            commands.put("delhome", new ComDelHome(core));
            commands.put("delspawn", new ComDelSpawn(core));
            commands.put("delwarp", new ComDelWarp(core));
            commands.put("eco", new ComEconomy(core));
            commands.put("enderchest", new ComEnderChest(core));
            commands.put("esscore", new ComEss(core));
            commands.put("feed", new ComFeed(core));
            commands.put("fly", new ComFly(core));
            commands.put("freeze", new ComFreeze(core));
            commands.put("heal", new ComHeal(core));
            commands.put("home", new ComHome(core));
            commands.put("homes", new ComHomes(core));
            commands.put("money", new ComBalance(core, false));
            commands.put("mute", new ComMute(core));
            commands.put("nickname", new ComNick(core));
            commands.put("openinv", new ComOpenInv(core));
            commands.put("pay", new ComPay(core));
            commands.put("sethome", new ComSetHome(core));
            commands.put("setspawn", new ComSetSpawn(core));
            commands.put("setwarp", new ComSetWarp(core));
            commands.put("spawn", new ComSpawn(core));
            commands.put("suicide", new ComSuicide());
            commands.put("unfreeze", new ComUnFreeze(core));
            commands.put("unmute", new ComUnMute(core));
            commands.put("vanish", new ComVanish(core));
            commands.put("warp", new ComWarp(core));
            commands.put("warps", new ComWarps(core));
            commands.put("workbench", new ComCrafting(core));
            commands.put("gm", new ComGamemode(core));

            for (String command : commands.keySet())
                core.getCommand(command).setExecutor(commands.get(command));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
