package com.mars.cithotfix;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public interface MultiLoaderModelManager {
    @Nullable
    default BakedModel getModel(ResourceLocation id){
        throw new UnsupportedOperationException("Implemented via mixin.");
    }
}
