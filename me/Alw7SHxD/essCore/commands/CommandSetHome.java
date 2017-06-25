package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.API.EssHomesAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class CommandSetHome implements CommandExecutor, messages {
    private Core core;

    public CommandSetHome(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(m_not_player);
            return true;
        }

        if (!EssAPI.hasPermission(commandSender, "esscore.sethome")) return true;
        if (strings.length != 1) {
            commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9<name>")));
            return true;
        }

        EssHomesAPI homesAPI = new EssHomesAPI((Player) commandSender);

        int i = 0;

        for (String key : core.lists.getLimitHomes()) {
            if (commandSender.hasPermission("esscore.sethome." + key))
                if (homesAPI.listAll().toArray().length >= core.getConfig().getInt("limit-homes." + key)) {
                    i = -1;
                    break;
                } else {
                    i = 1;
                    break;
                }
        }

        if (!commandSender.hasPermission("esscore.sethome.*")) {
            if (i == -1) {
                commandSender.sendMessage(EssAPI.color(m_sethome_limit));
                return true;
            } else if (i == 0) {
                if (homesAPI.listAll().toArray().length >= 1) {
                    commandSender.sendMessage(EssAPI.color(m_sethome_limit));
                    return true;
                }
            }
        }

        commandSender.sendMessage(homesAPI.set(strings[0].replace(".", "-").toLowerCase(), ((Player) commandSender).getLocation()) ? EssAPI.color(String.format(m_sethome_success, strings[0].replace(".", "-").toLowerCase())) : EssAPI.color(m_sethome_exists));
        return true;
    }
}
