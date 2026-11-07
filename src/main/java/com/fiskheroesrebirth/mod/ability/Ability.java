package com.fiskheroesrebirth.mod.ability;

import net.minecraft.world.entity.player.Player;

public abstract class Ability {
    public final String id;
    public final String name;
    public float energyCost = 10.0f;
    public int cooldownTicks = 20;
    public String animation = "";

    protected Ability(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void onActivate(Player player);

    public abstract void onDeactivate(Player player);

    public void onTick(Player player) {
    }

    public Ability energy(float cost) {
        this.energyCost = Math.max(0.0f, cost);
        return this;
    }

    public Ability cooldown(int ticks) {
        this.cooldownTicks = Math.max(0, ticks);
        return this;
    }

    public Ability animation(String animationId) {
        this.animation = animationId == null ? "" : animationId;
        return this;
    }
}
