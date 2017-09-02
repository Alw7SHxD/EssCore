package me.Alw7SHxD.EssCore.util;

import me.Alw7SHxD.EssCore.API.Players;
import me.Alw7SHxD.EssCore.Core;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class EssEconomy implements Economy {
    private Core core;

    public EssEconomy(Core core) {
        this.core = core;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return core.getName();
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double v) {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        return String.format("%s%s", currencySymbol(), decimalFormat.format(v));
    }

    public String format(double v, boolean b) {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        return b ? String.format("%s %s", decimalFormat.format(v), v == 1 ? currencyNameSingular() : currencyNamePlural()) : decimalFormat.format(v);
    }

    @Override
    public String currencyNamePlural() {
        return core.getConfigCache().getConfig().get("cuf.plural") != null ? core.getConfigCache().getString("cuf.plural") : "Dollars";
    }

    @Override
    public String currencyNameSingular() {
        return core.getConfigCache().getConfig().get("cuf.singular") != null ? core.getConfigCache().getString("cuf.singular") : "Dollar";
    }

    public String currencySymbol() {
        return core.getConfigCache().getConfig().get("cuf.symbol") != null ? core.getConfigCache().getString("cuf.symbol") : "$";
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public boolean hasAccount(String s) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        return playerAPI.hasAccount();
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        Players playerAPI = new Players(offlinePlayer);
        return playerAPI.hasAccount();
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public boolean hasAccount(String s, String s1) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        return playerAPI.hasAccount();
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        Players playerAPI = new Players(offlinePlayer);
        return playerAPI.hasAccount();
    }

    /**
     * @param s
     * @deprecated
     */
    @Override
    public double getBalance(String s) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        return playerAPI.getBalance();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        Players playerAPI = new Players(offlinePlayer);
        return playerAPI.getBalance();
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public double getBalance(String s, String s1) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        return playerAPI.getBalance();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        Players playerAPI = new Players(offlinePlayer);
        return playerAPI.getBalance();
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public boolean has(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        return playerAPI.hasBalance(v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        Players playerAPI = new Players(offlinePlayer);
        return playerAPI.hasBalance(v);
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
        Players playerAPI = new Players(player);
        return playerAPI.hasBalance(v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        Players playerAPI = new Players(offlinePlayer);
        return playerAPI.hasBalance(v);
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        return new EconomyResponse(v, playerAPI.getBalance(), playerAPI.takeBalance(v) ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE, "withdrawFailure");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        Players playerAPI = new Players(offlinePlayer);
        return new EconomyResponse(v, playerAPI.getBalance(), playerAPI.takeBalance(v) ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE, "withdrawFailure");
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
        Players playerAPI = new Players(player);
        return new EconomyResponse(v, playerAPI.getBalance(), playerAPI.takeBalance(v) ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE, "withdrawFailure");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        Players playerAPI = new Players(offlinePlayer);
        return new EconomyResponse(v, playerAPI.getBalance(), playerAPI.takeBalance(v) ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE, "withdrawFailure");
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        double balance = playerAPI.getBalance();
        playerAPI.giveBalance(v);
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "depositFailure");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        Players playerAPI = new Players(offlinePlayer);
        double balance = playerAPI.getBalance();
        playerAPI.giveBalance(v);
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "depositFailure");
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
        Players playerAPI = new Players(player);
        double balance = playerAPI.getBalance();
        playerAPI.giveBalance(v);
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "depositFailure");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        Players playerAPI = new Players(offlinePlayer);
        double balance = playerAPI.getBalance();
        playerAPI.giveBalance(v);
        return new EconomyResponse(v, balance, EconomyResponse.ResponseType.SUCCESS, "depositFailure");
    }

    /**
     * @param s
     * @param v
     * @deprecated
     */
    public void setPlayer(String s, double v) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        playerAPI.setBalance(v);
    }

    public void setPlayer(OfflinePlayer offlinePlayer, double v) {
        Players playerAPI = new Players(offlinePlayer);
        playerAPI.setBalance(v);
    }

    /**
     * @param s
     * @param s1
     * @param v
     * @deprecated
     */
    public void setPlayer(String s, String s1, double v) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        playerAPI.setBalance(v);
    }

    public void setPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        Players playerAPI = new Players(offlinePlayer);
        playerAPI.setBalance(v);
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
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        if(!playerAPI.hasAccount())
            playerAPI.setDefaultBalance();
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        Players playerAPI = new Players(offlinePlayer);
        if(!playerAPI.hasAccount())
            playerAPI.setDefaultBalance();
        return true;
    }

    /**
     * @param s
     * @param s1
     * @deprecated
     */
    @Override
    public boolean createPlayerAccount(String s, String s1) {
        Player player = core.getServer().getPlayerExact(s);
        Players playerAPI = new Players(player);
        if(!playerAPI.hasAccount())
            playerAPI.setDefaultBalance();
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        Players playerAPI = new Players(offlinePlayer);
        if(!playerAPI.hasAccount())
            playerAPI.setDefaultBalance();
        return true;
    }
}
