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
public class CommandWarps implements CommandExecutor, messages {
    private Core core;
    private EssWarpAPI warpAPI;

    public CommandWarps(Core core) {
        this.core = core;
        this.warpAPI = new EssWarpAPI(core);
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
