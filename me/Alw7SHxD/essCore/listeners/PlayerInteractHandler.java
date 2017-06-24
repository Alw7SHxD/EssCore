package me.Alw7SHxD.essCore.listeners;

import me.Alw7SHxD.essCore.API.EssAPI;
import me.Alw7SHxD.essCore.API.EssSpawnAPI;
import me.Alw7SHxD.essCore.API.EssWarpAPI;
import me.Alw7SHxD.essCore.Core;
import me.Alw7SHxD.essCore.lists;
import me.Alw7SHxD.essCore.messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class PlayerInteractHandler implements Listener, messages {
    private Core core;
    private lists lists;

    PlayerInteractHandler(Core core) {
        this.core = core;
        this.lists = new lists(core);
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {
        try {
            Material clickedBlock = e.getClickedBlock().getType();
            if (clickedBlock == Material.SIGN || clickedBlock == Material.SIGN_POST || clickedBlock == Material.WALL_SIGN) {
                if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
                    return;

                lists.reload();
                Sign sign = (Sign) e.getClickedBlock().getState();
                if (sign.getLine(0).equals(EssAPI.color("&8[&2&lWARP&8]"))) {
                    if (lists.getAllowedSigns().contains("warp") && e.getPlayer().hasPermission("esscore.signs.warp.use"))
                        new EssWarpAPI(core).teleport(sign.getLine(1), e.getPlayer());
                } else if (sign.getLine(0).equals(EssAPI.color("&8[&2&lWARPS&8]"))) {
                    if (lists.getAllowedSigns().contains("warps") && e.getPlayer().hasPermission("esscore.signs.warps.use"))
                        new EssWarpAPI(core).list(e.getPlayer());
                } else if (sign.getLine(0).equals(EssAPI.color("&8[&2&lSPAWN&8]"))) {
                    if (lists.getAllowedSigns().contains("spawn") && e.getPlayer().hasPermission("esscore.signs.spawn.use")) {
                        new EssSpawnAPI(core).teleport(e.getPlayer());
                        e.getPlayer().sendMessage(EssAPI.color(m_spawn_teleport));
                    }
                } else if (sign.getLine(0).equals(EssAPI.color("&8[&2&lDISPOSAL&8]"))) {
                    if (lists.getAllowedSigns().contains("disposal") && e.getPlayer().hasPermission("esscore.signs.disposal.use")) {
                        e.getPlayer().openInventory(Bukkit.createInventory(null, 27, EssAPI.color("&8&lDisposal")));
                    }
                }
            }
        } catch (NullPointerException ex) {
            // nothing
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
