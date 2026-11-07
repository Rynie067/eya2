package com.fiskheroesrebirth.mod.client.animation;

import net.minecraft.world.entity.player.Player;

public final class AnimationLayer {
    private AnimationLayer() {
    }

    public static void tick(Player player) {
        if (player != null) {
            AnimationPlayer.tick(player.getUUID());
        }
    }

    public static String getCurrentAnimation(Player player) {
        return player == null ? null : AnimationState.getCurrentAnimation(player.getUUID());
    }

    public static float getProgress(Player player) {
        return player == null ? 0.0f : AnimationState.getProgress(player.getUUID());
    }
}
