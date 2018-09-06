package me.dotalw.esscore.lib.api;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.CommandIssuer;
import co.aikar.locales.MessageKey;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 * EssCore (2017) made by dotalw (C) 2011-2018
 */
public class EssAPI {
    private static final char COLORCODE = '&';

    private BukkitCommandManager manager;

    public EssAPI(BukkitCommandManager manager) {
        this.manager = manager;
    }

    public EssAPI() {}

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes(COLORCODE, s);
    }

    public static String stripColor(String s) {
        return ChatColor.stripColor(s);
    }

    public static String removeAltColorCodes(String s) {
        return removeAltColorCodes(COLORCODE, s);
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

    public void sendMessage(CommandSender commandSender, String message, Object... replacements) {
        message = MessageFormat.format(color(message).replace("'", "''"), replacements);
        commandSender.sendMessage(message);
    }

    public String getTranslatableMessage(CommandSender commandSender, String key) {
        CommandIssuer issuer = manager.getCommandIssuer(commandSender);
        return manager.getLocales().getMessage(issuer, MessageKey.of(key));
    }
}
