package com.mars.cithotfix;

import com.mars.deimos.config.DeimosConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.mars.cithotfix.Constants.MOD_ID;

public class CommonClass {
    public static String BOOK_FOLDER = "";
    public static @NotNull Map<ResourceLocation, ResourceLocation> REGISTERED_MODEL_IDS = new HashMap<>();

    public static void init() {
        DeimosConfig.init(MOD_ID, Config.class);
    }

    static public ResourceLocation OfVariant(ResourceLocation variantId){
        return REGISTERED_MODEL_IDS.get(variantId);
    }

    public static Set<ResourceLocation> getTextures(ResourceManager resourceManager){
        Set<ResourceLocation> IDs = new HashSet<>();
        String folder = "optifine/cit";
        for(ResourceLocation id : resourceManager.listResources(folder, id -> id.getPath().endsWith(".png")).keySet()){
            String path = id.getPath();
            path = path.substring(folder.length()+1, path.length()-".png".length());
            IDs.add(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), path));
            if(path.contains("/")){
                BOOK_FOLDER = path.substring(0, path.indexOf("/")+1);
            }
            else{
                BOOK_FOLDER = "";
            }
        }
        return IDs;
    }

    public static String createItemModelJson(String id) {
        return "{\n" +
                "  \"parent\": \"minecraft:item/generated" + "\",\n" +
                "  \"textures\": {\n" +
                "    \"layer0\": \"minecraft:optifine/cit/" + BOOK_FOLDER + id.replace("minecraft:item/ebooks/" + BOOK_FOLDER, "") + "\"\n" +
                "  }\n" +
                "}";
    }
}
