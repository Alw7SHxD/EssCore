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
public class CommandHeal implements CommandExecutor, messages {
    private Core core;

    public CommandHeal(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.heal")) return true;
        if (strings.length == 0) {
            if(!(commandSender instanceof Player)){
                commandSender.sendMessage(m_not_player);
                return true;
            }

            ((Player) commandSender).setHealth(20);
            commandSender.sendMessage(EssAPI.color(m_heal_self));
        } else if (strings.length == 1) {
            if(strings[0].equalsIgnoreCase("-all")){
                if(!EssAPI.hasPermission(commandSender, "esscore.heal.all")) return true;
                for(Player p : core.getServer().getOnlinePlayers()){
                    p.setHealth(20);
                    p.sendMessage(EssAPI.color(String.format(m_heal_target, EssAPI.getSenderDisplayName(core, commandSender))));
                }
                commandSender.sendMessage(EssAPI.color(m_heal_sender_all));
                return true;
            }
            if(!EssAPI.hasPermission(commandSender, "esscore.heal.target")) return true;

            Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
            if(target == null) return true;

            target.setHealth(20);

            if(commandSender != target){
                target.sendMessage(EssAPI.color(String.format(m_heal_target, EssAPI.getSenderDisplayName(core, commandSender))));
                commandSender.sendMessage(EssAPI.color(String.format(m_heal_sender, target.getName())));
            }else commandSender.sendMessage(EssAPI.color(m_heal_self));
        } else commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9[Player]")));
        return true;
    }
}
