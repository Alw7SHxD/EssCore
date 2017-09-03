package me.Alw7SHxD.EssCore.API;

import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.configuration.Data;
import me.Alw7SHxD.EssCore.messages;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * (C) Copyright 2017 Alw7SHxD.
 *
 * EssCore is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class EssWarps {
    private Core core;
    private Data data;
    private String w1 = "warps.";
    private String w2 = ".location.";
    private String w3 = ".permission";
    private String w4 = "esscore.warps.";

    public EssWarps(Core core) {
        this.core = core;
        this.data = new Data(core, "warps");
    }

    private boolean setters(String name, Location location) {
        name = name.replace(".", "-");
        if (data.isSet(w1 + name))
            return false;

        data.set(w1 + name + w2 + "World", location.getWorld().getName());
        data.set(w1 + name + w2 + "X", location.getX());
        data.set(w1 + name + w2 + "Y", location.getY());
        data.set(w1 + name + w2 + "Z", location.getZ());
        data.set(w1 + name + w2 + "Yaw", location.getYaw());
        data.set(w1 + name + w2 + "Pitch", location.getPitch());
        return true;
    }

    protected boolean create(String name, Location location, boolean permission) {
        name = name.toLowerCase();
        data.reloadConfig();

        if (!setters(name, location))
            return false;

        if (permission) data.set(w1 + name + w3, name);
        data.saveConfig();
        return true;
    }

    protected boolean remove(String name) {
        name = name.toLowerCase();
        data.reloadConfig();

        if (data.sectionExists(w1 + name)) {
            data.set(w1 + name, null);
            data.saveConfig();
            return true;
        }

        return false;
    }

    public void reload() {
        data.reloadConfig();
    }

    public Location getLocation(String name) {
        World world = core.getServer().getWorld(data.getString(w1 + name + w2 + "World"));
        Double x = data.getDouble(w1 + name + w2 + "X");
        Double y = data.getDouble(w1 + name + w2 + "Y");
        Double z = data.getDouble(w1 + name + w2 + "Z");
        Float yaw = data.getFloat(w1 + name + w2 + "Yaw");
        Float pitch = data.getFloat(w1 + name + w2 + "Pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public void teleport(String name, Player player) {
        name = name.toLowerCase();
        data.reloadConfig();

        if (data.sectionExists(w1 + name)) {
            if (data.isSet(w1 + name + w3)) {
                if (player.hasPermission(w4 + data.getString(w1 + name + w3))) {
                    player.teleport(getLocation(name));
                    player.sendMessage(EssAPI.color(String.format(messages.m_warp_teleport, name)));
                } else player.sendMessage(EssAPI.color(messages.m_no_permission));
            } else {
                player.teleport(getLocation(name));
                player.sendMessage(EssAPI.color(String.format(messages.m_warp_teleport, name)));
            }
        } else player.sendMessage(EssAPI.color(messages.m_warp_doesnt_exist));
    }

    public void teleport(String name, CommandSender sender, Player player) {
        name = name.toLowerCase();
        data.reloadConfig();

        if (data.sectionExists(w1 + name)) {
            if (data.isSet(w1 + name + w3)) {
                if (sender.hasPermission(w4 + data.getString(w1 + name + w3))) {
                    player.teleport(getLocation(name));
                    player.sendMessage(EssAPI.color(String.format(messages.m_warp_teleport_target, EssAPI.getSenderDisplayName(core, sender), name)));
                    if (sender != player)
                        sender.sendMessage(EssAPI.color(String.format(messages.m_warp_teleport_sender, player.getName(), name)));
                } else player.sendMessage(EssAPI.color(messages.m_no_permission));
            } else {
                player.teleport(getLocation(name));
                player.sendMessage(EssAPI.color(String.format(messages.m_warp_teleport_target, EssAPI.getSenderDisplayName(core, sender), name)));
                if (sender != player)
                    sender.sendMessage(EssAPI.color(String.format(messages.m_warp_teleport_sender, player.getName(), name)));
            }
        } else sender.sendMessage(EssAPI.color(messages.m_warp_doesnt_exist));
    }

    public void list(CommandSender commandSender) {
        data.reloadConfig();
        ArrayList<String> list = new ArrayList<>();

        try {
            for (String warp : data.getConfigurationSection("warps").getKeys(false)) {
                if (data.isSet(w1 + warp + w3)) {
                    if (commandSender.hasPermission(w4 + data.getString(w1 + warp + w3))) list.add(warp);
                } else list.add(warp);
            }
        } catch (Exception e) {
            list = null;
        }

        if (list == null) {
            commandSender.sendMessage(EssAPI.color(messages.m_warp_no_warps));
            return;
        }

        String warps = String.join("&8, &7", list);

        if (warps.length() == 0) {
            commandSender.sendMessage(EssAPI.color(messages.m_warp_not_available));
            return;
        }
        commandSender.sendMessage(EssAPI.color(String.format(messages.m_warp_list, warps)));
    }

    public List list() {
        data.reloadConfig();
        ArrayList<String> list = new ArrayList<>();

        try {
            list.addAll(data.getConfigurationSection("warps").getKeys(false));
        } catch (Exception e) {
            list = null;
        }

        if (list == null) return null;

        return list;
    }

    public String getPermission(String s) {
        data.reloadConfig();
        return !Objects.equals(w4 + data.getString(w1 + s + w3), w4 + "null") ? w4 + data.getString(w1 + s + w3) : null;
    }

    public String getString(String name, String s) {
        data.reloadConfig();
        return data.getString(w1 + name + "." + s);
    }
}
