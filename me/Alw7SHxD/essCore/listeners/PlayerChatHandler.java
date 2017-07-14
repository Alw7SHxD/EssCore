package me.Alw7SHxD.essCore.listeners;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.API.EssPlayerAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.messages;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.UnknownFormatConversionException;

/*
 * (C) Copyright 2017 Alw7SHxD.
 *
 * essCore is licensed under the Apache License, Version 2.0 (the "License");
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
public class PlayerChatHandler implements Listener, messages {
    private Core core;

    PlayerChatHandler(Core core) {
        this.core = core;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        EssPlayerAPI playerAPI = new EssPlayerAPI(e.getPlayer());
        if (playerAPI.getMuted()) {
            e.getPlayer().sendMessage(EssAPI.color(m_muted));
            e.setCancelled(true);
        }

        if(core.lists.getPlayerPayTransaction().containsKey(e.getPlayer().getUniqueId())){
            if(e.getMessage().equalsIgnoreCase("yes")){
                HashMap<UUID, Double> hashMap = core.lists.getPlayerPayTransaction().get(e.getPlayer().getUniqueId());
                UUID targetUUID = null;
                for(UUID uuid: hashMap.keySet())
                    targetUUID = uuid;
                core.getEssEconomy().withdrawPlayer(e.getPlayer(), hashMap.get(targetUUID));
                core.getEssEconomy().depositPlayer(core.getServer().getOfflinePlayer(targetUUID), hashMap.get(targetUUID));
            }else if(e.getMessage().equalsIgnoreCase("no"))
                e.getPlayer().sendMessage(EssAPI.color(m_pay_confirm_decline));
            else
                e.getPlayer().sendMessage(EssAPI.color(m_economy_transaction_fail));
            core.lists.removePlayerPayTransaction(e.getPlayer().getUniqueId());
            e.setCancelled(true);
        }

        if (e.getPlayer().hasPermission("esscore.chat.color") || core.getConfig().getBoolean("allow-color-codes")) {
            e.setMessage(EssAPI.color(e.getMessage()));
        }

        if (core.getConfig().getBoolean("chat-format.enabled")) {
            try {
                if (core.usingPlaceholderAPI) {
                    String s1 = core.getConfig().getString("chat-format.default-format");
                    String s2 = PlaceholderAPI.setPlaceholders(e.getPlayer(), s1);
                    s2 = s2.replaceAll("%", "%%");
                    e.setFormat(EssAPI.color(localph(playerAPI, e.getPlayer(), s2)));
                } else {
                    String s1 = core.getConfig().getString("chat-format.default-format");
                    s1 = s1.replaceAll("%", "%%");
                    e.setFormat(EssAPI.color(localph(playerAPI, e.getPlayer(), s1)));
                }
            } catch (UnknownFormatConversionException ex) {
                core.getLogger().warning("An error occurred! please make sure that you use proper/valid placeholders in the 'chat-format' section inside of config.yml");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String localph(EssPlayerAPI playerAPI, Player player, String s){
        s = s.replace("{DISPLAYNAME}", playerAPI.getNick() ? core.getConfig().getString("nickname-prefix") + playerAPI.getNickname() : player.getName());
        s = s.replace("{MESSAGE}", "%2$s");
        return s;
    }
}
