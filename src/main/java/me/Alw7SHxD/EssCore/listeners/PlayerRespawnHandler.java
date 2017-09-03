package me.Alw7SHxD.EssCore.listeners;

import me.Alw7SHxD.EssCore.API.EssSpawn;
import me.Alw7SHxD.EssCore.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class PlayerRespawnHandler implements Listener {
    private Core core;
    private EssSpawn spawnAPI;

    public PlayerRespawnHandler(Core core) {
        this.core = core;
        this.spawnAPI = new EssSpawn(core);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        if(core.getConfigCache().getBoolean("stp.player-respawn"))
            e.setRespawnLocation(spawnAPI.getLocation());
            //spawnAPI.teleport(e.getPlayer());
    }
}
