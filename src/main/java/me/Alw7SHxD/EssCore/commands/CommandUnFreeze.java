package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssPlayer;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class CommandUnFreeze implements CommandExecutor, messages {
    private Core core;

    CommandUnFreeze(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.unfreeze")) return true;
        if (strings.length != 1) {
            commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9<Player>")));
            return true;
        }


        Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
        if (target == null) return true;

        EssPlayer playerAPI = new EssPlayer(target);

        if (!playerAPI.getFrozen()) {
            commandSender.sendMessage(EssAPI.color(m_unfreeze_unfrozen));
            return true;
        }

        playerAPI.setFrozen(false);
        target.sendMessage(EssAPI.color(String.format(m_unfreeze_target, EssAPI.getSenderDisplayName(core, commandSender))));
        if (commandSender != target)
            commandSender.sendMessage(EssAPI.color(String.format(m_unfreeze_sender, target.getName())));
        return true;
    }
}
