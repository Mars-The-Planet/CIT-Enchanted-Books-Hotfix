package com.mars.cithotfix.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mars.cithotfix.CommonClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.mars.cithotfix.CommonClass.*;

@Mixin(ModelManager.class)
public class ModelBakeryMixin {
    @Inject(method = "discoverModelDependencies", cancellable = true, at = @At("HEAD"))
    public void loadBlockModel(UnbakedModel missingModel, Map<ResourceLocation, UnbakedModel> inputs, BlockStateModelLoader.LoadedModels definition, CallbackInfoReturnable<ModelDiscovery> cir, @Local(argsOnly=true) LocalRef<Map<ResourceLocation, UnbakedModel>> inputRef){
        inputs = new HashMap<>(inputs);
        inputRef.set(inputs);

        for (var entry : REGISTERED_MODEL_IDS.entrySet()){
            ResourceLocation resourceId = entry.getValue();
            StringReader reader = new StringReader(createItemModelJson(resourceId.toString()));
            inputs.put(resourceId, BlockModel.fromStream(reader));
        }
    }
}
