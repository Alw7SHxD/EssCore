package me.Alw7SHxD.EssCore.listeners;

import me.Alw7SHxD.EssCore.API.EssPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class BlocksHandler implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (getPlayer(e.getPlayer()).getFrozen())
            e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBrake(BlockBreakEvent e) {
        if (getPlayer(e.getPlayer()).getFrozen())
            e.setCancelled(true);
    }

    private EssPlayer getPlayer(Player player) {
        return new EssPlayer(player);
    }
}
