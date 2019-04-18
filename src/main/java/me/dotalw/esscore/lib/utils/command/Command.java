package me.dotalw.esscore.lib.utils.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    /**
     * Name of command
     * @return
     */

    public String name();

    /**
     * Description of command - help of the cmd
     * @return
     */

    public String description() default "";

    /**
     * Usage of command - /help (commandname)
     *
     * @return
     */
    public String usage() default "";
    /**
     * Aliases of the command.
     *
     * @return
     */
    public String[] aliases() default {};
    /**
     * Players or not only?
     *
     * @return
     */
    public boolean playerOnly() default false;
    /**
     * Gets the required permission of the command
     *
     * @return
     */
    public String permission() default "";
    /**
     * When a player that doesnt have the permission tries to execute it.
     * Essentially, this will point to the configuration file.
     *
     * @return
     */
    public String noPerm() default "You do not have permission to perform that action";

}
