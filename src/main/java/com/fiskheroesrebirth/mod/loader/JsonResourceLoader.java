package com.fiskheroesrebirth.mod.loader;

import com.fiskheroesrebirth.mod.FiskHeroesRebirth;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

final class JsonResourceLoader {
    private JsonResourceLoader() {
    }

    static JsonObject read(String path) {
        String resource = "data/" + FiskHeroesRebirth.MOD_ID + "/" + path + ".json";
        try (InputStream stream = FiskHeroesRebirth.class.getClassLoader().getResourceAsStream(resource)) {
            if (stream == null) {
                FiskHeroesRebirth.LOGGER.warn("Missing data file: {}", resource);
                return null;
            }
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                return JsonParser.parseReader(reader).getAsJsonObject();
            }
        } catch (IOException | RuntimeException exception) {
            FiskHeroesRebirth.LOGGER.error("Could not load data file {}", resource, exception);
            return null;
        }
    }

    static String string(JsonObject json, String key, String fallback) {
        return json.has(key) && !json.get(key).isJsonNull()
            ? json.get(key).getAsString() : fallback;
    }

    static float number(JsonObject json, String key, float fallback) {
        try {
            return json.has(key) ? json.get(key).getAsFloat() : fallback;
        } catch (RuntimeException exception) {
            return fallback;
        }
    }
}
