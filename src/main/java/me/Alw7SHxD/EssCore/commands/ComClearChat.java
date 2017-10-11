package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssChat;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
public class ComClearChat implements CommandExecutor, messages {
    private Core core;
    private EssChat.Clear cc;

    ComClearChat(Core core) {
        this.core = core;
        this.cc = new EssChat.Clear(core);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("esscore.clearchat")) {
            commandSender.sendMessage(EssAPI.color(m_no_permission));
            return true;
        }

        if (strings.length == 0) {
            if (!commandSender.hasPermission("esscore.clearchat.global")) {
                commandSender.sendMessage(m_no_permission);
                return true;
            }

            cc.clearChat(commandSender);
        } else if (strings.length == 1 && strings[0].equalsIgnoreCase("-a")) {
            cc.clearChat();
        } else if (strings.length > 0) {
            if (!commandSender.hasPermission("esscore.clearchat.target")) {
                commandSender.sendMessage(m_no_permission);
                return true;
            }

            Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
            if (target == null) return true;

            if (strings.length == 1)
                cc.clearChat(commandSender, target);
            else if (strings.length == 2 && strings[1].equalsIgnoreCase("-a"))
                cc.clearChat(target);
            else
                commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9[Player]")));
        } else commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9[Player]")));
        return true;
    }
}
