package me.Alw7SHxD.essCore.util.hooks;

import me.Alw7SHxD.essCore.Core;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class VaultHook {
    private Core core;
    private Economy economy;

    public VaultHook(Core core) {
        this.core = core;
        this.economy = core.getEssEconomy();
    }

    public void hook() {
        core.getServer().getServicesManager().register(Economy.class, this.economy, this.core, ServicePriority.High);
        core.getLogger().info("[Economy] hooked into Vault");
    }

    public void unHook() {
        core.getServer().getServicesManager().unregister(Economy.class, this.economy);
    }
}
