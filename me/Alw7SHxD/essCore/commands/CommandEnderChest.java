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
public class CommandEnderChest implements CommandExecutor, messages {
    private Core core;

    public CommandEnderChest(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(m_not_player);
            return true;
        }

        if (!EssAPI.hasPermission(commandSender, "esscore.enderchest")) return true;

        if (strings.length == 0) {
            ((Player) commandSender).openInventory(((Player) commandSender).getEnderChest());
        } else if (strings.length == 1) {
            if (!EssAPI.hasPermission(commandSender, "esscore.enderchest.target")) return true;
            Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
            if (target == null) return true;

            ((Player) commandSender).openInventory(target.getEnderChest());
        } else commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s)));
        return true;
    }
}
