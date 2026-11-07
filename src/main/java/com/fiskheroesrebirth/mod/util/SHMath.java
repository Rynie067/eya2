package com.fiskheroesrebirth.mod.util;

public final class SHMath {
    public static final float PI = (float) Math.PI;
    public static final float TAU = PI * 2.0f;

    private SHMath() {
    }

    public static float toRad(float degrees) {
        return degrees * PI / 180.0f;
    }

    public static float toDeg(float radians) {
        return radians * 180.0f / PI;
    }

    public static float lerp(float start, float end, float amount) {
        return start + (end - start) * amount;
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    public static float smoothstep(float edge0, float edge1, float value) {
        if (edge0 == edge1) {
            return value < edge0 ? 0.0f : 1.0f;
        }
        float amount = clamp((value - edge0) / (edge1 - edge0), 0.0f, 1.0f);
        return amount * amount * (3.0f - 2.0f * amount);
    }
}
