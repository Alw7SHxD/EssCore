package me.Alw7SHxD.essCore.API;

import me.Alw7SHxD.essCore.Core;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class EssPlaceholders extends EZPlaceholderHook {
    private Core core;

    public EssPlaceholders(Core core) {
        super(core, "essCore");
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {
        if (player == null)
            return "";

        EssPlayerAPI playerAPI = new EssPlayerAPI(player);
        switch (s) {
            case "nickname":
                if (playerAPI.getNick()) {
                    return playerAPI.getNickname();
                } else return player.getName();
            case "is_vanished":
                return String.valueOf(playerAPI.getVanished());
            case "is_muted":
                return String.valueOf(playerAPI.getMuted());
            case "flight":
                return String.valueOf(playerAPI.getFlight());
            case "is_frozen":
                return String.valueOf(playerAPI.getFrozen());
            case "is_nicked":
                return String.valueOf(playerAPI.getNick());
        }
        return null;
    }
}
