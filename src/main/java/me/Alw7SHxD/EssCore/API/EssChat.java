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
public class EssChat {
    public static class Clear {
        private JavaPlugin plugin;

        public Clear(JavaPlugin plugin) {
            this.plugin = plugin;
        }

        private void cc() {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (player.hasPermission("esscore.clearchat.ignore")) continue;
                for (int i = 0; i < 127; i++) {
                    player.sendMessage("\n");
                }
            }
        }

        private void cc(Player target) {
            if (target.hasPermission("esscore.clearchat.ignore")) return;
            for (int i = 0; i < 127; i++) {
                target.sendMessage("\n");
            }
        }

        public void clearChat() {
            cc();
            plugin.getServer().broadcastMessage("\n" + EssAPI.color(messages.m_cc_global_a));
        }

        public void clearChat(CommandSender executor) {
            cc();
            plugin.getServer().broadcastMessage("\n" + EssAPI.color(String.format(messages.m_cc_global, EssAPI.getSenderDisplayName(plugin, executor))));
        }

        public void clearChat(CommandSender executor, Player target) {
            cc(target);
            target.sendMessage("\n" + EssAPI.color(String.format(messages.m_cc_target, EssAPI.getSenderDisplayName(plugin, executor))));
        }

        public void clearChat(Player target) {
            cc(target);
            target.sendMessage("\n" + EssAPI.color(messages.m_cc_target_a));
        }
    }

    public static class Mute {
        private EssPlayer playerAPI;
        private Player player;
        private JavaPlugin plugin;

        public Mute(@NotNull JavaPlugin plugin, @NotNull Player player) {
            this.playerAPI = new EssPlayer(player);
            this.player = player;
            this.plugin = plugin;
        }

        private boolean mute() {
            if (playerAPI.getMuted())
                return false;
            else {
                playerAPI.setMuted(true);
                return true;
            }
        }

        private boolean unMute() {
            if (playerAPI.getMuted()) {
                playerAPI.setMuted(false);
                return true;
            } else return false;
        }

        public void eMute() {
            if (!mute()) return;
            player.sendMessage(EssAPI.color(messages.m_mute_target_a));
        }

        public void eMute(CommandSender sender) {
            if (!mute()) {
                sender.sendMessage(EssAPI.color(messages.m_muted_sender));
                return;
            }

            player.sendMessage(EssAPI.color(String.format(messages.m_mute_target, EssAPI.getSenderDisplayName(plugin, sender))));
            if (sender != player)
                sender.sendMessage(EssAPI.color(String.format(messages.m_mute_sender, player.getName())));
        }

        public void eUnMute() {
            if (!unMute()) return;
            player.sendMessage(EssAPI.color(messages.m_unmute_target_a));
        }

        public void eUnMute(CommandSender sender) {
            if (!unMute()) {
                sender.sendMessage(EssAPI.color(messages.m_unmuted_sender));
                return;
            }

            player.sendMessage(EssAPI.color(String.format(messages.m_unmute_target, EssAPI.getSenderDisplayName(plugin, sender))));
            if (sender != player)
                sender.sendMessage(EssAPI.color(String.format(messages.m_unmute_sender, player.getName())));
        }
    }
}
