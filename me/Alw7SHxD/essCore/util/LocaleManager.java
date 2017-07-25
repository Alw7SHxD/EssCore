package me.Alw7SHxD.essCore.util;

import me.Alw7SHxD.essCore.Core;

import java.util.HashMap;

/**
 * essCore was created by Alw7SHxD (C) 2017
 */
public class LocaleManager {
    private Core core;
    private HashMap<message, String> locale = new HashMap<>();

    public LocaleManager(Core core) {
        this.core = core;
    }

    public HashMap<message, String> getLocale() {
        return locale;
    }
}

enum message{
    NO_PERMISSION,
    TARGET_NO_PERMISSION,
    SYNTAX_ERROR,
    SYNTAX_ERROR_C,
    TARGET_OFFLINE,
    SENDER_NOT_PLAYER,
    RELOAD_SUCCESS,
    RELOAD_FAILURE,
    NUMBER_FORMAT_ERROR,
    NON_EXISTINCE_PLAYER,
    VAULT_UNSUPPORTED,
    HELP_INVALID_PAGE,
    CLEARCHAT_GLOBAL,
    CLEARCHAT_GLOBAL_A,
    CLEARCHAT_TARGET,
    CLEARCHAT_TARGET_A,
    CLEARCHAT_SENDER,
    FLIGHT_SELF_ON,
    FLIGHT_SELF_OFF,
    FLIGHT_TARGET_ON,
    FLIGHT_TARGET_OFF,
    FLIGHT_SENDER,
    MUTE_GLOBAL,
    MUTE_TARGET,
    MUTE_TARGET_A,
    MUTE_SENDER,
    MUTE_SENDER_G,
    MUTE_SENDER_M,
    MUTED
}