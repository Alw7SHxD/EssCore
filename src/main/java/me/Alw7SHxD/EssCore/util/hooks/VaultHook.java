package me.Alw7SHxD.EssCore.util.hooks;

import me.Alw7SHxD.EssCore.Core;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class VaultHook {
    private Core core;
    private Economy economy;
    private Permission permission;
    private Chat chat;


    public VaultHook(Core core) {
        this.core = core;
        this.economy = core.getEssEconomy();
    }

    public boolean setup() {
        RegisteredServiceProvider<Permission> permsProvider = Bukkit.getServer().getServicesManager()
                .getRegistration(Permission.class);
        if (permsProvider != null && permsProvider.getPlugin() != null)
            permission = permsProvider.getProvider();

        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager()
                .getRegistration(Chat.class);
        if (chatProvider != null && chatProvider.getPlugin() != null)
            chat = chatProvider.getProvider();

        return permission != null && chat != null;
    }

    public void hookEconomy() {
        core.getServer().getServicesManager().register(Economy.class, this.economy, this.core, ServicePriority.High);
        core.getLogger().info("[Economy] hooked into Vault");
    }

    public void unHook() {
        core.getServer().getServicesManager().unregister(Economy.class, this.economy);
    }

    public Permission getPermissionProvider() {
        return permission;
    }

    public Chat getChatProvider() {
        return chat;
    }
}
