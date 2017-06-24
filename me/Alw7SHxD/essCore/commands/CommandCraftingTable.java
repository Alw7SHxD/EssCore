package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class CommandCraftingTable implements CommandExecutor, messages {
    private Core core;

    public CommandCraftingTable(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(m_not_player);
            return true;
        }

        if(!EssAPI.hasPermission(commandSender, "esscore.workbench")) return true;

        ((Player) commandSender).openWorkbench(((Player) commandSender).getLocation(), true);
        return true;
    }
}
