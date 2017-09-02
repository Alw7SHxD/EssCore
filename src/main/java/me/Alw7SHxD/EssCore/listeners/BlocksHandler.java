package me.Alw7SHxD.EssCore.listeners;

import me.Alw7SHxD.EssCore.API.Players;
import me.Alw7SHxD.EssCore.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class BlocksHandler implements Listener {
    private Core core;
    private Players playerAPI;

    public BlocksHandler(Core core) {
        this.core = core;
    }

    private void getPlayer(Player player) {
        this.playerAPI = new Players(player);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        getPlayer(e.getPlayer());
        if (playerAPI.getFrozen()) e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBrake(BlockBreakEvent e) {
        getPlayer(e.getPlayer());
        if (playerAPI.getFrozen()) e.setCancelled(true);
    }
}
