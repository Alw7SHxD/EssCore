package me.Alw7SHxD.essCore;

import me.Alw7SHxD.essCore.API.EssPlayerAPI;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class Runnables {
    private Core core;

    public Runnables(Core core) {
        this.core = core;
    }

    public void asynFiveMinutes(){
        new BukkitRunnable(){
            @Override
            public void run() {
                if(!core.hookedWithVault && (core.lists.getPlayerBank().isEmpty() || core.lists.getPlayerBank().size() == 0) && core.getServer().getOnlinePlayers().size() == 0) return;

                for(Player player: core.getServer().getOnlinePlayers()){
                    EssPlayerAPI playerAPI = new EssPlayerAPI(player);
                    playerAPI.setLocalBalance(playerAPI.getBalance());
                }
            }
        }.runTaskTimerAsynchronously(core, 0, 6000);
    }
}
