package com.fiskheroesrebirth.mod.energy;

import com.fiskheroesrebirth.mod.data.SHData;
import com.fiskheroesrebirth.mod.data.SHDataManager;
import net.minecraft.world.entity.player.Player;

public final class EnergySystem {
    private EnergySystem() {
    }

    public static boolean consume(Player player, float amount) {
        if (player == null || amount < 0.0f) {
            return false;
        }
        float current = SHDataManager.getValue(player, SHData.ENERGY);
        if (current < amount) {
            return false;
        }
        SHDataManager.setValue(player, SHData.ENERGY, current - amount);
        return true;
    }

    public static void regenerate(Player player, float amount) {
        if (player == null || amount <= 0.0f) {
            return;
        }
        float current = SHDataManager.getValue(player, SHData.ENERGY);
        float maximum = SHDataManager.getValue(player, SHData.MAX_ENERGY);
        SHDataManager.setValue(player, SHData.ENERGY, Math.min(maximum, current + amount));
    }

    public static float getEnergy(Player player) {
        return SHDataManager.getValue(player, SHData.ENERGY);
    }

    public static float getMaxEnergy(Player player) {
        return SHDataManager.getValue(player, SHData.MAX_ENERGY);
    }
}
