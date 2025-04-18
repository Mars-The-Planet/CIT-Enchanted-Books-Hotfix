package com.mars.cithotfix;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class Cithotfix {
    public Cithotfix(IEventBus eventBus) {
        CommonClass.init();
    }
}
