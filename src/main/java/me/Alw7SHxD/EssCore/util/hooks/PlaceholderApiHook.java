package me.Alw7SHxD.EssCore.util.hooks;

import me.Alw7SHxD.EssCore.API.Players;
import me.Alw7SHxD.EssCore.Core;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class PlaceholderApiHook extends EZPlaceholderHook {
    private Core core;

    public PlaceholderApiHook(Core core) {
        super(core, "EssCore");
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {
        if (player == null)
            return "";

        Players playerAPI = new Players(player);
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
