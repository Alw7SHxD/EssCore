package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.Warps;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class CommandDelWarp extends Warps implements CommandExecutor, messages {
    public CommandDelWarp(Core core) {
        super(core);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.delwarp")) return true;
        if (strings.length != 1) {
            commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9<name>")));
            return true;
        }

        if (remove(strings[0].replace(".", "-")))
            commandSender.sendMessage(EssAPI.color(String.format(m_delwarp_removed, strings[0].replace(".", "-").toLowerCase())));
        else commandSender.sendMessage(EssAPI.color(m_delwarp_failed));
        return true;
    }
}
