package com.mars.cithotfix;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static com.mars.cithotfix.CommonClass.REGISTERED_MODEL_IDS;

public class EbookModelPreloader implements PreparableReloadListener {
    @Override
    public CompletableFuture<Void> reload(PreparationBarrier barrier, ResourceManager resourceManager, Executor executor, Executor executor1) {
        REGISTERED_MODEL_IDS = new HashMap<>();
        return CompletableFuture
                .supplyAsync(() -> {
                    Set<ResourceLocation> ids = CommonClass.getTextures(resourceManager);
                    for (ResourceLocation id : ids) {
                        ResourceLocation model = id.withPrefix("item/ebooks/");
                        REGISTERED_MODEL_IDS.put(id, model);
                    }
                    return null;
                }, executor)
                .thenCompose(barrier::wait)
                .thenRunAsync(() -> {}, executor1);
    }

}
