package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.messages;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            if (strings.length == 0) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(messages.m_not_player);
                    return true;
                }

                hasAccount(((Player) commandSender));
                Double balance = core.getEssEconomy().getBalance(((Player) commandSender));
                commandSender.sendMessage(EssAPI.color(String.format("&7You have &c&l%s &7%s", balance(balance), replace(balance))));
            } else if (strings.length == 1) {
                Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
                if (target == null) return true;

                hasAccount(target);
                Double balance = core.getEssEconomy().getBalance(((Player) commandSender));
                commandSender.sendMessage(EssAPI.color(String.format("&c&l%s &7has &c&l%s &7%s", target.getName(), balance(balance), replace(balance))));
            } else commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9[Player]")));
        } else {
            if (strings.length == 0) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage(messages.m_not_player);
                    return true;
                }

                hasAccount(((Player) commandSender));
                Double balance = core.getEssEconomy().getBalance(((Player) commandSender));
                commandSender.sendMessage(EssAPI.color(String.format("&7Your balance is &c&l%s%s", symbol(), balance(balance))));
            } else if (strings.length == 1) {
                Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
                if (target == null) return true;

                hasAccount(target);
                Double balance = core.getEssEconomy().getBalance(((Player) commandSender));
                commandSender.sendMessage(EssAPI.color(String.format("&c&l%s&7's balance is &c&l%s%s", target.getName(), symbol(), balance(balance))));
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
        return (v == Math.floor(v)) && !Double.isInfinite(v) ? Math.floor(v) + "" : v + "";
    }
}
