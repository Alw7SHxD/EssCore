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
package me.dotalw.esscore.lib.utils.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    /**
     * Name of the command
     * @return command name
     */
    public String name();

    /**
     * Description of the command
     * @return command description
     */
    public String description() default "";

    /**
     * Usage of the command - /help (command)
     * @return command usage
     */
    public String usage() default "";

    /**
     * Aliases of the command.
     * @return command aliases
     */
    public String[] aliases() default {};

    /**
     * Indicates whether the command shall be executed by players only.
     * @return TRUE if the command is a player's only command
     *         FALSE otherwise.
     */
    public boolean playerOnly() default false;

    /**
     * Indicates whether the command shall be executed by the console only.
     * @return TRUE if the command is a console command
     *         FALSE otherwise.
     */
    public boolean consoleOnly() default false;

    /**
     * Gets the required permission of the command.
     * @return command permission
     */
    public String permission() default "";

    /**
     * When a player doesnt have the permission tries to execute it.
     * Essentially, this will point to the configuration file.
     * @return no permission message
     */
    public String noPerm() default "You do not have permission to perform that action";

}
