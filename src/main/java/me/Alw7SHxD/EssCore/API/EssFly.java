package me.Alw7SHxD.EssCore.API;

import com.sun.istack.internal.NotNull;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
public class EssFly {
    private EssPlayer playerAPI;
    private Player player;
    private JavaPlugin plugin;

    public EssFly(@NotNull JavaPlugin plugin, @NotNull Player player) {
        this.playerAPI = new EssPlayer(player);
        this.player = player;
        this.plugin = plugin;
    }

    private boolean fly() {
        if (playerAPI.toggle("flight")) {
            player.setAllowFlight(true);
            return true;
        } else {
            player.setAllowFlight(false);
            return false;
        }
    }

    public void eFly() {
        if (fly())
            player.sendMessage(EssAPI.color(messages.m_fly_self_on));
        else
            player.sendMessage(EssAPI.color(messages.m_fly_self_off));
    }

    public void eFly(@NotNull CommandSender sender) {
        Boolean f = fly();
        if (f) {
            player.sendMessage(EssAPI.color(String.format(messages.m_fly_target_on, EssAPI.getSenderDisplayName(plugin, sender))));
        } else {
            player.sendMessage(EssAPI.color(String.format(messages.m_fly_target_off, sender.getName())));
        }

        if(sender != this.player)
            sender.sendMessage(EssAPI.color(String.format(messages.m_fly_sender, player.getName(), f ? "&a&lenabled" : "&c&ldisabled")));
    }
}
