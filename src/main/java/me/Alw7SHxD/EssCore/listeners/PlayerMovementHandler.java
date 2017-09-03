package me.Alw7SHxD.EssCore.listeners;

import me.Alw7SHxD.EssCore.API.EssPlayer;
import me.Alw7SHxD.EssCore.Core;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class PlayerMovementHandler implements Listener {
    private Core core;

    PlayerMovementHandler(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        EssPlayer playerAPI = new EssPlayer(e.getPlayer());

        if (playerAPI.getFrozen()) {
            Location from = e.getFrom();
            Location to = e.getTo();
            double x = Math.floor(from.getX());
            double y = Math.floor(from.getY());
            double z = Math.floor(from.getZ());
            if (Math.floor(to.getX()) != x || Math.floor(to.getY()) != y || Math.floor(to.getZ()) != z) {
                x += .5;
                z += .5;
                e.getPlayer().teleport(new Location(from.getWorld(), x, y, z, from.getYaw(), from.getPitch()));
            }
        }
    }
}
