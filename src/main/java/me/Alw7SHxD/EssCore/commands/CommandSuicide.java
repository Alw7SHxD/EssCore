package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class CommandSuicide implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!EssAPI.hasPermission(commandSender, "esscore.suicide")) return true;
        if(strings.length != 0){
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s)));
            return true;
        }

        ((Player) commandSender).setHealth(0);
        commandSender.sendMessage(EssAPI.color("&7Death, death, death. Here it comes!"));
        return true;
    }
}
