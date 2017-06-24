package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.API.*;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.lists;
import me.Alw7SHxD.essCore.messages;
import me.Alw7SHxD.essCore.util.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
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
public class CommandEss implements CommandExecutor, messages {
    private Core core;

    CommandEss(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl"))
                if (sender.hasPermission("esscore.reload")) {
                    try {
                        core.reloadConfig();
                        new EssWarpAPI(core).reload();
                        new EssSpawnAPI(core).reload();
                        new lists(core);
                        sender.sendMessage(EssAPI.color(m_reload_done));
                    } catch (Exception e) {
                        sender.sendMessage(EssAPI.color(m_reload_error));
                    }
                } else sender.sendMessage(EssAPI.color(m_no_permission));
            else if (args[0].equalsIgnoreCase("update") || args[0].equalsIgnoreCase("check"))
                new UpdateChecker(core).check(core.getDescription().getVersion(), sender);
            else if (args[0].equalsIgnoreCase("help") || args[0].equals("?")) {
                Integer maxPage = 1;
                sender.sendMessage(EssAPI.color(header(1, 1)));
                for(String string : commands().keySet()){
                    new FancyMessage("/").color(ChatColor.DARK_GRAY).then(string).color(ChatColor.GRAY).suggest("/" + string).tooltip("aliases: " + core.getCommand(string).getAliases().toString()).color(ChatColor.AQUA).send(sender);
                }
            } else sender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " help")));
        } else
            sender.sendMessage(EssAPI.color("&a&lessCore &7version &a&l" + core.getDescription().getVersion() + "\n&7made by: &a&lAlw7SHxD"));
        return true;
    }

    private HashMap<String, String> commands(){
        HashMap<String, String> commands = new HashMap<>();

        commands.put("broadcast", "send a message to the whole server.");
        commands.put("clearchat", "clear the chat.");
        commands.put("crafting", "open a crafting table.");
        commands.put("delhome", "delete an existing home.");
        commands.put("delspawn", "delete an existing spawn.");
        commands.put("delwarp", "delete an existing warp.");
        commands.put("enderchest", "open your enderchest.");
        commands.put("esscore", "esscore's main command.");
        commands.put("feed", "refill your hunger.");
        commands.put("fly", "toggle your flight.");
        commands.put("heal", "restore your health.");
        commands.put("home", "teleport to your home.");
        commands.put("mute", "mute a certain player.");
        commands.put("nickname", "change your display name.");
        commands.put("openinv", "open a certain player's inventory.");
        commands.put("sethome", "set a new home.");
        commands.put("setspawn", "set a global spawn point.");
        commands.put("setwarp", "set a new warp.");
        commands.put("spawn", "teleport to the global spawn point.");
        commands.put("suicide", "close your eyes. and never wakeup.");
        commands.put("unfreeze", "unfreeze a frozen player.");
        commands.put("unmute", "un-mute a muted player.");
        commands.put("vanish", "disappear from players eyes.");
        commands.put("warp", "teleport to a specific warp.");
        commands.put("warps", "display available warps.");

        return commands;
    }

    private String header(int current, int max){
        return "&b> &2&lessCore commands &7(&c&l" + current + "&8/&c&l" + max + "&7)";
    }

    private String footer(){
        return "";
    }
}
