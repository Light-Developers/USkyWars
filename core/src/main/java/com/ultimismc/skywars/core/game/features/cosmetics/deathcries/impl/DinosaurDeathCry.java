package com.ultimismc.skywars.core.game.features.cosmetics.deathcries.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticRarity;
import com.ultimismc.skywars.core.game.features.cosmetics.deathcries.DeathCry;
import lombok.Getter;
import org.bukkit.Sound;

/**
 * @author DirectPlan
 */
@Getter
public class DinosaurDeathCry extends DeathCry {

    private final PurchasableDesign design = new PurchasableDesign("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRhNmM5MTQ0MDRiZWQyMWJkNGNmZmIwNDliOTI3ZWVkMGY4ODc2MDllNGQxZjRlNDk3NjFlYjg5MWM2OGQ5MiJ9fX0=");

    public DinosaurDeathCry() {
        super("Dinosaur", CosmeticRarity.COMMON, Sound.DONKEY_DEATH);
    }
}
