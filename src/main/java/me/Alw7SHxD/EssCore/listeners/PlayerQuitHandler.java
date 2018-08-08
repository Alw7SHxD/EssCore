package me.Alw7SHxD.EssCore.listeners;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.Lists;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class PlayerQuitHandler implements Listener {
    private Core core;
    private Lists Lists;

    PlayerQuitHandler(Core core) {
        this.core = core;
        this.Lists = new Lists(core);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (core.getConfigCache().getBoolean("hm.leave") || e.getPlayer().hasPermission("esscore.silent"))
            e.setQuitMessage("");

        if (!core.getConfigCache().getString("cm.leave").isEmpty() && (!core.getConfigCache().getBoolean("hm.leave") || !e.getPlayer().hasPermission("esscore.silent")))
            e.setQuitMessage(EssAPI.color(core.getConfigCache().getString("cm.leave").replaceAll("%name%", e.getPlayer().getDisplayName())));

        Lists.loadVanishedPlayers();
        if(Lists.getVanishedPlayers().contains(e.getPlayer()) && e.getPlayer().hasPermission("esscore.vanish.silent"))
            e.setQuitMessage("");
    }
}
