package com.mars.cithotfix;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.List;
import java.util.Map;

public class ResourceFinder {
    private final String directoryName;
    private final String fileExtension;

    public ResourceFinder(String directoryName, String fileExtension) {
        this.directoryName = directoryName;
        this.fileExtension = fileExtension;
    }

    public static ResourceFinder json(String directoryName) {
        return new ResourceFinder(directoryName, ".json");
    }

    public ResourceLocation toResourcePath(ResourceLocation id) {
        String var10001 = this.directoryName;
        return id.withPath(var10001 + "/" + id.getPath() + this.fileExtension);
    }

    public ResourceLocation toResourceId(ResourceLocation path) {
        String string = path.getPath();
        return path.withPath(string.substring(this.directoryName.length() + 1, string.length() - this.fileExtension.length()));
    }

    public Map<ResourceLocation, Resource> findResources(ResourceManager resourceManager) {
        return resourceManager.listResources(this.directoryName, (path) -> {
            return path.getPath().endsWith(this.fileExtension);
        });
    }

    public Map<ResourceLocation, List<Resource>> findAllResources(ResourceManager resourceManager) {
        return resourceManager.listResourceStacks(this.directoryName, (path) -> {
            return path.getPath().endsWith(this.fileExtension);
        });
    }
}
