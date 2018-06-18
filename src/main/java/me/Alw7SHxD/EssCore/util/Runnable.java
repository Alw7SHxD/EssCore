package me.Alw7SHxD.EssCore.util;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class Runnable {
    private Core core;

    public Runnable(Core core) {
        this.core = core;
    }

    public void asyncOneSecond() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    if (core.Lists.getPlayerPayTransactionTime().size() == 0 || core.getServer().getOnlinePlayers().size() == 0)
                        return;

                    for (UUID uuid : core.Lists.getPlayerPayTransactionTime().keySet()) {
                        int time = core.Lists.getPlayerPayTransactionTime().get(uuid);

                        for (UUID uuid1 : core.Lists.getPlayerPayTransaction().get(uuid).keySet()) {
                            if (time <= 1 || !core.getServer().getOfflinePlayer(uuid1).isOnline()) {
                                core.getServer().getPlayer(uuid).sendMessage(EssAPI.color(messages.m_economy_transaction_fail));
                                core.Lists.getPlayerPayTransaction().remove(uuid);
                                core.Lists.getPlayerPayTransactionTime().remove(uuid);
                            } else
                                core.Lists.getPlayerPayTransactionTime().put(uuid, time - 1);
                        }
                    }
                } catch (NullPointerException e) {
                    // ignored.
                }
            }
        }.runTaskTimerAsynchronously(core, 0, 20);
    }
}
