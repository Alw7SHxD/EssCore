package me.Alw7SHxD.essCore.commands;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class CommandEconomy implements CommandExecutor {
    private Core core;

    public CommandEconomy(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.eco")) return true;
        if (strings.length >= 1) {
            try {
                if (strings[0].equalsIgnoreCase("set")) {
                    if (strings.length != 3) {
                        commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " set &9<Player> <Balance>")));
                        return true;
                    }

                    Player target = EssAPI.getPlayer(core, commandSender, strings[1]);
                    if (target == null) return true;
                    Double balance = Double.parseDouble(strings[2]);
                    hasAccount(target);
                    core.getEssEconomy().setPlayer(target, balance);
                    commandSender.sendMessage(EssAPI.color(String.format(messages.m_eco_set_sender, target.getName(), balance)));
                    if(commandSender != target)
                        target.sendMessage(EssAPI.color(String.format(messages.m_eco_set_target, balance)));
                } else if (strings[0].equalsIgnoreCase("give")) {
                    if (strings.length != 3) {
                        commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " give &9<Player> <Amount>")));
                        return true;
                    }

                    Player target = EssAPI.getPlayer(core, commandSender, strings[1]);
                    if (target == null) return true;
                    Double amount = Double.parseDouble(strings[2]);
                    hasAccount(target);
                    core.getEssEconomy().depositPlayer(target, amount);
                    commandSender.sendMessage(EssAPI.color(String.format(messages.m_eco_give_sender, amount, target.getName())));
                    if(commandSender != target)
                        target.sendMessage(EssAPI.color(String.format(messages.m_eco_give_target, amount, core.getEssEconomy().getBalance(target))));
                } else if (strings[0].equalsIgnoreCase("take")) {
                    if (strings.length != 3) {
                        commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " take &9<Player> <Amount>")));
                        return true;
                    }

                    Player target = EssAPI.getPlayer(core, commandSender, strings[1]);
                    if (target == null) return true;
                    Double amount = Double.parseDouble(strings[2]);
                    hasAccount(target);
                    core.getEssEconomy().withdrawPlayer(target, amount);
                    commandSender.sendMessage(EssAPI.color(String.format(messages.m_eco_take_sender, amount, target.getName())));
                    if(commandSender != target)
                        target.sendMessage(EssAPI.color(String.format(messages.m_eco_take_target, amount, core.getEssEconomy().getBalance(target))));
                } else if (strings[0].equalsIgnoreCase("reset")) {
                    if (strings.length != 2) {
                        commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " reset &9<Player>")));
                        return true;
                    }

                    Player target = EssAPI.getPlayer(core, commandSender, strings[1]);
                    if (target == null) return true;
                    core.getEssEconomy().setPlayer(target, core.getConfig().get("starting-balance") != null ? core.getConfig().getDouble("starting-balance") : 0);
                    commandSender.sendMessage(EssAPI.color(String.format(messages.m_eco_reset_sender, target.getName())));
                    if(commandSender != target)
                        target.sendMessage(EssAPI.color(messages.m_eco_reset_target));
                } else commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9<set/give/take/reset>")));
            } catch (NumberFormatException e) {
                commandSender.sendMessage(EssAPI.color("&7Please make sure to type a correct number."));
            }
        } else commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9<set/give/take/reset>")));
        return true;
    }

    private void hasAccount(Player player) {
        if (!core.getEssEconomy().hasAccount(player))
            core.lists.getPlayerBank().put(player.getUniqueId(), core.getConfig().get("starting-balance") != null ? core.getConfig().getDouble("starting-balance") : 0);
    }
}
