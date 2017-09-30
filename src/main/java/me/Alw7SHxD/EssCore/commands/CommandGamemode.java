package me.Alw7SHxD.EssCore.commands;

import me.Alw7SHxD.EssCore.API.EssAPI;
import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.util.vars.messages;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * EssCore was created by ApixTeam (C) 2017
 * in association with TheSourceCode (C) 2017
 */
public class CommandGamemode implements CommandExecutor {
    private Core core;

    public CommandGamemode(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!EssAPI.hasPermission(commandSender, "esscore.gamemode"))
            return true;

        if (s.equalsIgnoreCase("gmc")) {
            updateGamemode(commandSender, s, strings, "gamemode.creative", GameMode.CREATIVE);
            return true;
        } else if (s.equalsIgnoreCase("gms")) {
            updateGamemode(commandSender, s, strings, "gamemode.survival", GameMode.SURVIVAL);
            return true;
        } else if (s.equalsIgnoreCase("gma")) {
            updateGamemode(commandSender, s, strings, "gamemode.adventure", GameMode.ADVENTURE);
            return true;
        } else if (s.equalsIgnoreCase("gmsp")) {
            updateGamemode(commandSender, s, strings, "gamemode.spectator", GameMode.SPECTATOR);
            return true;
        }

        if (strings.length == 1) {
            if(!(commandSender instanceof Player)){
                commandSender.sendMessage(EssAPI.color(messages.m_not_player));
                return true;
            }

            updateGamemode(commandSender, ((Player) commandSender), s, lol(strings[0]));
        } else if (strings.length == 2) {
            Player target = EssAPI.getPlayer(core, commandSender, strings[1]);
            if(target == null)
                return true;

            updateGamemode(commandSender, target, s, lol(strings[0]));
        } else
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9<mode> [player]")));
        return true;
    }

    private GameMode lol(String s) {
            if (s.equalsIgnoreCase("creative") || s.equalsIgnoreCase("c") || s.equalsIgnoreCase("1"))
                return GameMode.CREATIVE;
            else if (s.equalsIgnoreCase("survival") || s.equalsIgnoreCase("s") || s.equalsIgnoreCase("0"))
                return GameMode.SURVIVAL;
            else if (s.equalsIgnoreCase("adventure") || s.equalsIgnoreCase("a") || s.equalsIgnoreCase("2"))
                return GameMode.ADVENTURE;
            else if (s.equalsIgnoreCase("spectator") || s.equalsIgnoreCase("sp") || s.equalsIgnoreCase("3"))
                return GameMode.SPECTATOR;

        return null;
    }

    private void updateGamemode(CommandSender commandSender, Player player, String s, GameMode gameMode) {
        if(gameMode == null){
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9<mode> [player]")));
            return;
        }

        if(commandSender != player)
            if (!EssAPI.hasPermission(commandSender, "esscore.gamemode.target"))
                return;

        if (!EssAPI.hasPermission(commandSender, String.format("esscore.gamemode.%s", gameMode.toString().toLowerCase())))
            return;

        player.setGameMode(gameMode);
        if(player == commandSender)
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_gamemode, gameMode.toString().toLowerCase())));
        else{
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_gamemode_sender, player.getName(), gameMode.toString().toLowerCase())));
            player.sendMessage(EssAPI.color(String.format(messages.m_gamemode, gameMode.toString().toLowerCase())));
        }
    }

    private void updateGamemode(CommandSender commandSender, String s, String[] strings, String permission, GameMode gameMode) {
        if (strings.length == 0) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(EssAPI.color(messages.m_not_player));
                return;
            }

            if (!EssAPI.hasPermission(commandSender, String.format("esscore.%s", permission)))
                return;

            ((Player) commandSender).setGameMode(gameMode);
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_gamemode, gameMode.toString().toLowerCase())));
        } else if (strings.length == 1) {
            Player target = EssAPI.getPlayer(core, commandSender, strings[0]);
            if (target == null)
                return;

            if(target != commandSender)
                if (!EssAPI.hasPermission(commandSender, "esscore.gamemode.target"))
                    return;

            if (!EssAPI.hasPermission(commandSender, String.format("esscore.%s", permission)))
                return;

            target.setGameMode(gameMode);
            if (target == commandSender)
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_gamemode, gameMode.toString().toLowerCase())));
            else {
                commandSender.sendMessage(EssAPI.color(String.format(messages.m_gamemode_sender, target.getName(), gameMode.toString().toLowerCase())));
                target.sendMessage(EssAPI.color(String.format(messages.m_gamemode, gameMode.toString().toLowerCase())));
            }
        } else
            commandSender.sendMessage(EssAPI.color(String.format(messages.m_syntax_error_c, s + " &9[player]")));
    }
}
