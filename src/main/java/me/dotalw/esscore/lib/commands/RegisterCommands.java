package me.dotalw.esscore.lib.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import co.aikar.commands.RegisteredCommand;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.SetMultimap;
import me.dotalw.esscore.EssCore;
import org.bukkit.ChatColor;

import java.util.*;

/**
 * EssCore (2017) made by dotalw (C) 2011-2018
 */
public class RegisterCommands {
    private void regCommands(EssCore ess, List<BaseCommand> commandList) {
        Collections.addAll(commandList,
                new ComEssCore(ess));
    }

    public RegisterCommands(EssCore ess, BukkitCommandManager manager) {
        manager.getLocales().addMessageBundles("messages", ess.getDescription().getName(), ess.getDescription().getName().toLowerCase());
        manager.addSupportedLanguage(Locale.ENGLISH);
        manager.getLocales().loadLanguages();
        manager.setFormat(MessageType.ERROR, ChatColor.GRAY, ChatColor.GREEN, ChatColor.RED);

        List<BaseCommand> commands = new ArrayList<>();
        List<String> commandsName = new ArrayList<>();
        regCommands(ess, commands);

        commands.forEach(command -> {
            manager.registerCommand(command);
            commandsName.add(command.getName());
        });

        manager.getCommandCompletions().registerAsyncCompletion("commands", e -> ImmutableList.copyOf(commandsName));

        // I do actually want to die :)
        commands.stream().map(baseCommand -> manager.getRootCommand(baseCommand.getName())).forEach(command -> {
            SetMultimap<String, RegisteredCommand> subcommands = command.getSubCommands();
            List<String> subs = new ArrayList<>();
            subcommands.keys().stream().map(subcommands::get).flatMap(Collection::stream).filter(cmd -> !subs.contains(cmd.getPrefSubCommand())).forEach(cmd -> subs.add(cmd.getPrefSubCommand()));
            if (!subs.isEmpty())
                manager.getCommandCompletions().registerAsyncCompletion(String.format("sub.%s", command.getCommandName()), (c) -> ImmutableList.copyOf(subs));
        });
    }
}
