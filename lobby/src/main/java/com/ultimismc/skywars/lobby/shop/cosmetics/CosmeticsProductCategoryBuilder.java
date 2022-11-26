package com.ultimismc.skywars.lobby.shop.cosmetics;

import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.game.features.cosmetics.CosmeticManager;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.shop.UserProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.cosmetics.ballons.BallonsCategory;
import com.ultimismc.skywars.lobby.shop.cosmetics.cages.CagesProductCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.cosmetics.deathcries.DeathCriesCategory;
import com.ultimismc.skywars.lobby.shop.cosmetics.killeffects.KillEffectsCategory;
import com.ultimismc.skywars.lobby.shop.cosmetics.killmessages.KillMessagesCategoryBuilder;
import com.ultimismc.skywars.lobby.shop.cosmetics.sprays.SpraysCategory;
import com.ultimismc.skywars.lobby.shop.cosmetics.trails.ProjectileTrailsCategory;
import com.ultimismc.skywars.lobby.shop.cosmetics.victorydances.VictoryDancesCategory;
import xyz.directplan.directlib.shop.ProductCategory;

/**
 * @author DirectPlan
 */
public class CosmeticsProductCategoryBuilder implements UserProductCategoryBuilder {

    private final CosmeticManager cosmeticManager;

    public CosmeticsProductCategoryBuilder(FeatureHandler featureHandler) {
        cosmeticManager = featureHandler.getCosmeticManager();
    }

    @Override
    public ProductCategory<User> buildCategory() {

        CosmeticProductCategory productCategory = new CosmeticProductCategory(14);

        productCategory.addProduct(new ProjectileTrailsCategory(10));
        productCategory.buildProductCategory(new CagesProductCategoryBuilder(cosmeticManager));
        productCategory.addProduct(new VictoryDancesCategory(14));
        productCategory.addProduct(new KillEffectsCategory(16));
        productCategory.addProduct(new DeathCriesCategory(28));
        productCategory.addProduct(new BallonsCategory(30));
        productCategory.buildProductCategory(new KillMessagesCategoryBuilder(cosmeticManager));
        productCategory.addProduct(new SpraysCategory(34));
        return productCategory;
    }
}
