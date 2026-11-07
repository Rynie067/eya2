package com.fiskheroesrebirth.mod.client.animation;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public final class AnimationEventHandler {
    private static final List<Consumer<AnimationEvent>> LISTENERS = new CopyOnWriteArrayList<>();

    private AnimationEventHandler() {
    }

    public static void register(Consumer<AnimationEvent> listener) {
        if (listener != null) {
            LISTENERS.add(listener);
        }
    }

    public static void unregister(Consumer<AnimationEvent> listener) {
        LISTENERS.remove(listener);
    }

    public static void fire(AnimationEvent event) {
        for (Consumer<AnimationEvent> listener : LISTENERS) {
            try {
                listener.accept(event);
            } catch (RuntimeException exception) {
                System.err.println("[Super Heroes Rework] Animation listener failed: " + exception.getMessage());
            }
        }
    }

    public static int listenerCount() {
        return LISTENERS.size();
    }
}
