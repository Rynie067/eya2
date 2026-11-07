package com.fiskheroesrebirth.mod.client.animation;

import java.util.UUID;

public final class AnimationPlayer {
    private AnimationPlayer() {
    }

    public static boolean play(UUID playerId, String animationId) {
        AnimationRegistry.AnimationData data = AnimationRegistry.get(animationId);
        if (playerId == null || data == null) {
            return false;
        }

        AnimationState.PlayerAnimationData state = AnimationState.get(playerId);
        state.currentAnimation = data.fskFile;
        state.startTime = System.currentTimeMillis();
        state.progress = 0.0f;
        state.playing = true;
        state.loop = data.loop;
        state.duration = Math.max(1, data.duration);
        AnimationEventHandler.fire(new AnimationEvent(
            AnimationEvent.Type.START, playerId, animationId, 0.0f
        ));
        return true;
    }

    public static void stop(UUID playerId) {
        if (playerId == null) {
            return;
        }
        AnimationState.PlayerAnimationData state = AnimationState.get(playerId);
        if (state.playing) {
            AnimationEventHandler.fire(new AnimationEvent(
                AnimationEvent.Type.INTERRUPT, playerId, state.currentAnimation, state.progress
            ));
        }
        AnimationState.stop(playerId);
    }

    public static void tick(UUID playerId) {
        if (playerId == null) {
            return;
        }
        AnimationState.PlayerAnimationData state = AnimationState.get(playerId);
        if (!state.playing) {
            return;
        }

        long elapsed = Math.max(0L, System.currentTimeMillis() - state.startTime);
        float durationMs = Math.max(1, state.duration) * 50.0f;
        state.progress = Math.min(1.0f, elapsed / durationMs) * Math.max(0.0f, state.speed);

        if (state.progress >= 1.0f) {
            if (state.loop) {
                state.startTime = System.currentTimeMillis();
                state.progress = 0.0f;
                AnimationEventHandler.fire(new AnimationEvent(
                    AnimationEvent.Type.LOOP, playerId, state.currentAnimation, 0.0f
                ));
            } else {
                state.progress = 1.0f;
                state.playing = false;
                AnimationEventHandler.fire(new AnimationEvent(
                    AnimationEvent.Type.END, playerId, state.currentAnimation, 1.0f
                ));
            }
        }
    }

    public static float getProgress(UUID playerId) {
        return AnimationState.getProgress(playerId);
    }
}
