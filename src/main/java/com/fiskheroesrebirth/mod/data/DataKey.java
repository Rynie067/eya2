package com.fiskheroesrebirth.mod.data;

import java.util.Objects;

public final class DataKey<T> {
    public final int id;
    public final String name;
    public final DataSerializer<T> serializer;
    public final T defaultValue;

    public DataKey(int id, String name, DataSerializer<T> serializer, T defaultValue) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "name");
        this.serializer = Objects.requireNonNull(serializer, "serializer");
        this.defaultValue = defaultValue;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof DataKey<?> key && id == key.id;
    }
}
