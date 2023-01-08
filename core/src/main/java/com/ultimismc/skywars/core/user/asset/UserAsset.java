package com.ultimismc.skywars.core.user.asset;

import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.features.Purchasable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
@Getter
public class UserAsset {

    private final Purchasable purchasable;
    @Setter private boolean activated;
    @Setter private boolean defaultAsset;
    private final List<GameType> purchasedFor = new ArrayList<>();
    private long acquiredAt;

    public UserAsset(Purchasable purchasable, long acquiredAt, boolean activated) {
        this(purchasable);
        this.acquiredAt = acquiredAt;
        this.activated = activated;
    }

    public String getName() {
        return purchasable.getName();
    }

    public String getNameWithCategory() {
        return purchasable.getNameWithCategory();
    }

    public String getCategory() {
        return purchasable.getCategory();
    }

    public int getPrice() {
        return purchasable.getPrice();
    }

    public String getDisplayPrice() {
        return purchasable.getDisplayPrice();
    }

    public void toggleAsset() {
        this.activated = !activated;
    }

    public boolean isPurchasedFor(GameType gameType) {
        if(gameType == null) return true;
        return purchasedFor.contains(gameType);
    }

    public void addPurchasedGame(GameType gameType) {
        if(gameType == null) return;
        purchasedFor.add(gameType);
    }
}
