package com.mars.cithotfix.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mars.cithotfix.MultiLoaderModelManager;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

import static com.mars.cithotfix.CommonClass.BOOK_FOLDER;
import static com.mars.cithotfix.CommonClass.OfVariant;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @WrapOperation(method="getModel", at=@At( value="INVOKE", target="net/minecraft/client/renderer/ItemModelShaper.getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;"))
    private BakedModel getModel(ItemModelShaper models, ItemStack stack, Operation<BakedModel> original)
    {
        final ModelManager modelManager = models.getModelManager();

        if (!stack.is(Items.ENCHANTED_BOOK) || !EnchantmentHelper.hasAnyEnchantments(stack))
            return original.call(models, stack);

        ItemEnchantments storedEnchants = stack.get(DataComponents.STORED_ENCHANTMENTS);
        ResourceLocation enchantId = storedEnchants.keySet().iterator().next().unwrapKey().get().location();
        String enchantName = enchantId.toString().substring(enchantId.toString().lastIndexOf(":")+1);

        BakedModel model = !Objects.equals(enchantId.toString(), "minecraft:sweeping_edge") ?
                ((MultiLoaderModelManager) modelManager).getModel(OfVariant(new ResourceLocation("minecraft", BOOK_FOLDER + enchantName))) :
                ((MultiLoaderModelManager) modelManager).getModel(OfVariant(new ResourceLocation("minecraft", BOOK_FOLDER + "sweeping")));
        return (model!=null && model != modelManager.getMissingModel()) ? model : original.call(models, stack);
    }
}
