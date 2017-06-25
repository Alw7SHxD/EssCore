package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.API.*;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.messages;
import me.Alw7SHxD.essCore.util.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
                        core.lists.startup();
                        sender.sendMessage(EssAPI.color(m_reload_done));
                    } catch (Exception e) {
                        sender.sendMessage(EssAPI.color(m_reload_error));
                    }
                } else sender.sendMessage(EssAPI.color(m_no_permission));
            else if (args[0].equalsIgnoreCase("update") || args[0].equalsIgnoreCase("check"))
                new UpdateChecker(core).check(core.getDescription().getVersion(), sender);
            else if (args[0].equalsIgnoreCase("help") || args[0].equals("?")) {
                Integer maxPage = 4;
                try {
                    getPage(sender, Integer.parseInt(args[1]), maxPage);
                }catch (NumberFormatException e){
                    sender.sendMessage(EssAPI.color("&c&lHey! &7only numbers are allowed to be used."));
                }
            } else sender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " help")));
        } else
            sender.sendMessage(EssAPI.color("&a&lessCore &7version &a&l" + core.getDescription().getVersion() + "\n&7made by: &a&lAlw7SHxD"));
        return true;
    }

    private void getPage(CommandSender commandSender, int page, int max){
        if(page >= max) {
            commandSender.sendMessage(EssAPI.color(String.format(m_ess_help_invalid_page, max)));
            return;
        }

        commandSender.sendMessage(EssAPI.color(header(page, max)));

        for(String string : commands(max).keySet()) {
            if (commands(max).get(string).equals(page)) {
                FancyMessage tooltips = new FancyMessage("aliases: ").color(ChatColor.GRAY).then(core.getCommand(string).getAliases().toString()).color(ChatColor.DARK_AQUA).then("\n").then("usage: ").color(ChatColor.GRAY).then(core.getCommand(string).getUsage().replace("<command>", string)).color(ChatColor.DARK_AQUA);
                new FancyMessage("/").color(ChatColor.DARK_GRAY).then(string).color(ChatColor.AQUA).suggest("/" + string).formattedTooltip(tooltips).then(" > ").color(ChatColor.GRAY).then(core.getCommand(string).getDescription()).send(commandSender);
            }
        }

        commandSender.sendMessage(EssAPI.color(footer()));
    }

    private HashMap<String, Integer> commands(int max){
        HashMap<String, Integer> commands = new HashMap<>();

        int i = 1;
        for(int x = 0; x <= 7; x++) {
            if(x == 7) {
                if(i >= max) break;
                else{
                    i++;
                    x = 0;
                    continue;
                }
            }

            commands.put("broadcast", i);
            commands.put("clearchat", i);
            commands.put("crafting", i);
            commands.put("delhome", i);
            commands.put("delspawn", i);
            commands.put("delwarp", i);
            commands.put("enderchest", i);
            commands.put("esscore", i);
            commands.put("feed", i);
            commands.put("fly", i);
            commands.put("freeze", i);
            commands.put("heal", i);
            commands.put("home", i);
            commands.put("mute", i);
            commands.put("nickname", i);
            commands.put("openinv", i);
            commands.put("sethome", i);
            commands.put("setspawn", i);
            commands.put("setwarp", i);
            commands.put("spawn", i);
            commands.put("suicide", i);
            commands.put("unfreeze", i);
            commands.put("unmute", i);
            commands.put("vanish", i);
            commands.put("warp", i);
            commands.put("warps", i);
        }

        return commands;
    }

    private String header(int current, int max){
        return "&b> &2&lessCore commands &7(&c&l" + current + "&8/&c&l" + max + "&7)";
    }

    private String footer(){
        return "&b> &7you can &nclick &7or &nhover &7over the commands";
    }
}
