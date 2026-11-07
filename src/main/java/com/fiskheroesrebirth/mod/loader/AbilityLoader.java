package com.fiskheroesrebirth.mod.loader;

import com.fiskheroesrebirth.mod.FiskHeroesRebirth;
import com.fiskheroesrebirth.mod.ability.AbilityDefinition;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class AbilityLoader {
    private static final Gson GSON = new Gson();
    private static final Map<String, AbilityDefinition> DEFINITIONS = new ConcurrentHashMap<>();
    private static final List<String> FILES = List.of(
        "abilities/helmet_deploy", "abilities/infinite_punch", "abilities/lightning_throw",
        "abilities/phasing", "abilities/repulsor_blast", "abilities/super_speed",
        "abilities/wall_crawl", "abilities/web_shot", "abilities/web_swing"
    );

    private AbilityLoader() {
    }

    public static void loadAll() {
        DEFINITIONS.clear();
        for (String path : FILES) {
            JsonObject json = JsonResourceLoader.read(path);
            if (json == null) {
                continue;
            }
            AbilityDefinition definition = GSON.fromJson(json, AbilityDefinition.class);
            if (definition == null) {
                continue;
            }
            if (definition.id == null || definition.id.isBlank()) {
                definition.id = path.substring(path.lastIndexOf('/') + 1);
            }
            DEFINITIONS.put(definition.id, definition);
        }
        FiskHeroesRebirth.LOGGER.info("Loaded {} ability definitions", DEFINITIONS.size());
    }

    public static AbilityDefinition get(String id) {
        return DEFINITIONS.get(id);
    }

    public static Collection<AbilityDefinition> getAll() {
        return DEFINITIONS.values();
    }

    public static int count() {
        return DEFINITIONS.size();
    }
}
