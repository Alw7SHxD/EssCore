package me.Alw7SHxD.essCore;

import me.Alw7SHxD.essCore.API.EssPlayerAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class lists{
    private Core core;

    private ArrayList<String> allowedSigns = new ArrayList<>();
    private ArrayList<Player> vanishedPlayers = new ArrayList<>();
    private ArrayList<String> limitHomes = new ArrayList<>();
    private ArrayList<String> limitHomesInt = new ArrayList<>();

    public lists(Core core) {
        this.core = core;
    }

    public void startup(){
        reload();
        loadVanishedPlayers();
        loadLimitHomes();
    }

    public void reload() {
        allowedSigns.clear();
        allowedSigns.addAll(core.getConfig().getStringList("allowed-signs"));
    }

    public void loadVanishedPlayers(){
        try {
            vanishedPlayers.clear();
            for (Player player : core.getServer().getOnlinePlayers()) {
                EssPlayerAPI playerAPI = new EssPlayerAPI(player);
                if (playerAPI.getVanished())
                    this.vanishedPlayers.add(player);
            }
        }catch (NullPointerException e){
            // nothing
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadLimitHomes(){
        try {
            limitHomes.clear();
            if(core.getConfig().getConfigurationSection("limit-homes") != null)
                limitHomes.addAll(core.getConfig().getConfigurationSection("limit-homes").getKeys(false));
        }catch (NullPointerException e){
            // nothing
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAllowedSigns() {
        return allowedSigns;
    }

    public ArrayList<String> getLimitHomes() {
        return limitHomes;
    }

    public ArrayList<Player> getVanishedPlayers() {
        return vanishedPlayers;
    }

    public void addVanishedPlayers(Player player) {
        this.vanishedPlayers.add(player);
    }

    public void removeVanishedPlayers(Player player) {
        this.vanishedPlayers.remove(player);
    }
}