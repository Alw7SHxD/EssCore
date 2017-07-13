package me.Alw7SHxD.essCore.util;

import me.Alw7SHxD.essCore.Core;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.List;
import java.util.UUID;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class EssEconomy implements Economy {
    private Core core;
    private Economy economy;

    public EssEconomy(Core core) {
        this.core = core;
        RegisteredServiceProvider<Economy> economyProvider = core.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null)
            this.economy = economyProvider.getProvider();
    }

    @Override
    public boolean isEnabled() {
        return this.economy.isEnabled();
    }

    @Override
    public String getName() {
        return this.economy.getName();
    }

    @Override
    public boolean hasBankSupport() {
        return this.economy.hasBankSupport();
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return this.economy.format(v);
    }

    @Override
    public String currencyNamePlural() {
        return "dollars";
    }

    @Override
    public String currencyNameSingular() {
        return "dollar";
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public boolean hasAccount(String s) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        return core.lists.getPlayerBank().containsKey(uuid);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        UUID uuid = offlinePlayer.getUniqueId();
        return core.lists.getPlayerBank().containsKey(uuid);
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public boolean hasAccount(String s, String s1) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        return core.lists.getPlayerBank().containsKey(uuid);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        UUID uuid = offlinePlayer.getUniqueId();
        return core.lists.getPlayerBank().containsKey(uuid);
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public double getBalance(String s) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        return core.lists.getPlayerBank().get(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        UUID uuid = offlinePlayer.getUniqueId();
        return core.lists.getPlayerBank().get(uuid);
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public double getBalance(String s, String s1) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        return core.lists.getPlayerBank().get(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        UUID uuid = offlinePlayer.getUniqueId();
        return core.lists.getPlayerBank().get(uuid);
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public boolean has(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        Double balance = core.lists.getPlayerBank().get(uuid);
        return balance >= v;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        Double balance = core.lists.getPlayerBank().get(uuid);
        return balance >= v;
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    @Override
    public boolean has(String s, String s1, double v) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        Double balance = core.lists.getPlayerBank().get(uuid);
        return balance >= v;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        Double balance = core.lists.getPlayerBank().get(uuid);
        return balance >= v;
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = core.lists.getPlayerBank().get(uuid);
        core.lists.getPlayerBank().put(uuid, oldBalance - v);
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = core.lists.getPlayerBank().get(uuid);
        core.lists.getPlayerBank().put(uuid, oldBalance - v);
        return null;
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = core.lists.getPlayerBank().get(uuid);
        core.lists.getPlayerBank().put(uuid, oldBalance - v);
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = core.lists.getPlayerBank().get(uuid);
        core.lists.getPlayerBank().put(uuid, oldBalance - v);
        return null;
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = core.lists.getPlayerBank().get(uuid);
        core.lists.getPlayerBank().put(uuid, oldBalance + v);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = core.lists.getPlayerBank().get(uuid);
        core.lists.getPlayerBank().put(uuid, oldBalance + v);
        return null;
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = core.lists.getPlayerBank().get(uuid);
        core.lists.getPlayerBank().put(uuid, oldBalance + v);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = core.lists.getPlayerBank().get(uuid);
        core.lists.getPlayerBank().put(uuid, oldBalance + v);
        return null;
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    public EconomyResponse setPlayer(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        core.lists.getPlayerBank().put(uuid, v);
        return null;
    }

    public EconomyResponse setPlayer(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        core.lists.getPlayerBank().put(uuid, v);
        return null;
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    public EconomyResponse setPlayer(String s, String s1, double v) {
        Player player = core.getServer().getPlayerExact(s);
        UUID uuid = player.getUniqueId();
        core.lists.getPlayerBank().put(uuid, v);
        return null;
    }

    public EconomyResponse setPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        core.lists.getPlayerBank().put(uuid, v);
        return null;
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public boolean createPlayerAccount(String s) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return false;
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }
}
