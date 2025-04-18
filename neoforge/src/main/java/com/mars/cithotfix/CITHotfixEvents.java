package com.mars.cithotfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

import java.util.Map;
import java.util.Set;

import static com.mars.cithotfix.CommonClass.REGISTERED_MODEL_IDS;
import static com.mars.cithotfix.Constants.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CITHotfixEvents {
    @SubscribeEvent
    public static void onRegisterModel(ModelEvent.RegisterAdditional event) {
        for (Map.Entry<ResourceLocation, ResourceLocation> entry : REGISTERED_MODEL_IDS.entrySet()) {
            event.register(ModelResourceLocation.standalone(entry.getValue()));
        }
//        Set<ResourceLocation> ids = CommonClass.getTextures(Minecraft.getInstance().getResourceManager());
//        for (ResourceLocation id : ids){
//            System.out.println("onRegisterModel: " + id);
//            ResourceLocation model = id.withPrefix("item/ebooks/");
//            REGISTERED_MODEL_IDS.put(id, model);
//            event.register(ModelResourceLocation.standalone(model));
//        }
    }

    @SubscribeEvent
    public static void onRegisterClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new EbookModelPreloader());
    }
}
