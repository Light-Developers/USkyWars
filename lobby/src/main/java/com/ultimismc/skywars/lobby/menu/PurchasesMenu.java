package com.ultimismc.skywars.lobby.menu;

import com.ultimismc.skywars.core.game.features.Purchasable;
import com.ultimismc.skywars.core.game.features.PurchasableDesign;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.asset.UserAsset;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.DateUtil;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PaginatedMenu;
import xyz.directplan.directlib.inventory.PaginatedModel;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author DirectPlan
 */
public class PurchasesMenu extends PaginatedMenu<UserAsset> {

    private final User user;

    public PurchasesMenu(User user) {
        super(user.getName() + " Purchases", 3, PaginatedModel.DEFAULT_MODEL);

        this.user = user;
    }

    @Override
    public Collection<UserAsset> getList() {
        return user.getAssets();
    }

    @Override
    public MenuItem buildContent(Player player, UserAsset asset) {

        Purchasable purchasable = asset.getPurchasable();
        PurchasableDesign purchasableDesign = purchasable.getDesign();
        String nameWithCategory = asset.getNameWithCategory();

        boolean defaultAsset = asset.isDefaultAsset();

        MenuItem menuItem = new MenuItem(purchasableDesign.getMaterial(), ChatColor.GREEN + nameWithCategory + (defaultAsset ? "&7 (Default) " : ""), purchasableDesign.getDurability());
        String texture = purchasableDesign.getTexture();
        if(texture != null) {
            menuItem.setCustomSkullProperty(texture);
        }
        if(defaultAsset) {
            menuItem.setLore(" ");
            menuItem.setLore("&aThis " + asset.getCategory() + " comes by Default.");
            return menuItem;
        }
        menuItem.setLore("&7Price: " + asset.getDisplayPrice());
        menuItem.setLore("&7Date: &b" + DateUtil.getFormattedDate(asset.getAcquiredAt()));
        return menuItem;
    }
}
