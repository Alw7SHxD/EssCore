package me.Alw7SHxD.EssCore.API;

import me.Alw7SHxD.EssCore.Core;
import me.Alw7SHxD.EssCore.configuration.Data;

/**
 * EssCore was created by Alw7SHxD (C) 2017
 */
public class EssKits {
    private Core core;
    private Data data;

    public EssKits(Core core) {
        this.core = core;
        this.data = new Data(core, "kits");
    }


}
