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
public class ComSetHome implements CommandExecutor, messages {
    private Core core;

    public ComSetHome(Core core) {
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

        EssHomes essHomesAPI = new EssHomes((Player) commandSender);

        int i = 0;

        for (String key : core.Lists.getLimitHomes()) {
            if (commandSender.hasPermission("esscore.sethome." + key)) {
                if (essHomesAPI.listAll() != null && essHomesAPI.listAll().size() >= core.getConfig().getInt("limit-homes." + key)) {
                    i = -1;
                    break;
                } else {
                    i = 1;
                    break;
                }
            }
        }

        if (!commandSender.hasPermission("esscore.sethome.*")) {
            if (i == -1) {
                commandSender.sendMessage(EssAPI.color(m_sethome_limit));
                return true;
            } else if (i == 0) {
                if (essHomesAPI.listAll().size() >= 1) {
                    commandSender.sendMessage(EssAPI.color(m_sethome_limit));
                    return true;
                }
            }
        }

        commandSender.sendMessage(essHomesAPI.set(strings[0].replace(".", "-").toLowerCase(), ((Player) commandSender).getLocation()) ? EssAPI.color(String.format(m_sethome_success, strings[0].replace(".", "-").toLowerCase())) : EssAPI.color(m_sethome_exists));
        return true;
    }
}
