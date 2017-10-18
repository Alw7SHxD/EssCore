package me.Alw7SHxD.EssCore.listeners;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.API.EssSpawn;
import me.Alw7SHxD.EssCore.API.EssWarps;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.Lists;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class PlayerInteractHandler implements Listener, messages {
    private Core core;
    private Lists Lists;

    PlayerInteractHandler(Core core) {
        this.core = core;
        this.Lists = new Lists(core);
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {
        try {
            Material clickedBlock = e.getClickedBlock().getType();
            if (clickedBlock == Material.SIGN || clickedBlock == Material.SIGN_POST || clickedBlock == Material.WALL_SIGN) {
                if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
                    return;

                Lists.reload();
                Sign sign = (Sign) e.getClickedBlock().getState();
                if (sign.getLine(0).equals(EssAPI.color("&8[&2&lWARP&8]"))) {
                    String perm = "esscore.signs.warp.use";
                    if (Lists.getAllowedSigns().contains("warp") && e.getPlayer().hasPermission(perm))
                        new EssWarps(core).teleport(sign.getLine(1), e.getPlayer());
                    else if (!e.getPlayer().hasPermission(perm) && core.Lists.isDebugSigns())
                        e.getPlayer().sendMessage(EssAPI.color(String.format(m_signs_debug_permission, perm)));
                } else if (sign.getLine(0).equals(EssAPI.color("&8[&2&lWARPS&8]"))) {
                    String perm = "esscore.signs.warps.use";
                    if (Lists.getAllowedSigns().contains("warps") && e.getPlayer().hasPermission(perm))
                        new EssWarps(core).list(e.getPlayer());
                    else if (!e.getPlayer().hasPermission(perm) && core.Lists.isDebugSigns())
                        e.getPlayer().sendMessage(EssAPI.color(String.format(m_signs_debug_permission, perm)));
                } else if (sign.getLine(0).equals(EssAPI.color("&8[&2&lSPAWN&8]"))) {
                    String perm = "esscore.signs.spawn.use";
                    if (Lists.getAllowedSigns().contains("spawn") && e.getPlayer().hasPermission(perm)) {
                        new EssSpawn(core).teleport(e.getPlayer());
                        e.getPlayer().sendMessage(EssAPI.color(m_spawn_teleport));
                    } else if (!e.getPlayer().hasPermission(perm) && core.Lists.isDebugSigns())
                        e.getPlayer().sendMessage(EssAPI.color(String.format(m_signs_debug_permission, perm)));
                } else if (sign.getLine(0).equals(EssAPI.color("&8[&2&lDISPOSAL&8]"))) {
                    String perm = "esscore.signs.disposal.use";
                    if (Lists.getAllowedSigns().contains("disposal") && e.getPlayer().hasPermission(perm)) {
                        e.getPlayer().openInventory(Bukkit.createInventory(null, 27, EssAPI.color("&8&lDisposal")));
                    } else if (!e.getPlayer().hasPermission(perm) && core.Lists.isDebugSigns())
                        e.getPlayer().sendMessage(EssAPI.color(String.format(m_signs_debug_permission, perm)));
                } else if (sign.getLine(0).equals(EssAPI.color("&8[&2&lFEED&8]"))) {
                    String perm = "esscore.signs.feed.use";
                    if (Lists.getAllowedSigns().contains("feed") && e.getPlayer().hasPermission(perm)) {
                        e.getPlayer().setFoodLevel(20);
                        e.getPlayer().sendMessage(EssAPI.color(m_feed_self));
                    } else if (!e.getPlayer().hasPermission(perm) && core.Lists.isDebugSigns())
                        e.getPlayer().sendMessage(EssAPI.color(String.format(m_signs_debug_permission, perm)));
                } else if (sign.getLine(0).equals(EssAPI.color("&8[&2&lHEAL&8]"))) {
                    String perm = "esscore.signs.heal.use";
                    if (Lists.getAllowedSigns().contains("heal") && e.getPlayer().hasPermission(perm)) {
                        e.getPlayer().setHealth(20);
                        e.getPlayer().sendMessage(EssAPI.color(m_heal_self));
                    } else if (!e.getPlayer().hasPermission(perm) && core.Lists.isDebugSigns())
                        e.getPlayer().sendMessage(EssAPI.color(String.format(m_signs_debug_permission, perm)));
                } else if (sign.getLine(0).equals(EssAPI.color("&8[&2&lBALANCE&8]"))) {
                    String perm = "esscore.signs.balance.use";
                    if (Lists.getAllowedSigns().contains("balance") && e.getPlayer().hasPermission(perm)) {
                        e.getPlayer().sendMessage(EssAPI.color(String.format(messages.m_balance_self, core.getEssEconomy().format(core.getEssEconomy().getBalance(e.getPlayer()), false), replace(core.getEssEconomy().getBalance(e.getPlayer())))));
                    } else if (!e.getPlayer().hasPermission(perm) && core.Lists.isDebugSigns())
                        e.getPlayer().sendMessage(EssAPI.color(String.format(m_signs_debug_permission, perm)));
                }
            }
        } catch (NullPointerException ex) {
            // nothing
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String replace(double v) {
        return v == 1 ? core.getEssEconomy().currencyNameSingular() : core.getEssEconomy().currencyNamePlural();
    }
}
