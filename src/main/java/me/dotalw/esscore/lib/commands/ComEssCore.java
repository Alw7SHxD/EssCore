package me.dotalw.esscore.lib.commands;

import co.aikar.commands.BaseCommand;
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

    ComEssCore(EssCore ess) {
        this.ess = ess;
        this.api = ess.getAPI();
    }

    @Default
    @Description("{@@command.esscore.description}")
    public void onDefault(CommandSender commandSender) {
        api.sendMessage(commandSender, "&a&lEssCore &7version &a&o{0}\n&7Created by &a&ldotalw", ess.getDescription().getVersion());
    }

    @CatchUnknown
    public void catchUnknown(CommandSender commandSender) {
        api.sendMessage(commandSender, api.getTranslatableMessage(commandSender, "general.unknown_subcommand"), getExecCommandLabel(), "?s");
    }

    @Subcommand("help|?|-h|--help")
    @Description("{@@command.esscore.description.help}")
    @CommandCompletion("@commands")
    public void onHelp(CommandSender commandSender, String[] strings) {
        api.sendMessage(commandSender, helpHeader(false), "commands");
    }

    @Subcommand("subcommands|sub|?s|-hs")
    @Description("{@@command.esscore.description.help_sub}")
    @CommandCompletion("@sub.esscore")
    public void onSubHelp(CommandSender commandSender, String[] strings) {
        api.sendMessage(commandSender, helpHeader(false), "subcommands");
    }

    private String helpHeader(boolean hasPages) {
        return String.format("&7» EssCore {0} %s", hasPages ? "&8(&c{1}&7/&c{2}&8)" : "");
    }
}
