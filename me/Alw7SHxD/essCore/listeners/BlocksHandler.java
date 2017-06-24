package me.Alw7SHxD.essCore.listeners;

import me.Alw7SHxD.essCore.API.EssPlayerAPI;
import me.Alw7SHxD.essCore.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class BlocksHandler implements Listener {
    private Core core;
    private EssPlayerAPI playerAPI;

    public BlocksHandler(Core core) {
        this.core = core;
    }

    private void getPlayer(Player player) {
        this.playerAPI = new EssPlayerAPI(player);
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
