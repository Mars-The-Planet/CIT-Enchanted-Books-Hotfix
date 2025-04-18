package com.mars.cithotfix.mixin;

import com.mars.cithotfix.ResourceFinder;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceList;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

import static com.mars.cithotfix.Config.possible_roots;

@Mixin(SpriteSourceList.class)
public class SpriteSourceListMixin {
    @Shadow @Final private List<SpriteSource> sources;
    
    @Inject(at = @At("RETURN"), method = "load")
    private static void init(ResourceManager resourceManager, ResourceLocation sprite, CallbackInfoReturnable<SpriteSourceList> cir) {
        if (sprite.getPath().equals("blocks") && sprite.getNamespace().equals("minecraft")) {
            ((SpriteSourceListMixin) (Object) cir.getReturnValue()).sources.add(new SpriteSource() {
                @Override
                public void run(ResourceManager resourceManager, Output output) {
                    for (String root : possible_roots) {
                        ResourceFinder resourceFinder = new ResourceFinder(root + "/cit", ".png");
                        for (Map.Entry<ResourceLocation, Resource> entry : resourceFinder.findResources(resourceManager).entrySet())
                            output.add(resourceFinder.toResourceId(entry.getKey()).withPrefix(root + "/cit/"), entry.getValue());
                    }
                }

                @Override
                public SpriteSourceType type() {
                    return null;
                }
            });
        }
    }
}
