package com.mars.cithotfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

import static com.mars.cithotfix.CommonClass.REGISTERED_MODEL_IDS;
import static com.mars.cithotfix.Constants.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CITHotfixEvents {
    @SubscribeEvent
    public static void onRegisterModel(ModelEvent.RegisterAdditional event) {
        Set<ResourceLocation> ids = CommonClass.getTextures(Minecraft.getInstance().getResourceManager());
        for (ResourceLocation id : ids){
            ResourceLocation model = id.withPrefix("item/ebooks/");
            REGISTERED_MODEL_IDS.put(id, model);
            event.register(ModelResourceLocation.inventory(model));
        }
    }
}
