package com.ultimismc.skywars.core.game.features.cosmetics;

import com.ultimismc.skywars.core.game.features.PurchasableRarity;
import lombok.Getter;

/**
 * @author DirectPlan
 */
@Getter
public enum CosmeticRarity {

    LEGENDARY(PurchasableRarity.LEGENDARY, 30000), // 30000
    RARE(PurchasableRarity.RARE, 20000), // 20000
    COMMON(PurchasableRarity.COMMON, 15000); // 15000

    private final PurchasableRarity purchasableRarity;
    private final int price;

    CosmeticRarity(PurchasableRarity purchasableRarity, int price) {
        this.purchasableRarity = purchasableRarity;
        this.price = price;
    }

    public int getPriority() {
        return purchasableRarity.getPriority();
    }

    public String getDisplayName() {
        return purchasableRarity.getDisplayName();
    }
}
