package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.API.EssWarpAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class CommandDelWarp extends EssWarpAPI implements CommandExecutor, messages {
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
