package com.fiskheroesrebirth.mod.client.animation;

import java.util.UUID;

public final class AnimationEvent {
    public enum Type {
        START,
        END,
        LOOP,
        KEYFRAME,
        INTERRUPT
    }

    public final Type type;
    public final UUID playerId;
    public final String animationId;
    public final float progress;
    public final long timestamp;

    public AnimationEvent(Type type, UUID playerId, String animationId, float progress) {
        this.type = type;
        this.playerId = playerId;
        this.animationId = animationId;
        this.progress = progress;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return String.format("[AnimationEvent] %s: %s @ %.2f", type, animationId, progress);
    }
}
