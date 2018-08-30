package me.Alw7SHxD.EssCore.util.updaters;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.Core;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * EssCore made by Alw7SHxD (C) 2018
 * @author Alw7SHxD
 *
 * This class was created to check updates using SpigotMC's legacy API.
 * @author iShadey
 */

public class SpigotUpdater {
    private Core core;
    private int project = 37766;
    private URL checkURL;
    private String newVersion;

    public SpigotUpdater(Core core) {
        this.core = core;
        this.newVersion = core.getDescription().getVersion();
        try {
            this.checkURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + project);
        } catch (MalformedURLException e) {
            // ignored
        }
    }

    public String getLatestVersion() {
        return newVersion;
    }

    public String getVersion() {
        String s = core.getDescription().getVersion();
        if (this.newVersion.startsWith("("))
            s = String.format("(%s", s);
        if (this.newVersion.endsWith(")"))
            s = String.format("%s)", s);
        return s;
    }

    public boolean checkForUpdates() throws Exception {
        URLConnection con = checkURL.openConnection();
        this.newVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        return getVersion().compareTo(getLatestVersion()) < 0;
    }

    public void check(CommandSender commandSender) {
        try {
            if (checkForUpdates()) {
                if (isConsole(commandSender)) commandSender.sendMessage(EssAPI.color("&3----------------------------------------------"));
                commandSender.sendMessage("");
                commandSender.sendMessage(EssAPI.color("&7Found a newer version for &a&lEssCore"));
                commandSender.sendMessage(EssAPI.color(String.format("&7please make sure to update to version &a&l%s", getLatestVersion())));
                commandSender.sendMessage(EssAPI.color("&eSpigot Download &8@ &e&nhttps://goo.gl/Ie0eX4"));
                commandSender.sendMessage("");
                if (isConsole(commandSender)) commandSender.sendMessage(EssAPI.color("&3----------------------------------------------"));

            } else
                commandSender.sendMessage(EssAPI.color((isConsole(commandSender) ? "[EssCore] " : "") + String.format("&7Currently running the latest version of &a&lEssCore &7(&a&l%s&7)", core.getDescription().getVersion())));
        } catch (Exception e) {
            commandSender.sendMessage(EssAPI.color("&cCouldn't check for &aEssCore&8's &cupdates."));
            commandSender.sendMessage(EssAPI.color("&cPlease check https://goo.gl/Ie0eX4 occasionally for an update!"));
            e.printStackTrace();
        }
    }

    private boolean isConsole(CommandSender commandSender) {
        return commandSender instanceof ConsoleCommandSender;
    }
}
