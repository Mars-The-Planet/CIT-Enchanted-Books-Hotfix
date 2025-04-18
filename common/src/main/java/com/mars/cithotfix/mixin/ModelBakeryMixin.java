package com.mars.cithotfix.mixin;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.Reader;
import java.io.StringReader;

import static com.mars.cithotfix.CommonClass.createItemModelJson;

@Mixin(ModelBakery.class)
public class ModelBakeryMixin {
    @Inject(method = "loadBlockModel", cancellable = true, at = @At("HEAD"))
    public void loadBlockModel(ResourceLocation id, CallbackInfoReturnable<BlockModel> cir){
        if(id.toString().contains("ebooks")){
            Reader modelJson = new StringReader(createItemModelJson(id.toString()));
            if ("".equals(modelJson)) return;

            BlockModel model = BlockModel.fromStream(modelJson);
            model.name = id.toString();
            cir.setReturnValue(model);
        }
    }
}
