package me.Alw7SHxD.essCore.util;

import me.Alw7SHxD.essCore.Core;

import java.util.HashMap;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class ConfigCache {
    private Core core;
    private HashMap<String, Object> config = new HashMap<>();

    public ConfigCache(Core core) {
        this.core = core;
    }

    public void load() {
        try {
            core.reloadConfig();
            config.put("nickname-prefix", core.getConfig().getString("nickname-prefix"));
            config.put("max-nick-length", core.getConfig().getInt("max-nick-length"));
            config.put("ignore-nick-colorcodes", core.getConfig().getBoolean("ignore-nick-colorcodes"));
            config.put("allow-color-codes", core.getConfig().getBoolean("allow-color-codes"));
            config.put("broadcast-prefix", core.getConfig().getString("broadcast-prefix"));
            config.put("cm.join", core.getConfig().getString("custom-messages.join"));
            config.put("cm.leave", core.getConfig().getString("custom-messages.leave"));
            config.put("hm.join", core.getConfig().getBoolean("hide-messages.join"));
            config.put("hm.leave", core.getConfig().getBoolean("hide-messages.leave"));
            config.put("cf.enabled", core.getConfig().getBoolean("chat-format.enabled"));
            config.put("cf.default-format", core.getConfig().getString("chat-format.default-format"));
            config.put("cuf.singular", core.getConfig().getString("currency-format.singular"));
            config.put("cuf.plural", core.getConfig().getString("currency-format.plural"));
            config.put("cuf.symbol", core.getConfig().getString("currency-format.symbol"));
            config.put("starting-balance", core.getConfig().getDouble("starting-balance"));
            config.put("allow-negative-balance", core.getConfig().getBoolean("allow-negative-balance"));
            config.put("maximum-negative-balance", core.getConfig().getDouble("maximum-negative-balance"));
            config.put("stp.player-join", core.getConfig().getBoolean("spawn-teleport.player-join"));
            config.put("stp.player-first-join", core.getConfig().getBoolean("spawn-teleport.player-first-join"));
            config.put("stp.player-respawn", core.getConfig().getBoolean("spawn-teleport.player-respawn"));
            config.put("version", core.getConfig().getString("essCore"));
        }catch (Exception e){
            core.getLogger().warning("seems like your config is outdated, please make sure to update it.");
            e.printStackTrace();
        }
    }

    public HashMap<String, Object> getConfig() {
        return config;
    }

    public String getString(String s){
        return String.valueOf(config.get(s));
    }

    public Double getDouble(String s){
        return (Double) config.get(s);
    }

    public int getInt(String s){
        return (int) config.get(s);
    }

    public Boolean getBoolean(String s){
        return (boolean) config.get(s);
    }
}
