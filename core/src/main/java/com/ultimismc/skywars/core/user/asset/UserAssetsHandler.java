package com.ultimismc.skywars.core.user.asset;

import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.user.UserCacheHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author DirectPlan
 */
public class UserAssetsHandler extends UserCacheHandler<String, UserAsset> {

    public UserAsset getAsset(String assetName) {
        return getCache(assetName);
    }

    public UserAsset getAsset(Purchasable purchasable) {
        return getAsset(purchasable.getName());
    }

    public void addAsset(UserAsset asset) {
        addCache(asset.getName(), asset);
    }

    public void purchaseAsset(Purchasable purchasable) {
        UserAsset userAsset = new UserAsset(purchasable, System.currentTimeMillis(), false);
        addAsset(userAsset);
    }

    public boolean hasPurchased(Purchasable purchasable) {
        return getAsset(purchasable) != null;
    }

    public <T extends Purchasable> List<T> getAssets(Class<T> clazz) {
        List<T> assets = new ArrayList<>();
        for(UserAsset userAsset : getAssets()) {
            Purchasable purchasable = userAsset.getPurchasable();
            if(!clazz.isInstance(purchasable)) continue;
            assets.add(clazz.cast(purchasable));
        }
        return assets;
    }

    public Collection<UserAsset> getAssets() {
        return getCacheCollection();
    }
}
