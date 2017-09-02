package me.Alw7SHxD.EssCore.API;

import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.lists;
import me.Alw7SHxD.EssCore.messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class Vanish {
    private JavaPlugin plugin;
    private Player player;
    private Players playerAPI;
    private lists lists;

    public Vanish(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        this.playerAPI = new Players(player);
    }

    public Vanish(Core core, Player player) {
        this.plugin = core;
        this.player = player;
        this.playerAPI = new Players(player);
        this.lists = new lists(core);
    }

    private boolean vanish(){
        lists.loadVanishedPlayers();
        if(playerAPI.toggle("vanished")){
            playerAPI.setVanished(true);
            lists.addVanishedPlayers(player);
            for(Player p: plugin.getServer().getOnlinePlayers()){
                p.hidePlayer(player);
            }
            return true;
        }else{
            playerAPI.setVanished(false);
            lists.removeVanishedPlayers(player);
            for(Player p: plugin.getServer().getOnlinePlayers()){
                p.showPlayer(player);
            }
            return false;
        }
    }

    public void eVanish(){
        if(vanish())
            player.sendMessage(EssAPI.color(messages.m_vanish_self_on));
        else
            player.sendMessage(EssAPI.color(messages.m_vanish_self_off));
    }

    public void eVanish(CommandSender commandSender){
        boolean v = vanish();
        if(v)
            player.sendMessage(EssAPI.color(String.format(messages.m_vanish_target_on, EssAPI.getSenderDisplayName(plugin, commandSender))));
        else
            player.sendMessage(EssAPI.color(String.format(messages.m_vanish_target_off, EssAPI.getSenderDisplayName(plugin, commandSender))));

        if(commandSender != player)
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_vanish_sender, player.getName(), v ? "&a&lenabled" : "&c&ldisabled")));
    }
}
