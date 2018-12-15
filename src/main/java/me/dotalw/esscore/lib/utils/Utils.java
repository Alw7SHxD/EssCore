package me.dotalw.esscore.lib.utils;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.CommandIssuer;
import co.aikar.locales.MessageKey;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.text.MessageFormat;
import java.util.stream.IntStream;

/**
 * EssCore (2017) made by dotalw (C) 2011-2018
 */
public class Utils {
    private static final char COLORCODE = '&';

    private BukkitCommandManager manager;

    public Utils(BukkitCommandManager manager) {
        this.manager = manager;
    }

    public Utils() {
    }

    public static String color(String s) {
        return color(COLORCODE, s);
    }

    public static String color(char altCode, String s) {
        return ChatColor.translateAlternateColorCodes(altCode, s);
    }

    public static String stripColor(String s) {
        return ChatColor.stripColor(s);
    }

    public static String removeAltColorCodes(String s) {
        return removeAltColorCodes(COLORCODE, s);
    }

    public static String removeAltColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        IntStream.range(0, b.length - 1).filter(i -> b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1).forEach(i -> {
            b[i] = 167;
            b[i + 1] = Character.toLowerCase(b[i + 1]);
        });
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
