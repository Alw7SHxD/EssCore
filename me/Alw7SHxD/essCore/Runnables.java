package me.Alw7SHxD.essCore;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.API.EssPlayerAPI;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class Runnables {
    private Core core;
    private BukkitTask asyncFiveMinutes;

    public Runnables(Core core) {
        this.core = core;
    }

    public void asyncFiveMinutes() {
        this.asyncFiveMinutes = new BukkitRunnable() {
            @Override
            public void run() {
                if (!core.hookedWithVault && (core.lists.getPlayerBank().isEmpty() || core.lists.getPlayerBank().size() == 0) && core.getServer().getOnlinePlayers().size() == 0)
                    return;

                for (Player player : core.getServer().getOnlinePlayers()) {
                    EssPlayerAPI playerAPI = new EssPlayerAPI(player);
                    playerAPI.setLocalBalance(playerAPI.getBalance());
                }
            }
        }.runTaskTimerAsynchronously(core, 0, 6000);
    }

    public void asyncOneSecond() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (core.lists.getPlayerPayTransactionTime().size() == 0 || core.getServer().getOnlinePlayers().size() == 0)
                    return;

                for (UUID uuid : core.lists.getPlayerPayTransactionTime().keySet()) {
                    int time = core.lists.getPlayerPayTransactionTime().get(uuid);

                    for (UUID uuid1 : core.lists.getPlayerPayTransaction().get(uuid).keySet()) {
                        if (time <= 1 || !core.getServer().getOfflinePlayer(uuid1).isOnline()) {
                            core.getServer().getPlayer(uuid).sendMessage(EssAPI.color(messages.m_economy_transaction_fail));
                            core.lists.getPlayerPayTransaction().remove(uuid);
                            core.lists.getPlayerPayTransactionTime().remove(uuid);
                        } else
                            core.lists.getPlayerPayTransactionTime().put(uuid, time - 1);
                    }
                }
            }
        }.runTaskTimerAsynchronously(core, 0, 20);
    }

    public BukkitTask getAsyncFiveMinutes() {
        return asyncFiveMinutes;
    }
}
