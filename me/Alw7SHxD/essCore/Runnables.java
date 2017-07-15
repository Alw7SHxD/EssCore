package me.Alw7SHxD.essCore;

import me.Alw7SHxD.essCore.API.EssAPI;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class Runnables {
    private Core core;

    public Runnables(Core core) {
        this.core = core;
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
}
