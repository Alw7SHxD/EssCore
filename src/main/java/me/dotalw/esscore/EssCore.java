package me.dotalw.esscore;

import me.dotalw.esscore.lib.utils.Utils;
import me.dotalw.esscore.lib.utils.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

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
public final class EssCore extends JavaPlugin {
    private Utils utils;
    private Configuration config;

    @Override
    public void onLoad() {
        this.config = new Configuration(this, "config.yml", true);
        config.update();
    }

    @Override
    public void onEnable() {


    }   
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    public Utils getUtils() {
        return utils;
    }

    public Configuration getConfiguration() {
        return config;
    }

    @Override
    public YamlConfiguration getConfig() {
        return config.getConfig();
    }
}
