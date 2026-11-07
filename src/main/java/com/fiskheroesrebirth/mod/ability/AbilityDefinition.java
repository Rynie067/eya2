package com.fiskheroesrebirth.mod.ability;

public final class AbilityDefinition {
    public String id;
    public String name;
    public float energy_cost;
    public int cooldown;
    public String animation;
    public String effect_type;
    public float damage;
    public int range;
    public String particle;
    public String sound;

    public String getId() {
        return id;
    }
}
