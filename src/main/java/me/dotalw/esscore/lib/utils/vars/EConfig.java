/*
 *    Copyright (C) 2011-2019 dotalw.
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
package me.dotalw.esscore.lib.utils.vars;

import me.dotalw.esscore.lib.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public enum EConfig implements ICacheable<EConfig> {
    VERSION("configuration.version", 10),
    METRICS("configuration.metrics", true),
    NOTIFY("configuration.notify", true),

    // DATABASE
    DB_METHOD("database.storage method", "h2"),
    DB_ADDRESS("database.properties.address", "localhost"),
    DB_NAME("database.properties.database name", "esscore"),
    DB_USER("database.properties.username", "root"),
    DB_PASS("database.properties.password", ""),
    DB_PREFIX("database.properties.table prefix", "esscore_"),

    // CHAT
    CH_COLORS("chat.allow colorcodes", true),
    CH_BC_PREFIX("chat.broadcast prefix", "&c&lBROADCAST!&r"),

    CH_ENICK("chat.nicknames.enabled", true),
    CH_NICK_PREFIX("chat.nicknames.prefix", "~"),
    CH_NICK_LEN_MIN("chat.nicknames.length.minimum", 3),
    CH_NICK_LEN_MAX("chat.nicknames.length.maximum", 16),
    CH_NICK_LEN_IPREFIX("chat.nicknames.length.ignore.prefix", false),
    CH_NICK_LEN_ICOLORS("chat.nicknames.length.ignore.colorcodes", false),

    CH_EFORMAT("chat.format.enabled", false),
    CH_FORMATS("chat.format.formats", "default=\"{DISPLAYNAME}: {MESSAGE}\"", "moderator=\"&9&lMOD&r {DISPLAYNAME}: {MESSAGE}\"", "administrator=\"&c&lADMIN&r {DISPLAYNAME}: {MESSAGE}\""),

    CM_JOIN("chat.custom messages.player joined", "Welcome {DISPLAYNAME}!"),
    CM_LEAVE("chat.custom messages.player left", "{DISPLAYNAME} left the server."),

    // ECONOMY
    ECO_E("economy.enabled", true),
    ECO_CHAR("economy.currency symbol", "$"),
    ECO_START("economy.balance.starting", 0),
    ECO_MAX("economy.balance.maximum", 10000000000000L),
    ECO_IGNORE("economy.balance.ignore", false),
    ECO_ENEGATIVES("economy.balance.negatives.allowed", false),
    ECO_NEGATIVES_MAX("economy.balance.negatives.maximum", -1000),

    // MISCELLANEOUS
    MISC_HOMES("misc.limit homes", "administrator=7", "moderator=5", "member=3"),
    MISC_TP_JOIN("teleport to spawn when a player.joins", false),
    MISC_TP_RESPAWN("teleport to spawn when a player.respawns", true),
    MISC_TP_FIRST_TIME("teleport to spawn when a player.joins for the first time", false),

    ;

    final String key;
    final Object defaultValue;

    EConfig(String key, Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    EConfig(String key, String... defaultValues) {
        this.key = key;
        this.defaultValue = Arrays.stream(defaultValues).collect(Collectors.toMap(k -> Utils.Text.before(k, "="), k -> Utils.Text.after(k, "="), (a, b) -> b, HashMap::new));
    }

    public EConfig getNameByKey(String key) {
        return Arrays.stream(EConfig.values()).filter(c -> Objects.equals(c.key, key)).findFirst().orElse(null);
    }

    public String getKey() {
        return key;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }
}
