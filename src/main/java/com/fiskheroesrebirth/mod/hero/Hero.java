package com.fiskheroesrebirth.mod.hero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hero {
    public final String id;
    public String name;
    public String description;
    public String texture;
    public final List<String> abilities = new ArrayList<>();
    public final Map<String, Float> attributes = new HashMap<>();
    public int color = 0xFFFFFF;
    public String alignment = "neutral";
    public String universe = "unknown";

    public Hero(String id) {
        this.id = id;
    }

    public Hero name(String name) {
        this.name = name;
        return this;
    }

    public Hero description(String description) {
        this.description = description;
        return this;
    }

    public Hero texture(String texture) {
        this.texture = texture;
        return this;
    }

    public Hero color(int color) {
        this.color = color & 0xFFFFFF;
        return this;
    }

    public Hero alignment(String alignment) {
        this.alignment = alignment;
        return this;
    }

    public Hero universe(String universe) {
        this.universe = universe;
        return this;
    }

    public Hero addAbility(String abilityId) {
        if (abilityId != null && !abilities.contains(abilityId)) {
            abilities.add(abilityId);
        }
        return this;
    }

    public Hero addAttribute(String name, float value) {
        attributes.put(name, value);
        return this;
    }
}
