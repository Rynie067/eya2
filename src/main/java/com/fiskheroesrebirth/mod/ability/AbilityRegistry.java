package com.fiskheroesrebirth.mod.ability;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class AbilityRegistry {
    private static final Map<String, Ability> REGISTRY = new ConcurrentHashMap<>();

    private AbilityRegistry() {
    }

    public static void register(Ability ability) {
        if (ability == null || ability.id == null || ability.id.isBlank()) {
            throw new IllegalArgumentException("Ability and ability id must be present");
        }
        REGISTRY.put(ability.id, ability);
    }

    public static Ability get(String id) {
        return REGISTRY.get(id);
    }

    public static Collection<Ability> getAll() {
        return REGISTRY.values();
    }

    public static int count() {
        return REGISTRY.size();
    }

    public static void clear() {
        REGISTRY.clear();
    }

    public static void registerAll() {
        // Concrete gameplay abilities are registered by their implementations.
    }
}
