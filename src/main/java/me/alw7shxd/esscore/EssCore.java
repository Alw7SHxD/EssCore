package me.alw7shxd.esscore;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssCore extends JavaPlugin {

    @Override
    public void onEnable() {
        BukkitCommandManager commandManager = new BukkitCommandManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
