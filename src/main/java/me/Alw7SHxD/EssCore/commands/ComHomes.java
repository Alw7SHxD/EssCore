package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssHomes;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class ComHomes implements CommandExecutor, messages {
    private Core core;

    public ComHomes(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(m_not_player);
            return true;
        }

        if(!EssAPI.hasPermission(commandSender, "esscore.homes")) return true;
        EssHomes essHomesAPI = new EssHomes((Player) commandSender);
        essHomesAPI.list();
        return true;
    }
}
