package com.fiskheroesrebirth.mod.events;

import com.fiskheroesrebirth.mod.ability.AbilityHandler;
import com.fiskheroesrebirth.mod.client.animation.AnimationPlayer;
import com.fiskheroesrebirth.mod.energy.CooldownManager;
import com.fiskheroesrebirth.mod.energy.EnergySystem;
import com.fiskheroesrebirth.mod.data.SHDataManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;

public final class EventHandler {
    private static boolean registered;

    private EventHandler() {
    }

    public static synchronized void register() {
        if (registered) {
            return;
        }
        registered = true;

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                CooldownManager.tick(player);
                EnergySystem.regenerate(player, 0.05f);
                AbilityHandler.tick(player);
                AnimationPlayer.tick(player.getUUID());
            }
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // The state maps are keyed by UUID; player removal cleanup is handled by
            // server lifecycle hooks in a later persistence/synchronization layer.
        });
    }
}
