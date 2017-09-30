package me.Alw7SHxD.EssCore.API;

import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.ChatColor;
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
public class EssAPI {
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String stripColor(String s) {
        return ChatColor.stripColor(s);
    }

    public static String removeAltColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();

        for(int i = 0; i < b.length - 1; ++i) {
            if(b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = 167;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

    public static Player getPlayer(JavaPlugin plugin, CommandSender sender, String player) {
        Player p = plugin.getServer().getPlayerExact(player);
        if (p != null) return p;
        else sender.sendMessage(color(messages.m_target_offline));
        return null;
    }

    public static boolean hasPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(color(messages.m_no_permission));
            return false;
        }
        return true;
    }

    public static String getSenderDisplayName(JavaPlugin plugin, CommandSender sender){
        return !(sender instanceof Player) ? sender.getName() : getPlayer(plugin, sender, sender.getName()).getDisplayName();
    }
}
