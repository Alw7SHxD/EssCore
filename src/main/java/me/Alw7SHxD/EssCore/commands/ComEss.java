package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.*;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import me.Alw7SHxD.EssCore.util.json.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.*;

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
public class ComEss implements CommandExecutor, messages {
    private Core core;

    ComEss(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl"))
                if (sender.hasPermission("esscore.reload")) {
                    try {
                        core.getConfigCache().load();
                        new EssWarps(core).reload();
                        new EssSpawn(core).reload();
                        core.Lists.startup();
                        sender.sendMessage(EssAPI.color(m_reload_done));
                    } catch (Exception e) {
                        sender.sendMessage(EssAPI.color(m_reload_error));
                    }
                } else sender.sendMessage(EssAPI.color(m_no_permission));
            else if (args[0].equalsIgnoreCase("update") || args[0].equalsIgnoreCase("check"))
               core.spigotUpdater.check(sender);
            else if (args[0].equalsIgnoreCase("help") || args[0].equals("?")) {
                Integer maxPage = 4;
                try {
                    if (args.length == 1 || args[1].isEmpty())
                        getCommands(sender, 1, maxPage);
                    else
                        getCommands(sender, Integer.parseInt(args[1]), maxPage);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    sender.sendMessage(EssAPI.color("&c&lHey! &7only numbers are allowed to be used."));
                }
            } else sender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " help")));
        } else {
            sender.sendMessage(EssAPI.color(String.format("&a&lEssCore &7version &a&l%s", core.getDescription().getVersion())));
            sender.sendMessage(EssAPI.color("&7Created by: &a&lAlw7SHxD"));
        }
        return true;
    }

    public void getCommands(CommandSender commandSender, int page, int max) {
        SortedMap<String, Integer> commands = new TreeMap<>();
        commands.put("balance", 29);
        commands.put("broadcast", 1);
        commands.put("clearchat", 2);
        commands.put("crafting", 3);
        commands.put("delhome", 4);
        commands.put("delspawn", 5);
        commands.put("delwarp", 6);
        commands.put("eco", 28);
        commands.put("enderchest", 7);
        commands.put("esscore", 8);
        commands.put("feed", 9);
        commands.put("fly", 10);
        commands.put("freeze", 11);
        commands.put("gm", 32);
        commands.put("heal", 12);
        commands.put("home", 13);
        commands.put("homes", 27);
        commands.put("money", 30);
        commands.put("mute", 14);
        commands.put("nickname", 15);
        commands.put("openinv", 16);
        commands.put("pay", 31);
        commands.put("sethome", 17);
        commands.put("setspawn", 18);
        commands.put("setwarp", 19);
        commands.put("spawn", 20);
        commands.put("suicide", 21);
        commands.put("unfreeze", 22);
        commands.put("unmute", 23);
        commands.put("vanish", 24);
        commands.put("warp", 25);
        commands.put("warps", 26);
        // 32

        paginate(commandSender, commands, page, 8, max);
    }

    public void paginate(CommandSender sender, SortedMap<String, Integer> map, int page, int pageLength, int max) {
        if (page > max) {
            sender.sendMessage(EssAPI.color(String.format(m_ess_help_invalid_page, max)));
            return;
        }
        sender.sendMessage(EssAPI.color(header(page, (((map.size() % pageLength) == 0) ? map.size() / pageLength : (map.size() / pageLength) + 1))));
        int i = 0, k = 0;
        page--;
        for (final Map.Entry<String, Integer> e : map.entrySet()) {
            k++;
            if ((((page * pageLength) + i + 1) == k) && (k != ((page * pageLength) + pageLength + 1))) {
                i++;
                FancyMessage tooltips = new FancyMessage(core.getCommand(e.getKey()).getAliases().size() == 1 ? "alias: " : "aliases: ").color(ChatColor.GRAY).then(core.getCommand(e.getKey()).getAliases().toString()).color(ChatColor.DARK_AQUA).then("\n").then("usage: ").color(ChatColor.GRAY).then(core.getCommand(e.getKey()).getUsage().replace("<command>", e.getKey())).color(ChatColor.DARK_AQUA);
                new FancyMessage("/").color(ChatColor.DARK_GRAY).then(e.getKey()).color(ChatColor.AQUA).suggest("/" + e.getKey()).formattedTooltip(tooltips).then(" > ").color(ChatColor.GRAY).then(core.getCommand(e.getKey()).getDescription()).send(sender);
            }
        }
        sender.sendMessage(EssAPI.color(footer()));
    }

    private String header(int current, int max) {
        return "&b> &2&lEssCore commands &7(&c&l" + current + "&8/&c&l" + max + "&7)";
    }

    private String footer() {
        return "&b> &7you can &nclick&7 or &nhover&7 over the commands";
    }
}
