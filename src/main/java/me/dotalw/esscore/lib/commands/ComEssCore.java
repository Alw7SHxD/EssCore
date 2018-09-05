package me.dotalw.esscore.lib.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.annotation.*;
import me.dotalw.esscore.EssCore;
import me.dotalw.esscore.lib.api.EssAPI;
import org.bukkit.command.CommandSender;

/**
 * EssCore (2017) made by dotalw (C) 2011-2018
 */
@CommandAlias("esscore|ess|essc")
class ComEssCore extends BaseCommand {
    private EssAPI api;
    private EssCore ess;
    private BukkitCommandManager commandManager;

    ComEssCore(EssCore ess, BukkitCommandManager manager) {
        this.ess = ess;
        this.commandManager = manager;
        this.api = ess.getAPI();
    }

    @Default
    @Description("{@@command.esscore.description}")
    public void onDefault(CommandSender commandSender) {
        api.sendMessage(commandSender, "&a&lEssCore &7version &a&o{0}\n&7Created by &a&ldotalw", ess.getDescription().getVersion());
    }

    @CatchUnknown
    public void catchUnknown(CommandSender commandSender) {
        api.sendMessage(commandSender, "&cUnknown subcommand.");
    }

    @Subcommand("help|?|-h|--help")
    @Description("{@@command.esscore.description.help}")
    @CommandCompletion("@commands")
    public void onHelp(CommandSender commandSender, String[] strings) {
        api.sendMessage(commandSender, "&7help, this is like... new tech or some shit");
    }
}
