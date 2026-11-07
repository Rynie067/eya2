package com.fiskheroesrebirth.mod.loader;

import com.fiskheroesrebirth.mod.FiskHeroesRebirth;
import com.fiskheroesrebirth.mod.hero.Hero;
import com.fiskheroesrebirth.mod.hero.HeroRegistry;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

public final class HeroLoader {
    private static final List<String> FILES = List.of(
        "heroes/flash", "heroes/iron_man", "heroes/spider_man",
        "hero/flash", "hero/garou_cosmic", "hero/godspeed", "hero/jesus",
        "hero/reverse_flash", "hero/saitama_punch_max", "hero/savitar", "hero/superman"
    );

    private HeroLoader() {
    }

    public static void loadAll() {
        HeroRegistry.clear();
        for (String path : FILES) {
            JsonObject json = JsonResourceLoader.read(path);
            if (json == null) {
                continue;
            }

            String id = path.substring(path.lastIndexOf('/') + 1);
            Hero hero = HeroRegistry.get(id);
            if (hero == null) {
                hero = new Hero(id);
                HeroRegistry.register(hero);
            }

            hero.name(JsonResourceLoader.string(json, "name", hero.name == null ? id : hero.name));
            hero.description(JsonResourceLoader.string(json, "description",
                hero.description == null ? "" : hero.description));
            hero.universe(JsonResourceLoader.string(json, "universe", hero.universe));
            hero.alignment(JsonResourceLoader.string(json, "alignment", hero.alignment));
            hero.texture(JsonResourceLoader.string(json, "texture", hero.texture));

            if (json.has("color")) {
                try {
                    String hex = json.get("color").getAsString().replace("#", "");
                    hero.color(Integer.parseInt(hex, 16));
                } catch (RuntimeException ignored) {
                    FiskHeroesRebirth.LOGGER.warn("Invalid color for hero {}", id);
                }
            }

            if (json.has("abilities") && json.get("abilities").isJsonArray()) {
                JsonArray abilities = json.getAsJsonArray("abilities");
                for (JsonElement ability : abilities) {
                    hero.addAbility(ability.getAsString());
                }
            }

            for (String attribute : List.of("tier", "speed", "health", "unlock_xp", "unlock_gold")) {
                if (json.has(attribute)) {
                    hero.addAttribute(attribute, JsonResourceLoader.number(json, attribute, 0.0f));
                }
            }
        }
        FiskHeroesRebirth.LOGGER.info("Loaded {} hero definitions", HeroRegistry.count());
    }
}
