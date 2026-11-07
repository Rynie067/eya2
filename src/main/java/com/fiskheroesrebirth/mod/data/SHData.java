package com.fiskheroesrebirth.mod.data;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class SHData {
    private static final Map<String, DataKey<?>> REGISTRY = new ConcurrentHashMap<>();
    private static final AtomicInteger NEXT_ID = new AtomicInteger();

    public static final DataKey<String> HERO = register("hero", DataSerializers.STRING, "");
    public static final DataKey<Float> ENERGY = register("energy", DataSerializers.FLOAT, 1000.0f);
    public static final DataKey<Float> MAX_ENERGY = register("max_energy", DataSerializers.FLOAT, 1000.0f);
    public static final DataKey<Boolean> FLYING = register("flying", DataSerializers.BOOLEAN, false);
    public static final DataKey<Boolean> SUIT_ACTIVE = register("suit_active", DataSerializers.BOOLEAN, false);
    public static final DataKey<Integer> SUIT_TIER = register("suit_tier", DataSerializers.INT, 1);

    private SHData() {
    }

    public static <T> DataKey<T> register(String name, DataSerializer<T> serializer, T defaultValue) {
        DataKey<T> key = new DataKey<>(NEXT_ID.getAndIncrement(), name, serializer, defaultValue);
        DataKey<?> previous = REGISTRY.putIfAbsent(name, key);
        if (previous != null) {
            throw new IllegalArgumentException("Duplicate data key: " + name);
        }
        return key;
    }

    public static DataKey<?> get(String name) {
        return REGISTRY.get(name);
    }

    public static Collection<DataKey<?>> getAll() {
        return REGISTRY.values();
    }
}
