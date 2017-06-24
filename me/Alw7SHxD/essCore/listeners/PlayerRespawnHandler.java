package me.Alw7SHxD.essCore.listeners;

import me.Alw7SHxD.essCore.API.EssSpawnAPI;
import me.Alw7SHxD.essCore.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class PlayerRespawnHandler implements Listener {
    private Core core;
    private EssSpawnAPI spawnAPI;

    public PlayerRespawnHandler(Core core) {
        this.core = core;
        this.spawnAPI = new EssSpawnAPI(core);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        if(core.getConfig().getBoolean("spawn-teleport.player-respawn"))
            e.setRespawnLocation(spawnAPI.getLocation());
            //spawnAPI.teleport(e.getPlayer());
    }
}
