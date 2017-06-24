package me.Alw7SHxD.essCore.listeners;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.API.EssSpawnAPI;
import me.Alw7SHxD.essCore.API.EssWarpAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.lists;
import me.Alw7SHxD.essCore.messages;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class SignsChangeHandler implements Listener, messages {
    private Core core;
    private lists lists;

    public SignsChangeHandler(Core core) {
        this.core = core;
        this.lists = new lists(core);
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        lists.reload();
        for (String s : lists.getAllowedSigns()) {
            if (s.equalsIgnoreCase("colors")) {
                if (!e.getPlayer().hasPermission("esscore.signs.colors.create"))
                    continue;
                for (int i = 0; i < 4; i++) {
                    if (i == 0 && e.getLine(0).equals("&8[&2&lWARP&8]")) {
                        e.setLine(0, EssAPI.color("&0[&2&lWARP&0]"));
                        continue;
                    } else if (i == 0 && e.getLine(0).equals("&8[&2&lWARPS&8]")) {
                        e.setLine(0, EssAPI.color("&0[&2&lWARPS&0]"));
                        continue;
                    } else if (i == 0 && e.getLine(0).equals("&8[&2&lSPAWN&8]")) {
                        e.setLine(0, EssAPI.color("&0[&2&lSPAWN&0]"));
                        continue;
                    } else if (i == 0 && e.getLine(0).equals("&8[&2&lDISPOSAL&8]")) {
                        e.setLine(0, EssAPI.color("&0[&2&lDISPOSAL&0]"));
                        continue;
                    }

                    e.setLine(i, EssAPI.color(e.getLine(i)));
                }

            } else if (s.equalsIgnoreCase("warp")) {
                if (!e.getPlayer().hasPermission("esscore.signs.warp.create"))
                    continue;
                if (e.getLine(0).equalsIgnoreCase("[warp]")) {
                    EssWarpAPI warpAPI = new EssWarpAPI(core);
                    if (warpAPI.list().contains(e.getLine(1).toLowerCase())) {
                        e.setLine(0, EssAPI.color("&8[&2&lWARP&8]"));
                        e.getPlayer().sendMessage(EssAPI.color(String.format(m_signs_warp_create, e.getLine(1))));
                    } else {
                        e.setLine(0, EssAPI.color("&4[warp]"));
                        e.getPlayer().sendMessage(EssAPI.color(m_signs_warp_create_fail));
                    }
                }
            } else if (s.equalsIgnoreCase("warps")) {
                if (!e.getPlayer().hasPermission("esscore.signs.warps.create"))
                    continue;
                if (e.getLine(0).equalsIgnoreCase("[warps]")) {
                    e.setLine(0, EssAPI.color("&8[&2&lWARPS&8]"));
                    e.getPlayer().sendMessage(EssAPI.color(m_signs_warps_create));
                }
            } else if (s.equalsIgnoreCase("spawn")) {
                if (!e.getPlayer().hasPermission("esscore.signs.spawn.create"))
                    continue;
                if (e.getLine(0).equalsIgnoreCase("[spawn]")) {
                    e.setLine(0, EssAPI.color("&8[&2&lSPAWN&8]"));
                    e.getPlayer().sendMessage(EssAPI.color(m_signs_spawn_create));
                }
            } else if (s.equalsIgnoreCase("disposal")) {
                if (!e.getPlayer().hasPermission("esscore.signs.disposal.create"))
                    continue;
                if (e.getLine(0).equalsIgnoreCase("[disposal]")) {
                    e.setLine(0, EssAPI.color("&8[&2&lDISPOSAL&8]"));
                    e.getPlayer().sendMessage(EssAPI.color(m_signs_disposal_create));
                }
            }
        }
    }
}
