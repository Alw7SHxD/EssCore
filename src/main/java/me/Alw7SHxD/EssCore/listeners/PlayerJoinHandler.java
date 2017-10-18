package me.Alw7SHxD.EssCore.listeners;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssPlayer;
import me.Alw7SHxD.EssCore.API.EssSpawn;
import me.Alw7SHxD.EssCore.util.updaters.UpdateChecker;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.Lists;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/*
 * (C) Copyright 2017 Alw7SHxD.
 *
 * EssCore is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class PlayerJoinHandler implements Listener {
    private Core core;
    private UpdateChecker updateChecker;
    private Lists Lists;
    private EssSpawn spawnAPI;

    PlayerJoinHandler(Core core) {
        this.core = core;
        updateChecker = new UpdateChecker(core);
        this.Lists = new Lists(core);
        this.spawnAPI = new EssSpawn(core);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        EssPlayer playerAPI = new EssPlayer(player);
        if (player.hasPermission("esscore.notify"))
            updateChecker.check(player);

        if (core.getConfigCache().getBoolean("hm.join") || e.getPlayer().hasPermission("esscore.silent"))
            e.setJoinMessage("");
        if (!core.getConfigCache().getString("cm.join").isEmpty() && (!core.getConfigCache().getBoolean("hm.join") || !e.getPlayer().hasPermission("esscore.silent")))
            e.setJoinMessage(core.getConfig().getString("custom-messages.join").replaceAll("%name%", player.getDisplayName()));
        if (playerAPI.getFlight()) {
            player.setAllowFlight(true);
            player.sendMessage(EssAPI.color(messages.m_fly_self_on));
        }
        if (playerAPI.getNick())
            player.setDisplayName(EssAPI.color(playerAPI.getNickname()));

        if (playerAPI.l && core.getConfigCache().getBoolean("stp.player-first-join"))
            spawnAPI.teleport(player);
        else if (core.getConfigCache().getBoolean("stp.player-join"))
            spawnAPI.teleport(player);

        if(core.hookedWithVault) {
            if(playerAPI.hasLocalAccount())
                playerAPI.setBalance(playerAPI.getLocalBalance());
            core.getEssEconomy().createPlayerAccount(player);
        }

        try {
            Lists.loadVanishedPlayers();
            for (Player p : Lists.getVanishedPlayers()) {
                if (p != player)
                    player.hidePlayer(p);
            }
            if (Lists.getVanishedPlayers().contains(player)) {
                for (Player p : core.getServer().getOnlinePlayers())
                    p.hidePlayer(player);
                if (player.hasPermission("esscore.vanish.silent")) e.setJoinMessage("");
            }
        } catch (NullPointerException ex) {
            //nothing
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
