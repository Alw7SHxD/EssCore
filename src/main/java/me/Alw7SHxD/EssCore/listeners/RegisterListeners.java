package me.Alw7SHxD.EssCore.listeners;

import me.Alw7SHxD.EssCore.Core;

/*
 * (C) Copyright 2017 Alw7SHxD.
 *
 * EssCore is licensed under the Apache License, Version 2.0 (the "License");
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
public class RegisterListeners {
    public RegisterListeners(Core core) {
        try {
            core.getServer().getPluginManager().registerEvents(new PlayerJoinHandler(core), core);
            core.getServer().getPluginManager().registerEvents(new PlayerQuitHandler(core), core);
            core.getServer().getPluginManager().registerEvents(new PlayerChatHandler(core), core);
            core.getServer().getPluginManager().registerEvents(new PlayerMovementHandler(core), core);
            core.getServer().getPluginManager().registerEvents(new BlocksHandler(core), core);
            core.getServer().getPluginManager().registerEvents(new SignsChangeHandler(core), core);
            core.getServer().getPluginManager().registerEvents(new PlayerInteractHandler(core), core);
            core.getServer().getPluginManager().registerEvents(new PlayerRespawnHandler(core), core);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
