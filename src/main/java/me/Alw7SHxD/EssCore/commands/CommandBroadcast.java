package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
public class CommandBroadcast implements CommandExecutor, messages {
    private Core core;

    public CommandBroadcast(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("esscore.broadcast")) {
            commandSender.sendMessage(EssAPI.color(m_no_permission));
            return true;
        }

        if (strings.length < 1) {
            commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9<message>")));
            return true;
        }

        String message = "";
        for (String part : strings) {
            if (!message.equals("")) message += " ";
            message += part;
        }
        core.getServer().broadcastMessage(EssAPI.color(core.getConfigCache().getString("broadcast-prefix") != null ? core.getConfigCache().getString("broadcast-prefix") + message : "&c&lBROADCAST! &r" + message));
        return true;
    }
}
