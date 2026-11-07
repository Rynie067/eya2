package com.fiskheroesrebirth.mod.loader;

import com.fiskheroesrebirth.mod.FiskHeroesRebirth;
import com.google.gson.JsonObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class TierLoader {
    private static final Map<String, JsonObject> TIERS = new ConcurrentHashMap<>();
    private static final List<String> FILES = List.of(
        "tier/tier1", "tier/tier5", "tier/tier10", "tier/tier14"
    );

    private TierLoader() {
    }

    public static void loadAll() {
        TIERS.clear();
        for (String path : FILES) {
            JsonObject json = JsonResourceLoader.read(path);
            if (json != null) {
                TIERS.put(path.substring(path.lastIndexOf('/') + 1), json);
            }
        }
        FiskHeroesRebirth.LOGGER.info("Loaded {} tier definitions", TIERS.size());
    }

    public static void loadTiers() {
        loadAll();
    }

    public static JsonObject get(String id) {
        return TIERS.get(id);
    }

    public static Collection<JsonObject> getAll() {
        return TIERS.values();
    }
}
