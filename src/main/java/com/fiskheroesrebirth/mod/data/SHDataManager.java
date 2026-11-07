package com.fiskheroesrebirth.mod.data;

import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class SHDataManager {
    private static final Map<UUID, DataHolder> HOLDERS = new ConcurrentHashMap<>();

    private SHDataManager() {
    }

    public static DataHolder get(Player player) {
        return HOLDERS.computeIfAbsent(player.getUUID(), ignored -> new DataHolder());
    }

    public static <T> T getValue(Player player, DataKey<T> key) {
        return get(player).get(key);
    }

    public static <T> void setValue(Player player, DataKey<T> key, T value) {
        get(player).set(key, value);
    }

    public static void remove(UUID id) {
        HOLDERS.remove(id);
    }
}
