package me.Alw7SHxD.essCore.util.updaters;

import com.sun.istack.internal.NotNull;
import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

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
public class UpdateChecker {
    private Core core;

    public UpdateChecker(Core core) {
        this.core = core;
    }

    private String getLatestVersion() {
        try {
            int id = 37766;
            HttpURLConnection con = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php").openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.getOutputStream().write(
                    ("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + id).getBytes("UTF-8"));
            String latestVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            return latestVersion.substring(1, latestVersion.length() - 1);
        } catch (UnknownHostException e){
            core.getLogger().warning("had an issue while trying to get the latest version.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void check(@NotNull String currentUpdate, @NotNull Player player){
        String latestVersion = getLatestVersion();
        if(latestVersion == null) return;

        if(currentUpdate.compareTo(latestVersion) < 0){
            player.sendMessage(EssAPI.color("&a&lessCore &8» &7found a new version (&c&l" + latestVersion + "&7)"));
            player.sendMessage(EssAPI.color("&a&lessCore &8» &7make sure to update to the latest version."));
            player.sendMessage(EssAPI.color("&a&lessCore &8» &7https://goo.gl/Ie0eX4"));
        }else{
            player.sendMessage(EssAPI.color("&a&lessCore &8» &7running the latest version."));
        }
    }

    public void check(@NotNull String currentUpdate, @NotNull CommandSender sender){
        String latestVersion = getLatestVersion();
        if(latestVersion == null) return;

        if(currentUpdate.compareTo(latestVersion) < 0){
            sender.sendMessage(EssAPI.color("&a&lessCore &8» &7found a new version (&c&l" + latestVersion + "&7)"));
            sender.sendMessage(EssAPI.color("&a&lessCore &8» &7make sure to update to the latest version."));
            sender.sendMessage(EssAPI.color("&a&lessCore &8» &7https://goo.gl/Ie0eX4"));
        }else{
            sender.sendMessage(EssAPI.color("&a&lessCore &8» &7currently running the latest version."));
        }
    }

    public void check(Core core, @NotNull String currentUpdate){
        String latestVersion = getLatestVersion();
        if(latestVersion == null) return;

        if(currentUpdate.compareTo(latestVersion) < 0){
            core.getLogger().warning(ChatColor.YELLOW + "---------------------");
            core.getLogger().warning(ChatColor.RED + " FOUND NEW VERSION FOR");
            core.getLogger().warning(ChatColor.RED + " essCore, please update");
            core.getLogger().warning(ChatColor.RED + " https://goo.gl/Ie0eX4");
            core.getLogger().warning(ChatColor.YELLOW + "---------------------");
        }else{
            core.getLogger().info("running latest version (" + currentUpdate + ")");
        }
    }
}