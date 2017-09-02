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
public class CommandWarps implements CommandExecutor, messages {
    private Core core;
    private Warps warpAPI;

    public CommandWarps(Core core) {
        this.core = core;
        this.warpAPI = new Warps(core);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.warps")) return true;
        if (strings.length > 0) {
            commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s)));
            return true;
        }

        warpAPI.list(commandSender);
        return true;
    }
}
