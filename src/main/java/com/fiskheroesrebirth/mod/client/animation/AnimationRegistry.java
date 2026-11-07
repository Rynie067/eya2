package com.fiskheroesrebirth.mod.client.animation;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class AnimationRegistry {
    private static final Map<String, AnimationData> REGISTRY = new ConcurrentHashMap<>();

    private AnimationRegistry() {
    }

    public static void register(String id, String fskFile, int duration, boolean loop) {
        if (id == null || id.isBlank() || fskFile == null || fskFile.isBlank()) {
            throw new IllegalArgumentException("Animation id and resource name must be present");
        }
        REGISTRY.put(id, new AnimationData(id, fskFile, Math.max(1, duration), loop));
    }

    public static AnimationData get(String id) {
        return REGISTRY.get(id);
    }

    public static Collection<AnimationData> getAll() {
        return REGISTRY.values();
    }

    public static int count() {
        return REGISTRY.size();
    }

    public static void registerDefaults() {
        register("helmet_deploy", "mk46_helmet", 20, false);
        register("repulsor_fire", "dual_hand_beam", 15, false);
        register("flight_lean", "flight_lean", 20, true);
        register("iron_dive", "iron_man_dive", 30, false);
        register("web_shoot_left", "web_shoot_left", 10, false);
        register("web_shoot_right", "web_shoot_right", 10, false);
        register("swing_default", "swing_default", 30, true);
        register("swing_dive", "swing_dive", 20, false);
        register("wall_crawl", "crawl_wall", 20, true);
        register("ceiling_crawl", "crawl_ceiling", 20, true);
        register("speedster_sprint", "speedster_sprint", 10, true);
        register("blocking", "blocking", 15, true);
        register("blocking_arm", "blocking_arm", 15, true);
        register("ocular_beam", "ocular_beam", 20, false);
        register("sword_pose", "sword_pose", 20, true);
        register("hero_landing", "superhero_landing", 25, false);
        register("falcon_dive", "falcon_dive_roll", 30, false);
        register("manta_flight", "mantapack_flight", 30, true);
        register("aiming", "aiming", 10, true);
        register("aiming_left", "aiming_left", 10, true);
        register("dual_aiming", "dual_aiming", 10, true);
        register("web_aim_left", "web_aim_left", 10, true);
        register("web_aim_right", "web_aim_right", 10, true);
        register("web_rappel", "web_rappel", 25, false);
        register("hat_tip", "hat_tip_player", 15, false);
        register("remove_cowl", "remove_cowl", 20, false);
    }

    public static final class AnimationData {
        public final String id;
        public final String fskFile;
        public final int duration;
        public final boolean loop;

        public AnimationData(String id, String fskFile, int duration, boolean loop) {
            this.id = id;
            this.fskFile = fskFile;
            this.duration = duration;
            this.loop = loop;
        }
    }
}
