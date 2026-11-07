package com.fiskheroesrebirth.mod.ability;

import com.fiskheroesrebirth.mod.energy.CooldownManager;
import com.fiskheroesrebirth.mod.energy.EnergySystem;
import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class AbilityHandler {
    private static final Map<UUID, Set<String>> ACTIVE = new ConcurrentHashMap<>();

    private AbilityHandler() {
    }

    public static boolean tryActivate(Player player, String abilityId) {
        if (player == null || abilityId == null) {
            return false;
        }

        Ability ability = AbilityRegistry.get(abilityId);
        if (ability == null || !CooldownManager.isReady(player, abilityId)) {
            return false;
        }
        if (!EnergySystem.consume(player, ability.energyCost)) {
            return false;
        }

        try {
            ability.onActivate(player);
        } catch (RuntimeException exception) {
            EnergySystem.regenerate(player, ability.energyCost);
            throw exception;
        }

        ACTIVE.computeIfAbsent(player.getUUID(), ignored -> ConcurrentHashMap.newKeySet())
            .add(abilityId);
        CooldownManager.set(player, abilityId, ability.cooldownTicks);
        return true;
    }

    public static void deactivate(Player player, String abilityId) {
        if (player == null || abilityId == null) {
            return;
        }

        Set<String> active = ACTIVE.get(player.getUUID());
        if (active == null || !active.remove(abilityId)) {
            return;
        }

        Ability ability = AbilityRegistry.get(abilityId);
        if (ability != null) {
            ability.onDeactivate(player);
        }
        if (active.isEmpty()) {
            ACTIVE.remove(player.getUUID());
        }
    }

    public static boolean isActive(Player player, String abilityId) {
        if (player == null || abilityId == null) {
            return false;
        }
        Set<String> active = ACTIVE.get(player.getUUID());
        return active != null && active.contains(abilityId);
    }

    public static void tick(Player player) {
        if (player == null) {
            return;
        }
        Set<String> active = ACTIVE.get(player.getUUID());
        if (active == null) {
            return;
        }
        for (String id : Set.copyOf(active)) {
            Ability ability = AbilityRegistry.get(id);
            if (ability != null) {
                ability.onTick(player);
            }
        }
    }

    public static void clear(Player player) {
        if (player != null) {
            ACTIVE.remove(player.getUUID());
        }
    }
}
