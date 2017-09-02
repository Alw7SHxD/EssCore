package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.Players;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.messages;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class CommandBalance implements CommandExecutor {
    private Core core;
    private boolean t;

    public CommandBalance(Core core, boolean type) {
        this.core = core;
        this.t = type;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!core.hookedWithVault) {
            commandSender.sendMessage(EssAPI.color(messages.m_vault_unavailable));
            return true;
        }

        if (t) {
            if (!EssAPI.hasPermission(commandSender, "esscore.balance")) return true;
            if (strings.length == 0) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(messages.m_not_player);
                    return true;
                }

                hasAccount(((Player) commandSender));
                Double balance = core.getEssEconomy().getBalance(((Player) commandSender));
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_balance_self, balance(balance), replace(balance))));
            } else if (strings.length == 1) {
                if (!EssAPI.hasPermission(commandSender, "esscore.balance.target")) return true;
                OfflinePlayer target = core.getServer().getOfflinePlayer(strings[0]);

                if (!hasAccount(target)) {
                    commandSender.sendMessage(EssAPI.color(messages.m_player_doesnt_exist));
                    return true;
                }
                Double balance = core.getEssEconomy().getBalance(target);
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_balance_target, target.getName(), balance(balance), replace(balance))));
            } else commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9[Player]")));
        } else {
            if (!EssAPI.hasPermission(commandSender, "esscore.money")) return true;
            if (strings.length == 0) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(messages.m_not_player);
                    return true;
                }

                hasAccount(((Player) commandSender));
                Double balance = core.getEssEconomy().getBalance(((Player) commandSender));
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_money_self, symbol(), balance(balance))));
            } else if (strings.length == 1) {
                if (!EssAPI.hasPermission(commandSender, "esscore.money.targetrhg9jhb")) return true;
                OfflinePlayer target = core.getServer().getOfflinePlayer(strings[0]);

                if (!hasAccount(target)) {
                    commandSender.sendMessage(EssAPI.color(messages.m_player_doesnt_exist));
                    return true;
                }
                Double balance = core.getEssEconomy().getBalance(target);
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_money_target, target.getName(), symbol(), balance(balance))));
            } else commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9[Player]")));
        }
        return true;
    }

    private boolean hasAccount(OfflinePlayer player) {
        if (!core.getEssEconomy().hasAccount(player)) {
            if (player.isOnline()) {
                new Players(core.getServer().getPlayer(player.getUniqueId())).setDefaultBalance();
                return true;
            }
        } else return true;
        return false;
    }

    private String replace(double v) {
        return v == 1 ? core.getEssEconomy().currencyNameSingular() : core.getEssEconomy().currencyNamePlural();
    }

    private String symbol() {
        return core.getConfigCache().getString("cuf.symbol") != null ? core.getConfigCache().getString("cuf.symbol") : "$";
    }

    private String balance(double v) {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        return decimalFormat.format(v);
    }
}
