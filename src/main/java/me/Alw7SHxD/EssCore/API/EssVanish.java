package me.Alw7SHxD.EssCore.API;

import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.Lists;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class EssVanish {
    private JavaPlugin plugin;
    private Player player;
    private EssPlayer playerAPI;
    private Lists Lists;

    public EssVanish(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        this.playerAPI = new EssPlayer(player);
    }

    public EssVanish(Core core, Player player) {
        this.plugin = core;
        this.player = player;
        this.playerAPI = new EssPlayer(player);
        this.Lists = new Lists(core);
    }

    private boolean vanish(){
        Lists.loadVanishedPlayers();
        if(playerAPI.toggle("vanished")){
            playerAPI.setVanished(true);
            Lists.addVanishedPlayers(player);
            for(Player p: plugin.getServer().getOnlinePlayers()){
                p.hidePlayer(player);
            }
            return true;
        }else{
            playerAPI.setVanished(false);
            Lists.removeVanishedPlayers(player);
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
