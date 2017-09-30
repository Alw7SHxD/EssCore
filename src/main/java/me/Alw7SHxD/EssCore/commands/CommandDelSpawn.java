package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssSpawn;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class CommandDelSpawn extends EssSpawn implements CommandExecutor, messages {
    public CommandDelSpawn(Core core) {
        super(core);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.delspawn")) return true;
        if (strings.length != 0) {
            commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s)));
            return true;
        }

        if (delete()) commandSender.sendMessage(EssAPI.color(m_delspawn));
        else commandSender.sendMessage(EssAPI.color(m_delspawn_fail));
        return true;
    }
}
