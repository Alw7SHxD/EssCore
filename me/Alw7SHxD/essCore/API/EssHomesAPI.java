package me.Alw7SHxD.essCore.API;

import me.Alw7SHxD.essCore.messages;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class EssHomesAPI extends EssPlayerAPI {
    String h1 = "homes.";
    String h2 = ".location.";

    public EssHomesAPI(Player player) {
        super(player);
    }

    public boolean set(String s, Location location) {
        return setHome(s, location);
    }

    public boolean get(String s){
        return getHome(s) != null;
    }

    public boolean remove(String s) {
        return delHome(s);
    }

    public boolean teleport(String s) {
        if (getHome(s) == null) return false;
        getPlayer().teleport(getHome(s));
        return true;
    }

    public void list() {
        getPlayerData().reloadYaml();
        ArrayList<String> list = new ArrayList<>();

        try {
            list.addAll(getPlayerData().getYaml().getConfigurationSection("homes").getKeys(false));
        } catch (Exception e) {
            list = null;
        }

        if (list == null) {
            getPlayer().sendMessage(EssAPI.color(messages.m_home_no_homes));
            return;
        }

        String homes = String.join("&8, &7", list);

        if (homes.length() == 0) {
            getPlayer().sendMessage(EssAPI.color(messages.m_home_no_homes));
            return;
        }
        getPlayer().sendMessage(EssAPI.color(String.format(messages.m_home_list, homes)));
    }

    public List listAll() {
        getPlayerData().reloadYaml();
        ArrayList<String> list = new ArrayList<>();

        try {
            list.addAll(getPlayerData().getYaml().getConfigurationSection("homes").getKeys(false));
        } catch (Exception e) {
            list = null;
        }

        if (list == null) return null;

        return list;
    }
}
