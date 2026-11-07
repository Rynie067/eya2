package com.fiskheroesrebirth.mod;

import com.fiskheroesrebirth.mod.client.animation.FskEngine;
import com.fiskheroesrebirth.mod.loader.AbilityLoader;
import com.fiskheroesrebirth.mod.loader.HeroLoader;
import com.fiskheroesrebirth.mod.loader.PowerLoader;
import com.fiskheroesrebirth.mod.loader.SuitLoader;
import com.fiskheroesrebirth.mod.loader.TierLoader;
import com.fiskheroesrebirth.mod.events.EventHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FiskHeroesRebirth implements ModInitializer {
    public static final String MOD_ID = "fiskheroesrebirth";
    public static final Logger LOGGER = LoggerFactory.getLogger("Super Heroes Rework");

    @Override
    public void onInitialize() {
        LOGGER.info("Loading Super Heroes Rework for Minecraft 26.2 / Fabric");

        HeroLoader.loadAll();
        PowerLoader.loadAll();
        SuitLoader.loadAll();
        TierLoader.loadAll();
        AbilityLoader.loadAll();
        FskEngine.loadAll();
        EventHandler.register();

        LOGGER.info(
            "Loaded {} heroes, {} powers, {} suits, {} ability definitions and {} animation entries",
            com.fiskheroesrebirth.mod.hero.HeroRegistry.count(),
            PowerLoader.count(),
            SuitLoader.count(),
            AbilityLoader.count(),
            com.fiskheroesrebirth.mod.client.animation.AnimationRegistry.count()
        );
    }
}
