package me.Alw7SHxD.essCore;

import me.Alw7SHxD.essCore.API.EssPlayerAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class lists{
    private Core core;

    private ArrayList<String> allowedSigns = new ArrayList<>();
    private boolean debugSigns = false;
    private ArrayList<Player> vanishedPlayers = new ArrayList<>();
    private ArrayList<String> limitHomes = new ArrayList<>();
    private final HashMap<UUID, Double> playerBank = new HashMap<>();
    private HashMap<UUID, Integer> playerPayTransactionTime = new HashMap<>();
    private HashMap<UUID, HashMap<UUID, Double>> playerPayTransaction = new HashMap<>();

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
        debugSigns = core.getConfig().getBoolean("debug-signs");
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

    public boolean isDebugSigns() {
        return debugSigns;
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

    public HashMap<UUID, Double> getPlayerBank() {
        return playerBank;
    }

    public HashMap<UUID, Integer> getPlayerPayTransactionTime() {
        return playerPayTransactionTime;
    }

    public HashMap<UUID, HashMap<UUID, Double>> getPlayerPayTransaction() {
        return playerPayTransaction;
    }

    public void addPlayerPayTransaction(UUID uuid, UUID uuid1, int time, double amount){
        HashMap<UUID, Double> hashMap = new HashMap<>();
        hashMap.put(uuid1, amount);
        playerPayTransactionTime.put(uuid, time);
        playerPayTransaction.put(uuid, hashMap);
    }

    public void removePlayerPayTransaction(UUID uuid){
        if(playerPayTransaction.containsKey(uuid))
            playerPayTransaction.remove(uuid);
        if(playerPayTransactionTime.containsKey(uuid))
            playerPayTransactionTime.remove(uuid);
    }
}