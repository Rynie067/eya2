package com.fiskheroesrebirth.mod.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class DataHolder {
    private final Map<Integer, Object> values = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T get(DataKey<T> key) {
        Object value = values.get(key.id);
        return value == null ? key.defaultValue : (T) value;
    }

    public <T> void set(DataKey<T> key, T value) {
        if (value == null) {
            values.remove(key.id);
        } else {
            values.put(key.id, value);
        }
    }

    public void clear() {
        values.clear();
    }
}
