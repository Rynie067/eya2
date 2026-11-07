package com.fiskheroesrebirth.mod.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class DataSerializers {
    private DataSerializers() {
    }

    public static final DataSerializer<Boolean> BOOLEAN = new DataSerializer<>() {
        @Override
        public void write(DataOutput output, Boolean value) throws IOException {
            output.writeBoolean(value);
        }

        @Override
        public Boolean read(DataInput input) throws IOException {
            return input.readBoolean();
        }
    };

    public static final DataSerializer<Integer> INT = new DataSerializer<>() {
        @Override
        public void write(DataOutput output, Integer value) throws IOException {
            output.writeInt(value);
        }

        @Override
        public Integer read(DataInput input) throws IOException {
            return input.readInt();
        }
    };

    public static final DataSerializer<Float> FLOAT = new DataSerializer<>() {
        @Override
        public void write(DataOutput output, Float value) throws IOException {
            output.writeFloat(value);
        }

        @Override
        public Float read(DataInput input) throws IOException {
            return input.readFloat();
        }
    };

    public static final DataSerializer<String> STRING = new DataSerializer<>() {
        @Override
        public void write(DataOutput output, String value) throws IOException {
            output.writeUTF(value);
        }

        @Override
        public String read(DataInput input) throws IOException {
            return input.readUTF();
        }
    };
}
