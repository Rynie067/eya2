package com.fiskheroesrebirth.mod.util;

import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Temporary per-process item metadata cache.
 *
 * Minecraft 26.x uses item data components instead of the legacy ItemStack NBT
 * helper methods used by the 1.20.1 source. This compatibility helper does not
 * persist values to the item or world save.
 */
public final class NBTHelper {
    private static final Map<ItemStack, Map<String, String>> VALUES =
        Collections.synchronizedMap(new WeakHashMap<>());

    private NBTHelper() {
    }

    public static Map<String, String> getOrCreate(ItemStack stack) {
        if (stack == null) {
            throw new IllegalArgumentException("stack cannot be null");
        }
        synchronized (VALUES) {
            return VALUES.computeIfAbsent(stack, ignored -> new ConcurrentHashMap<>());
        }
    }

    public static boolean hasKey(ItemStack stack, String key) {
        return stack != null && key != null && getOrCreate(stack).containsKey(key);
    }

    public static void put(ItemStack stack, String key, String value) {
        getOrCreate(stack).put(key, value);
    }

    public static String get(ItemStack stack, String key) {
        return getOrCreate(stack).get(key);
    }
}
