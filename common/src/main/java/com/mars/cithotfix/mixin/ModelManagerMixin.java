package com.mars.cithotfix.mixin;

import com.mars.cithotfix.MultiLoaderModelManager;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(ModelManager.class)
public class ModelManagerMixin implements MultiLoaderModelManager {
    @Shadow
    private Map<ResourceLocation, BakedModel> bakedRegistry;

    @Override
    public BakedModel getModel(ResourceLocation id) {
        return bakedRegistry.get(id);
    }
}
