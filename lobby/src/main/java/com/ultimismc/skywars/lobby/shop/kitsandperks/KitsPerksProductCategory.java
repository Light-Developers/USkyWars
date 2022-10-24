package com.ultimismc.skywars.lobby.shop.kitsandperks;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.lobby.config.ShopMessageKeys;
import com.ultimismc.skywars.lobby.shop.UserProductCategory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import xyz.directplan.directlib.shop.ProductItemDesign;

import java.util.List;

/**
 * @author DirectPlan
 */
public class KitsPerksProductCategory extends UserProductCategory {

    public KitsPerksProductCategory(int itemSlot) {
        super("Kits & Perks", itemSlot);
    }

    @Override
    public ProductItemDesign designCategory(User user) {

        List<String> lore = ShopMessageKeys.MY_COSMETICS_CATEGORY_LORE.getStringList();
        return new ProductItemDesign(Material.EYE_OF_ENDER, ChatColor.GREEN, lore);
    }
}
