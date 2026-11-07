package com.fiskheroesrebirth.mod.loader;

import com.fiskheroesrebirth.mod.FiskHeroesRebirth;
import com.fiskheroesrebirth.mod.power.Power;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PowerLoader {
    private static final Gson GSON = new Gson();
    private static final Map<String, Power> POWERS = new ConcurrentHashMap<>();
    private static final List<String> FILES = List.of(
        "powers/arc_reactor", "powers/flight", "powers/speed_force", "powers/spider_sense",
        "power/cosmic_mode", "power/divinity", "power/negative_speed_force",
        "power/one_punch", "power/speed_force", "power/white_lightning"
    );

    private PowerLoader() {
    }

    public static void loadAll() {
        POWERS.clear();
        for (String path : FILES) {
            JsonObject json = JsonResourceLoader.read(path);
            if (json == null) {
                continue;
            }
            Power power = GSON.fromJson(json, Power.class);
            if (power == null) {
                continue;
            }
            String id = JsonResourceLoader.string(json, "id",
                path.substring(path.lastIndexOf('/') + 1));
            power.id = id;
            POWERS.put(id, power);
        }
        FiskHeroesRebirth.LOGGER.info("Loaded {} power definitions", POWERS.size());
    }

    public static Power get(String id) {
        return POWERS.get(id);
    }

    public static Collection<Power> getAll() {
        return POWERS.values();
    }

    public static int count() {
        return POWERS.size();
    }
}
