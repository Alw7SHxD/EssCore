package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.API.*;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.lists;
import me.Alw7SHxD.essCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
                Integer maxPage = 4;

            } else sender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " help")));
        } else
            sender.sendMessage(EssAPI.color("&a&lessCore &7version &a&l" + core.getDescription().getVersion() + "\n&7made by: &a&lAlw7SHxD"));
        return true;
    }

    private String helpLines(Boolean header, Integer MaxPage, Integer currentPage) {
        return header ? "&8&l&m|-------------->&a&l essCore &7help (" + currentPage + "/" + MaxPage + ") &8&l&m<---------------|&r" : "&8&l&m|----------------------------------------------|";
    }
}
