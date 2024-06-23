package com.ultimismc.skywars.lobby;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import com.ultimismc.skywars.lobby.config.MessageConfigKeys;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.config.replacement.Replacement;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PlayerInventoryLayout;

import java.util.List;

/**
 * @author DirectPlan
 */
public class LobbyPlayerInventoryUi extends PlayerInventoryLayout {

    private final LobbyManager lobbyManager;
    private final User user;

    public LobbyPlayerInventoryUi(LobbyManager lobbyManager, User user) {
        this.lobbyManager = lobbyManager;
        this.user = user;
    }

    @Override
    public void build(Player player) {

        String displayName = MessageConfigKeys.SKYWARS_LOBBY_SHOP_ITEM_DISPLAY_NAME.getStringValue();
        int shopItemSlot = MessageConfigKeys.SKYWARS_LOBBY_SHOP_ITEM_SLOT.getInteger();
        MenuItem shopItem = new MenuItem(Material.EMERALD, displayName);

        UserStatistics userStatistics = user.getStatistics();
        List<String> lore = MessageConfigKeys.SKYWARS_LOBBY_SHOP_ITEM_LORE.getStringList(new Replacement("coins", userStatistics.getCoins()));
        shopItem.setLore(lore);

        shopItem.setAction((item, clicker, clickedBlock, clickType) -> lobbyManager.openShopMenu(user));
        setSlot(shopItemSlot, shopItem);
    }
}
