package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssSpawn;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class CommandSetSpawn extends EssSpawn implements CommandExecutor, messages {
    private Core core;

    public CommandSetSpawn(Core core) {
        super(core);
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(m_not_player);
            return true;
        }

        if (!EssAPI.hasPermission(commandSender, "esscore.setspawn")) return true;
        if (strings.length != 0) {
            commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s)));
            return true;
        }

        try {
            set(((Player) commandSender).getLocation());
            commandSender.sendMessage(EssAPI.color(m_setspawn));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
