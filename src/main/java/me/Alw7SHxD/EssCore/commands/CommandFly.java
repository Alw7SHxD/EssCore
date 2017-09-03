package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssFly;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.messages;
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
public class CommandFly implements CommandExecutor, messages {
    private Core core;

    CommandFly(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("esscore.fly")) {
            commandSender.sendMessage(EssAPI.color(m_no_permission));
            return true;
        }

        if (strings.length == 0) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(m_not_player);
                return true;
            }

            EssFly p = new EssFly(core, (Player) commandSender);
            p.eFly();
        } else if (strings.length == 1) {
            if (!commandSender.hasPermission("esscore.fly.target")) {
                commandSender.sendMessage(EssAPI.color(m_no_permission));
                return true;
            }

            Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
            if (target == null) return true;

            EssFly p = new EssFly(core, target);
            p.eFly(commandSender);
        } else commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9[Player]")));
        return true;
    }
}
