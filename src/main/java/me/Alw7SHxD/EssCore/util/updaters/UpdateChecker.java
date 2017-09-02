package me.Alw7SHxD.EssCore.util.updaters;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

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
        } catch (UnknownHostException e) {
            core.getLogger().warning("had an issue while trying to get the latest version.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void check(CommandSender commandSender) {
        String latestVersion = getLatestVersion();
        if (latestVersion == null) return;

        if (core.getDescription().getVersion().compareTo(latestVersion) < 0) {
            if (isConsole(commandSender)) commandSender.sendMessage(EssAPI.color("&7--------------------------------------"));
            commandSender.sendMessage(EssAPI.color(String.format("&7Found a newer version of &a&lEssCore &7(&a&l%s&7)", latestVersion)));
            commandSender.sendMessage(EssAPI.color("&7please make sure to update to the latest version."));
            commandSender.sendMessage(EssAPI.color("&eSpigot Download &8@&e&n https://goo.gl/Ie0eX4"));
            if (isConsole(commandSender)) commandSender.sendMessage(EssAPI.color("&7--------------------------------------"));

        } else
            commandSender.sendMessage(EssAPI.color((isConsole(commandSender) ? "[EssCore] " : "") + String.format("&7Currently running the latest version of &a&lEssCore &7(&a&l%s&7)", core.getDescription().getVersion())));
    }

    private boolean isConsole(CommandSender commandSender) {
        return commandSender instanceof ConsoleCommandSender;
    }
}