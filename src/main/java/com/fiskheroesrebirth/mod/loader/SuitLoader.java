package com.fiskheroesrebirth.mod.loader;

import com.fiskheroesrebirth.mod.FiskHeroesRebirth;
import com.fiskheroesrebirth.mod.suit.Suit;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SuitLoader {
    private static final Gson GSON = new Gson();
    private static final Map<String, Suit> SUITS = new ConcurrentHashMap<>();
    private static final List<String> FILES = List.of(
        "suits/classic_suit", "suits/flash_suit", "suits/mk46_suit",
        "suits/reverse_flash_suit"
    );

    private SuitLoader() {
    }

    public static void loadAll() {
        SUITS.clear();
        for (String path : FILES) {
            JsonObject json = JsonResourceLoader.read(path);
            if (json == null) {
                continue;
            }
            Suit suit = GSON.fromJson(json, Suit.class);
            if (suit == null) {
                continue;
            }
            String id = JsonResourceLoader.string(json, "id",
                path.substring(path.lastIndexOf('/') + 1));
            suit.id = id;
            SUITS.put(id, suit);
        }
        FiskHeroesRebirth.LOGGER.info("Loaded {} suit definitions", SUITS.size());
    }

    public static Suit get(String id) {
        return SUITS.get(id);
    }

    public static Collection<Suit> getAll() {
        return SUITS.values();
    }

    public static int count() {
        return SUITS.size();
    }
}
