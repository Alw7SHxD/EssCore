package me.Alw7SHxD.essCore.listeners;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.API.EssPlayerAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.lists;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class PlayerQuitHandler implements Listener {
    private Core core;
    private lists lists;

    PlayerQuitHandler(Core core) {
        this.core = core;
        this.lists = new lists(core);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(e.getPlayer());

        if (core.getConfig().getBoolean("hide-messages.leave") || e.getPlayer().hasPermission("esscore.silent"))
            e.setQuitMessage("");

        if (!core.getConfig().getString("custom-messages.leave").isEmpty() && (!core.getConfig().getBoolean("hide-messages.leave") || !e.getPlayer().hasPermission("esscore.silent")))
            e.setQuitMessage(EssAPI.color(core.getConfig().getString("custom-messages.leave").replaceAll("%name%", e.getPlayer().getDisplayName())));

        lists.loadVanishedPlayers();
        if(lists.getVanishedPlayers().contains(e.getPlayer()) && e.getPlayer().hasPermission("esscore.vanish.silent"))
            e.setQuitMessage("");

        if(core.hookedWithVault)
            playerAPI.setLocalBalance(playerAPI.getBalance());
    }
}
