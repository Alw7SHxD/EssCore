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
                if (args.length == 1 || args[1].equals("1")) {
                    sender.sendMessage(EssAPI.color(helpLines(true, maxPage, 1) +
                            "\n &7/essCore &9[<help/update/reload>] &7: essCore's main command" +
                            "\n &7/broadcast &9<message> &7: send your message to the whole server" +
                            "\n &7/clearchat &9[player] [<-s|-h>] &7: clear the chat" +
                            "\n &7/create-kit &9<name> &7: create a kit based on your items" +
                            "\n &7/fly &9[player] &7: toggle your/players's flight" +
                            "\n &7/freeze &9<player> &7: stop a player from moving" +
                            "\n &7/unfreeze &9<player> &7: unfreeze a frozen player" +
                            "\n &7/mute &9<player> &7: stop a player from talking in chat" +
                            helpLines(false, maxPage, 1)));
                } else if (args[1].equals("2")) {
                    sender.sendMessage(EssAPI.color(helpLines(true, maxPage, 2) +
                            "\n &7/unmute &9<player> &7: un-mute a muted player" +
                            "\n &7/setspawn &7: set a global spawn-point" +
                            "\n &7/delspawn &7: delete the global spawn-point" +
                            "\n &7/spawn &9[player] &7: teleport to the global spaw-point" +
                            "\n &7/setwarp &9<name> [<TRUE|FALSE>] &7: set a warp" +
                            "\n &7/delwarp &9<name> &7: delete a warp" +
                            "\n &7/warp &9<name> [player] &7: teleport to a warp" +
                            "\n &7/warps &7: display available warps" +
                            helpLines(false, maxPage, 2)));
                } else if (args[1].equals("3")) {
                    sender.sendMessage(EssAPI.color(helpLines(true, maxPage, 3) +
                            "\n &7/vanish &9[player] &7: hide yourself/target from the other players." +
                            "\n &7/heal &9[player] &7: restore your/target's health." +
                            "\n &7/feed &9[player] &7: restore your/target's hunger." +
                            "\n &7/sethome &9<name> &7: Set a new home." +
                            "\n &7/delhome &9<name> &7: Delete an existing home." +
                            "\n &7/home &9[name] &7: Yeleport to one of your homes." +
                            "\n &7/workbench &7: Open a crafting table." +
                            "\n &7/openinv &9<Player> &7: Open a player's inventory." +
                            helpLines(false, maxPage, 3)));
                } else if (args[1].equals("4")) {
                    sender.sendMessage(EssAPI.color(helpLines(true, maxPage, 4) +
                            "\n &7/enderchest &9[player] &7: Open your/player's enderchest." +
                            helpLines(false, maxPage, 4)));
                } else sender.sendMessage(EssAPI.color(String.format(m_ess_help_invalid_page, maxPage)));
            } else sender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " help")));
        } else
            sender.sendMessage(EssAPI.color("&a&lessCore &7version &a&l" + core.getDescription().getVersion() + "\n&7made by: &a&lAlw7SHxD"));
        return true;
    }

    private String helpLines(Boolean header, Integer MaxPage, Integer currentPage) {
        return header ? "&8&l&m|-------------->&a&l essCore &7help (" + currentPage + "/" + MaxPage + ") &8&l&m<---------------|&r" : "&8&l&m|----------------------------------------------|";
    }
}
