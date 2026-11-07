package com.fiskheroesrebirth.mod.client.animation;

import java.util.List;

/**
 * Bridge for applying parsed FSK transforms to a renderer.
 *
 * The upstream repository contains no .fsk animation files or parser, so this
 * class deliberately returns no transforms until that format is implemented.
 */
public final class FskAnimationApplier {
    private FskAnimationApplier() {
    }

    public static List<BoneTransform> sample(String animationId, float progress) {
        return List.of();
    }
}
