package com.ultimismc.skywars.core.game.features.kits.impl;

import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.game.features.kits.AbstractKit;
import com.ultimismc.skywars.core.game.features.kits.KitRarity;
import lombok.Getter;
import org.bukkit.Material;

/**
 * @author DirectPlan
 */
@Getter
public class CactusKit extends AbstractKit {

    private final PurchasableDesign design = new PurchasableDesign(Material.CACTUS);

    public CactusKit() {
        super("Cactus", KitRarity.COMMON);
    }
}
