package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.Vanish;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class CommandVanish implements CommandExecutor, messages {
    private Core core;

    CommandVanish(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("esscore.vanish")) {
            commandSender.sendMessage(EssAPI.color(m_no_permission));
            return true;
        }

        if (strings.length == 0) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(m_not_player);
                return true;
            }

            Vanish p = new Vanish(core, (Player) commandSender);
            p.eVanish();
        } else if (strings.length == 1) {
            if (!commandSender.hasPermission("esscore.vanish.target")) {
                commandSender.sendMessage(EssAPI.color(m_no_permission));
                return true;
            }

            Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
            if (target == null) return true;

            Vanish p = new Vanish(core, target);
            p.eVanish(commandSender);
        } else commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9[Player]")));
        return true;
    }
}
