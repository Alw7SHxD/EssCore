package me.Alw7SHxD.essCore;

/*
 * (C) Copyright 2017 Alw7SHxD.
 *
 * essCore is licensed under the Apache License, Version 2.0 (the "License");
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
public interface messages {
    String m_no_permission = "&c&lHey! &7you don't have permissions to do that.";
    String m_target_no_permission = "&c&lHey! &7you cannot %s &7that player.";
    String m_syntax_error = "&c&lSyntax Error! &7for more info type &6&l/ess &9help";
    String m_syntax_error_c = "&c&lSyntax Error! &7correct usage &6&l/%s";
    String m_target_offline = "&c&lOops! &7that player is currently offline.";
    String m_not_player = "Hey! only players can use this command.";
    String m_reload_done = "&a&lessCore &8» &2successfully reloaded configuration files.";
    String m_reload_error = "&a&lessCore &8» &can error occurred.";

    String m_ess_help_invalid_page = "&c&lInvalid page! &7available pages from &c&l1 &7to &c&l%s";

    String m_cc_global = "&7The chat was cleared by &c&l%s";
    String m_cc_global_a = "&7The global chat has been cleared";
    String m_cc_target = "&7Your chat has been cleared by &c&l%s";
    String m_cc_target_a = "&7Your chat has been cleared.";
    String m_cc_sender = "&c&l%s&7's chat has been cleared.";

    String m_fly_self_on = "&7Your flight has been &a&lenabled";
    String m_fly_self_off = "&7Your flight has been &c&ldisabled";
    String m_fly_target_on = "&c&l%s &7has &a&lenabled &7your flight";
    String m_fly_target_off = "&c&l%s &7has &c&ldisabled &7your flight";
    String m_fly_sender = "&c&l%s&7's flight has been %s";

    String m_mute_global = "&c&l%s &7has muted the global chat.";
    String m_mute_target = "&c&l%s &7has muted you.";
    String m_mute_target_a = "&7You have been muted.";
    String m_mute_sender = "&7You have muted &c&l%s";
    String m_mute_sender_g = "&c&l%s &7has muted &c&l%s&7!";
    String m_muted_sender = "&7That player is muted already.";

    String m_muted = "&c&lHey! &7you're muted, stop talking to yourself.";

    String m_unmute_global = "&c&l%s &7has un-muted the global chat.";
    String m_unmute_target = "&c&l%s &7has un-muted you.";
    String m_unmute_target_a = "&7You have been un-muted.";
    String m_unmute_sender = "&7You have un-muted &c&l%s&7!";
    String m_unmute_sender_g = "&c&l%s &7has been un-muted by &c&l%s&7!";
    String m_unmuted_sender = "&7That player isn't muted already.";

    String m_warp_no_warps = "&c&lOops! &7seems like there are no warps ;*(";
    String m_warp_not_available = "&c&l&c&lSorry! &7there are no warps available.";
    String m_warp_list = "&a&lAvailable warps&8: &7%s";
    String m_warp_doesnt_exist = "&c&lWooh! &7that warp doesn't exist!";
    String m_warp_teleport = "&aYou've been warped to &c&l%s";
    String m_warp_teleport_target = "&c&l%s &ahas warped you to &c&l%s";
    String m_warp_teleport_sender = "&c&l%s &ahas been warped to &c&l%s";

    String m_setwarp_created = "&a&lSuccess! &7warp &c&l%s &7has been created!";
    String m_setwarp_exists = "&c&lError! &7that warp already exists!";

    String m_delwarp_removed = "&a&lSuccess! &7warp &c&l%s &7has been removed!";
    String m_delwarp_failed = "&c&lError! &7couldn't find that warp.";

    String m_freeze_frozen = "&7That player is frozen already.";

    String m_freeze_target = "&3&lFreeeeeeze! &3Don't move!";
    String m_freeze_sender = "&c&l%s &7has been frozen.";

    String m_unfreeze_unfrozen = "&7That player isn't frozen already.";
    String m_unfreeze_target = "&c&l%s &7unfroze you.";
    String m_unfreeze_sender = "&c&l$s &7has been unfrozen.";

    String m_setspawn = "&a&lSuccess! &7spawn has been set.";

    String m_delspawn = "&a&lSuccess! &7spawn has been deleted.";
    String m_delspawn_fail = "&c&lOops! &7couldn't delete the spawn.";

    String m_spawn_teleport = "&aYou've been teleported to the spawn.";
    String m_spawn_fail = "&c&lOops! &7it seems like there isn't a spawn.";
    String m_spawn_target = "&c&l%s &7teleported you to the spawn.";
    String m_spawn_sender = "&7teleported &c&l%s &7to the spawn.";

    String m_nick_invalid = "&c&lHey! &7nicknames must be alphanumeric";
    String m_nick_max_length = "&c&lHey! &7nicknames cannot be more than &c&l%s &7letters/numbers";
    String m_nick_success = "&7Your nickname has been set to &r%s";
    String m_nick_sender = "&c&l%s&7's nickname has been set to &r%s";
    String m_nick_target_g = "&c&l%s has set your nickname to &r%s";
    String m_nick_off = "&7You've turned &c&loff &7your nickname.";
    String m_nick_off_fail = "&c&lHey! &7you don't have a nickname already.";
    String m_nick_off_target = "&c&l%s &7has turned &c&loff &7your nickname.";
    String m_nick_off_sender = "&c&l%s&7's nickname has been turned &c&loff&7.";

    String m_kit_create = "&a&cSuccess! &7kit &c&l%s &7has been created.";
    String m_kit_create_fail = "&a&cOops! &7that kit already exists.";

    String m_signs_warp_create = "&a&lSuccess! &7sign to warp &a&l%s &7has been created.";
    String m_signs_warp_create_fail = "&c&lError! &7make sure to type in the correct name of the warp.";
    String m_signs_warps_create = "&a&lSuccess! &7sign to display the available &a&lwarps &7has been created.";
    String m_signs_spawn_create = "&a&lSuccess! &7sign to teleport to the &a&lspawn &7has been created.";
    String m_signs_disposal_create = "&a&lSuccess! &7sign to &a&ldispose items &7has been created.";

    String m_vanish_self_on = "&7You are now &a&lvanished";
    String m_vanish_self_off = "&7You are now&c&l visible &7to everyone.";
    String m_vanish_target_on = "&c&l%s &7has &a&lenabled &7your vanish.";
    String m_vanish_target_off = "&c&l%s &7has &c&ldisabled &7your vanish.";
    String m_vanish_sender = "&c&l%s&7's vanish has been %s";

    String m_heal_self = "&aYou have been healed.";
    String m_heal_sender = "&aYou have healed &c&l%s";
    String m_heal_sender_all = "&aYou have healed all the players.";
    String m_heal_target = "&aYou have been healed by &c&l%s";

    String m_feed_self = "&aYou have been fed.";
    String m_feed_sender = "&aYou have fed &c&l%s";
    String m_feed_sender_all = "&aYou have fed all the players.";
    String m_feed_target = "&aYou have been fed by &c&l%s";

    String m_home_no_homes = "&c&lOops! &7seems like you don't have a home ;*(";
    String m_home_list = "&a&lAvailable homes&8: &7%s";
    String m_home_doesnt_exist = "&c&lWooh! &7that home doesn't exist!";
    String m_home_teleport = "&aYou've been teleported to your home.";

    String m_sethome_exists = "&c&lError! &7that home already exists.";
    String m_sethome_success = "&a&lSuccess! &7home &c&l%s &7has been created.";
    String m_sethome_limit = "&c&lHey! &7you've reached your maximum limit.";

    String m_delhome_success = "&c&lSuccess! &7deleted &c&l%s &7home.";
    String m_delhome_failed = "&c&lError! &7couldn't find that home.";
}
