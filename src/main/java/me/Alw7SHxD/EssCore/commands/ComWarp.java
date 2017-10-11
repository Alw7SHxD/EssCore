package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssWarps;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class ComWarp implements CommandExecutor, messages {
    private Core core;
    private EssWarps warpAPI;

    ComWarp(Core core) {
        this.core = core;
        this.warpAPI = new EssWarps(core);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.warp")) return true;
        if (strings.length == 1) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(m_not_player);
                return true;
            }

            warpAPI.teleport(strings[0].replace(".", "-"), (Player) commandSender);
        } else if (strings.length == 2) {
            if (!EssAPI.hasPermission(commandSender, "esscore.warp.target")) return true;

            Player target = EssAPI.getPlayer(core, commandSender, strings[1]);
            if (target == null) return true;

            warpAPI.teleport(strings[0].replace(".", "-"), commandSender, target);
        } else commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9<name>")));
        return true;
    }
}
