package com.fiskheroesrebirth.mod.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface DataSerializer<T> {
    void write(DataOutput output, T value) throws IOException;
    T read(DataInput input) throws IOException;
}
