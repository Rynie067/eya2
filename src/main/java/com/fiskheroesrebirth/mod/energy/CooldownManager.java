package com.fiskheroesrebirth.mod.energy;

import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class CooldownManager {
    private static final Map<UUID, Map<String, Integer>> COOLDOWNS = new ConcurrentHashMap<>();

    private CooldownManager() {
    }

    public static void set(Player player, String id, int ticks) {
        if (player == null || id == null) {
            return;
        }
        COOLDOWNS.computeIfAbsent(player.getUUID(), ignored -> new ConcurrentHashMap<>())
            .put(id, Math.max(0, ticks));
    }

    public static int get(Player player, String id) {
        if (player == null || id == null) {
            return 0;
        }
        Map<String, Integer> cooldowns = COOLDOWNS.get(player.getUUID());
        return cooldowns == null ? 0 : cooldowns.getOrDefault(id, 0);
    }

    public static boolean isReady(Player player, String id) {
        return get(player, id) <= 0;
    }

    public static void tick(Player player) {
        if (player == null) {
            return;
        }
        Map<String, Integer> cooldowns = COOLDOWNS.get(player.getUUID());
        if (cooldowns == null) {
            return;
        }
        cooldowns.replaceAll((id, ticks) -> Math.max(0, ticks - 1));
        cooldowns.entrySet().removeIf(entry -> entry.getValue() == 0);
        if (cooldowns.isEmpty()) {
            COOLDOWNS.remove(player.getUUID());
        }
    }

    public static void clear(Player player) {
        if (player != null) {
            COOLDOWNS.remove(player.getUUID());
        }
    }
}
