package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssNickname;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class CommandNick implements CommandExecutor, messages {
    private Core core;

    public CommandNick(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.nickname")) return true;

        if (strings.length == 1) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(m_not_player);
                return true;
            }

            EssNickname nickAPI = new EssNickname((Player) commandSender);
            if (checkOff(nickAPI, commandSender, ((Player) commandSender), strings[0])) return true;

            nickAPI.eNick(commandSender, ((Player) commandSender), strings[0]);
        } else if (strings.length == 2) {
            if (!EssAPI.hasPermission(commandSender, "esscore.nickname.target")) return true;

            Player target = EssAPI.getPlayer(core, commandSender, strings[1]);
            if (target == null) return true;

            EssNickname nickAPI = new EssNickname(target);

            if (checkOff(nickAPI, commandSender, target, strings[0])) return true;
            nickAPI.eNick(commandSender, target, strings[0]);
        } else
            commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9<nickname|off> &7[Player]")));

        return true;
    }

    private boolean checkOff(EssNickname nickAPI, CommandSender commandSender, Player player, String s) {
        if (s.equalsIgnoreCase("off")) {
            if (!nickAPI.eUnNick(commandSender, player)) return false;
            if (commandSender == player)
                commandSender.sendMessage(EssAPI.color(m_nick_off));
            else {
                commandSender.sendMessage(EssAPI.color(String.format(m_nick_off_sender, player.getName())));
                player.sendMessage(EssAPI.color(String.format(m_nick_off_target, EssAPI.getSenderDisplayName(core, commandSender))));
            }
            return true;
        }
        return false;
    }
}
