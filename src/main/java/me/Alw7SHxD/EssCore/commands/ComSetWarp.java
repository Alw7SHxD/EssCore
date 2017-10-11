package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssWarps;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */

public class ComSetWarp extends EssWarps implements CommandExecutor, messages {
    private Core core;
    private EssWarps warpAPI;

    ComSetWarp(Core core) {
        super(core);
        this.core = core;
        this.warpAPI = new EssWarps(core);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(m_not_player);
            return true;
        }

        if (!EssAPI.hasPermission(commandSender, "esscore.setwarp")) return true;

        Player sender = (Player) commandSender;
        if (strings.length == 1) {
            if (create(strings[0].replace(".", "-"), sender.getLocation(), true))
                commandSender.sendMessage(EssAPI.color(String.format(m_setwarp_created, strings[0].replace(".", "-").toLowerCase())));
            else commandSender.sendMessage(EssAPI.color(m_setwarp_exists));
        } else if (strings.length == 2 && strings[1].equalsIgnoreCase("-p")) {
            if (create(strings[0].replace(".", "-"), sender.getLocation(), false))
                commandSender.sendMessage(EssAPI.color(String.format(m_setwarp_created, strings[0].replace(".", "-").toLowerCase())));
            else commandSender.sendMessage(EssAPI.color(m_setwarp_exists));
        } else commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9<name>")));
        return true;
    }
}
