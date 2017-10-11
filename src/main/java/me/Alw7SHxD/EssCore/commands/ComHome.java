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
public class ComHome implements CommandExecutor, messages {
    private Core core;

    public ComHome(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(m_not_player);
            return true;
        }

        if (!EssAPI.hasPermission(commandSender, "esscore.home")) return true;
        if (strings.length == 1) {
            EssHomes essHomesAPI = new EssHomes((Player) commandSender);
            if (essHomesAPI.teleport(strings[0].replace(".", "-")))
                commandSender.sendMessage(EssAPI.color(m_home_teleport));
            else commandSender.sendMessage(EssAPI.color(m_home_doesnt_exist));
        } else commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9<name>")));
        return true;
    }
}
