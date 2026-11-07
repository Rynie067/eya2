package com.fiskheroesrebirth.mod.client.animation;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class AnimationState {
    private static final Map<UUID, PlayerAnimationData> STATES = new ConcurrentHashMap<>();

    private AnimationState() {
    }

    public static PlayerAnimationData get(UUID playerId) {
        if (playerId == null) {
            throw new IllegalArgumentException("playerId cannot be null");
        }
        return STATES.computeIfAbsent(playerId, ignored -> new PlayerAnimationData());
    }

    public static void play(UUID playerId, String animation) {
        PlayerAnimationData data = get(playerId);
        data.currentAnimation = animation;
        data.startTime = System.currentTimeMillis();
        data.progress = 0.0f;
        data.playing = true;
    }

    public static void stop(UUID playerId) {
        PlayerAnimationData data = get(playerId);
        data.playing = false;
        data.currentAnimation = null;
        data.progress = 0.0f;
    }

    public static String getCurrentAnimation(UUID playerId) {
        return get(playerId).currentAnimation;
    }

    public static float getProgress(UUID playerId) {
        return get(playerId).progress;
    }

    public static boolean isPlaying(UUID playerId) {
        return get(playerId).playing;
    }

    public static void remove(UUID playerId) {
        STATES.remove(playerId);
    }

    public static int activeCount() {
        return (int) STATES.values().stream().filter(state -> state.playing).count();
    }

    public static final class PlayerAnimationData {
        public volatile String currentAnimation;
        public volatile long startTime;
        public volatile float progress;
        public volatile boolean playing;
        public volatile boolean loop;
        public volatile int duration = 20;
        public volatile float speed = 1.0f;
    }
}
