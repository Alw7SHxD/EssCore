package me.Alw7SHxD.essCore.API;

import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.configuration.Data;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class EssSpawnAPI {
    private Core core;
    private Data data;

    public EssSpawnAPI(Core core) {
        this.core = core;
        this.data = new Data(core, "spawn");
    }

    public void reload(){
        data.reloadConfig();
    }

    protected void set(Location location) {
        data.set("spawn.world", location.getWorld().getName());
        data.set("spawn.X", location.getX());
        data.set("spawn.Y", location.getY());
        data.set("spawn.Z", location.getZ());
        data.set("spawn.Yaw", location.getYaw());
        data.set("spawn.Pitch", location.getPitch());
        data.saveConfig();
    }

    protected boolean delete() {
        data.reloadConfig();
        if (!data.sectionExists("spawn")) return false;
        data.set("spawn", null);
        data.saveConfig();
        return true;
    }

    public Location getLocation() {
        data.reloadConfig();
        if (!data.sectionExists("spawn")) return null;

        World world = core.getServer().getWorld(data.getString("spawn.world"));
        Double X = data.getDouble("spawn.X");
        Double Y = data.getDouble("spawn.Y");
        Double Z = data.getDouble("spawn.Z");
        Float Yaw = data.getFloat("spawn.Yaw");
        Float Pitch = data.getFloat("spawn.Pitch");

        return new Location(world, X, Y, Z, Yaw, Pitch);
    }

    public boolean teleport(Player player) {
        data.reloadConfig();
        if (!data.sectionExists("spawn")) return false;

        player.teleport(getLocation());
        return true;
    }
}
