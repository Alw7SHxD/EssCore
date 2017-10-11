package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class ComFeed implements CommandExecutor, messages {
    private Core core;

    public ComFeed(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.feed")) return true;
        if (strings.length == 0) {
            if(!(commandSender instanceof Player)){
                commandSender.sendMessage(m_not_player);
                return true;
            }

            ((Player) commandSender).setFoodLevel(20);
            commandSender.sendMessage(EssAPI.color(m_feed_self));
        } else if (strings.length == 1) {
            if(strings[0].equalsIgnoreCase("-all")){
                if(!EssAPI.hasPermission(commandSender, "esscore.feed.all")) return true;
                for(Player p : core.getServer().getOnlinePlayers()){
                    p.setFoodLevel(20);
                    p.sendMessage(EssAPI.color(String.format(m_feed_target, EssAPI.getSenderDisplayName(core, commandSender))));
                }
                commandSender.sendMessage(EssAPI.color(m_feed_sender_all));
                return true;
            }
            if(!EssAPI.hasPermission(commandSender, "esscore.feed.target")) return true;

            Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
            if(target == null) return true;

            target.setFoodLevel(20);

            if(commandSender != target){
                target.sendMessage(EssAPI.color(String.format(m_feed_target, EssAPI.getSenderDisplayName(core, commandSender))));
                commandSender.sendMessage(EssAPI.color(String.format(m_feed_sender, target.getName())));
            }else commandSender.sendMessage(EssAPI.color(m_feed_self));
        } else commandSender.sendMessage(EssAPI.color(String.format(m_syntax_error_c, s + " &9[Player]")));
        return true;
    }
}
