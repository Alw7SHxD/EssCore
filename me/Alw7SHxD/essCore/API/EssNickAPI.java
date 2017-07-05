package me.Alw7SHxD.essCore.API;

import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.messages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class EssNickAPI {
    private EssPlayerAPI playerAPI;
    private Player player;
    private Core core;

    public EssNickAPI(Player player) {
        this.playerAPI = new EssPlayerAPI(player);
        this.player = player;
        this.core = Core.getPlugin(Core.class);
    }

    private int nick(String s) {
        boolean t = true;
        String regax = "&[" + ChatColor.ALL_CODES + "]";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                if(c != '&' && c != '_') {
                    t = false;
                    break;
                }
            }
        }

        if (!t) return 0x00; // has non alphabetic letter.
        if (core.getConfig().getBoolean("ignore-nick-colorcodes")) {
            s = s.replaceAll(regax, "");
        }

        if (s.length() > core.getConfig().getInt("max-nick-length")) return 0x01; // maximum nick length exceeded
        if (s.isEmpty()) return 0x00;

        s = s.replaceAll(regax, "");
        if (!s.matches(".*[a-zA-Z0-9]+.*")) return 0x00;

        return 0x02; // valid nick
    }

    private void Nick(Player player, String s) {
        playerAPI.setNickname(s);
        player.setDisplayName(EssAPI.color(s + "&r"));
        // player.setPlayerListName(EssAPI.color(s));
    }

    public boolean eUnNick(CommandSender commandSender, Player player){
        if(playerAPI.getNickname().isEmpty()){
            commandSender.sendMessage(EssAPI.color(messages.m_nick_off_fail));
            return false;
        }

        playerAPI.setNickname(null);
        player.setDisplayName(player.getName());
        // player.setPlayerListName(player.getName());
        return true;
    }

    public void eNick(CommandSender commandSender, Player player, String nickname) {
        int nick = nick(nickname);
        if (nick == 0x00)
            commandSender.sendMessage(EssAPI.color(messages.m_nick_invalid));
        else if (nick == 0x01)
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_nick_max_length, core.getConfig().getInt("max-nick-length"))));
        else if (nick == 0x02) {
            Nick(player, nickname);
            if (commandSender == player)
                player.sendMessage(EssAPI.color(String.format(messages.m_nick_success, nickname)));
            else {
                player.sendMessage(EssAPI.color(String.format(messages.m_nick_success, nickname)));
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_nick_sender, player.getName(), nickname)));
            }
        } else
            commandSender.sendMessage(EssAPI.color(messages.m_syntax_error));
    }
}
