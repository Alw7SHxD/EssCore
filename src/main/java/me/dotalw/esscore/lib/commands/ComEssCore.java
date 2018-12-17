package me.dotalw.esscore.lib.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.dotalw.esscore.EssCore;
import me.dotalw.esscore.lib.utils.Utils;
import org.bukkit.command.CommandSender;

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
@CommandAlias("esscore|ess|essc")
class ComEssCore extends BaseCommand {
    private Utils utils;
    private EssCore ess;

    ComEssCore(EssCore ess) {
        this.ess = ess;
        this.utils = ess.getUtils();
    }

    @Default
    @Description("{@@command.esscore.description}")
    public void onDefault(CommandSender commandSender) {
        utils.sendMessage(commandSender, "&a&lEssCore &7version &a&o{0}\n&7Created by &a&ldotalw", ess.getDescription().getVersion());
    }

    @CatchUnknown
    public void catchUnknown(CommandSender commandSender) {
        utils.sendMessage(commandSender, utils.getTranslatableMessage(commandSender, "general.unknown_subcommand"), getExecCommandLabel(), "?s");
    }

    @Subcommand("help|commands|?|-h|--help")
    @Description("{@@command.esscore.description.help}")
    @CommandCompletion("@commands")
    public void onHelp(CommandSender commandSender, String[] strings) {
        utils.sendMessage(commandSender, helpHeader(false), "commands");
    }

    @Subcommand("subcommands|sub|?s|-hs")
    @Description("{@@command.esscore.description.help_sub}")
    @CommandCompletion("@sub.esscore")
    public void onSubHelp(CommandSender commandSender, String[] strings) {
        utils.sendMessage(commandSender, helpHeader(false), "subcommands");
    }

    private String helpHeader(boolean hasPages) {
        return String.format("&7» EssCore {0} %s", hasPages ? "&8(&c{1}&7/&c{2}&8)" : "");
    }
}
