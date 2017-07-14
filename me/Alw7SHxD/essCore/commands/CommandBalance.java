package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.messages;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

/**
 * essCore was created by Alw7SHxD (C) 2017
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
        if (t) {
            if(!EssAPI.hasPermission(commandSender, "esscore.balance")) return true;
            if (strings.length == 0) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(messages.m_not_player);
                    return true;
                }

                hasAccount(((Player) commandSender));
                Double balance = core.getEssEconomy().getBalance(((Player) commandSender));
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_balance_self, balance(balance), replace(balance))));
            } else if (strings.length == 1) {
                if(!EssAPI.hasPermission(commandSender, "esscore.balance.target")) return true;
                Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
                if (target == null) return true;

                hasAccount(target);
                Double balance = core.getEssEconomy().getBalance(target);
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_balance_target, target.getName(), balance(balance), replace(balance))));
            } else commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9[Player]")));
        } else {
            if(!EssAPI.hasPermission(commandSender, "esscore.money")) return true;
            if (strings.length == 0) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(messages.m_not_player);
                    return true;
                }

                hasAccount(((Player) commandSender));
                Double balance = core.getEssEconomy().getBalance(((Player) commandSender));
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_money_self, symbol(), balance(balance))));
            } else if (strings.length == 1) {
                if(!EssAPI.hasPermission(commandSender, "esscore.money.target")) return true;
                Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
                if (target == null) return true;

                hasAccount(target);
                Double balance = core.getEssEconomy().getBalance(target);
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_money_target, target.getName(), symbol(), balance(balance))));
            } else commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9[Player]")));
        }
        return true;
    }

    private void hasAccount(OfflinePlayer player) {
        if (!core.getEssEconomy().hasAccount(player))
            core.lists.getPlayerBank().put(player.getUniqueId(), core.getConfig().get("starting-balance") != null ? core.getConfig().getDouble("starting-balance") : 0);
    }

    private String replace(double v) {
        return v == 1 ? core.getEssEconomy().currencyNameSingular() : core.getEssEconomy().currencyNamePlural();
    }

    private String symbol() {
        return core.getConfig().getString("currency-format.symbol") != null ? core.getConfig().getString("currency-format.symbol") : "$";
    }

    private String balance(double v) {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        return decimalFormat.format(v);
    }
}
