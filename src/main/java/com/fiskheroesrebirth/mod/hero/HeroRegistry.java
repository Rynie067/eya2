package com.fiskheroesrebirth.mod.hero;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class HeroRegistry {
    private static final Map<String, Hero> HEROES = new ConcurrentHashMap<>();

    private HeroRegistry() {
    }

    public static void register(Hero hero) {
        if (hero == null || hero.id == null || hero.id.isBlank()) {
            throw new IllegalArgumentException("Hero and hero id must be present");
        }
        HEROES.put(hero.id, hero);
    }

    public static Hero get(String id) {
        return HEROES.get(id);
    }

    public static Collection<Hero> getAll() {
        return HEROES.values();
    }

    public static int count() {
        return HEROES.size();
    }

    public static void clear() {
        HEROES.clear();
    }

    public static void loadAll() {
        com.fiskheroesrebirth.mod.loader.HeroLoader.loadAll();
    }
}
