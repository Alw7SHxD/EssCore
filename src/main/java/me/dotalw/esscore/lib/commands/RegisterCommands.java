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

/*
 *    Copyright (C) 2011-2018 dotalw.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
