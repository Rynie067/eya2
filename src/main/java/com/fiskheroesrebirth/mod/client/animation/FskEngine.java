package com.fiskheroesrebirth.mod.client.animation;

public final class FskEngine {
    private FskEngine() {
    }

    public static void loadAll() {
        AnimationRegistry.registerDefaults();
        System.out.println("[Super Heroes Rework] Registered " + AnimationRegistry.count()
            + " animation definitions. No .fsk animation assets were included upstream.");
    }
}
