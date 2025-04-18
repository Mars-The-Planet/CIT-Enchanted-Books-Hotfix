package com.mars.cithotfix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.PreparableModelLoadingPlugin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static com.mars.cithotfix.CommonClass.REGISTERED_MODEL_IDS;
import static com.mars.cithotfix.CommonClass.getTextures;

public class Cithotfix implements ModInitializer, ClientModInitializer, PreparableModelLoadingPlugin<Set<ResourceLocation>>, PreparableModelLoadingPlugin.DataLoader<Set<ResourceLocation>> {
    @Override
    public void onInitialize() {
        CommonClass.init();
    }

    @Override
    public void onInitializeClient() {
        PreparableModelLoadingPlugin.register(this, this);
    }

    @Override
    public CompletableFuture<Set<ResourceLocation>> load(ResourceManager resourceManager, Executor executor) {
        return CompletableFuture.supplyAsync(()-> getTextures(resourceManager), executor);
    }

    @Override
    public void initialize(Set<ResourceLocation> ids, ModelLoadingPlugin.Context context) {
        REGISTERED_MODEL_IDS = new HashMap<>();
        for (ResourceLocation id : ids){
            //System.out.println("onRegisterModel: " + id);
            ResourceLocation model = id.withPrefix("item/ebooks/");
            REGISTERED_MODEL_IDS.put(id, model);
            System.out.println("initialize: " + id + " " + model);
            context.addModels(model);
        }
    }
}
