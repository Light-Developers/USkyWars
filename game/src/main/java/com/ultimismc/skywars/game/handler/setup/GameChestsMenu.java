package com.ultimismc.skywars.game.handler.setup;

import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.map.Chest;
import com.ultimismc.skywars.core.game.map.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.inventory.MenuItem;
import xyz.directplan.directlib.inventory.PaginatedMenu;
import xyz.directplan.directlib.inventory.PaginatedModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author DirectPlan
 */
public class GameChestsMenu extends PaginatedMenu<Chest> {

    private final GameServer gameServer;

    public GameChestsMenu(GameServer gameServer) {
        super(gameServer.getName() + " - " + gameServer.getServerId(), 3, PaginatedModel.DEFAULT_MODEL);
        this.gameServer = gameServer;
    }

    @Override
    public MenuItem buildContent(Player player, Chest chest) {
        Location location = chest.getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        boolean midChest = chest.isMidChest();
        MenuItem menuItem = new MenuItem(Material.CHEST, "&3Chest &b" + x + "&3, &b" + y + "&3, &b" + z + (midChest ? " &7(Middle Chest)" : ""));
        menuItem.setLore(Arrays.asList(" ", "&eClick to teleport!"));

        menuItem.setAction((item, clicker, clickedBlock, clickType) -> clicker.teleport(location));
        return menuItem;
    }

    @Override
    public Collection<Chest> getList() {
        Map map = gameServer.getMap();
        return map.getChests().values().stream().sorted((o1, o2) -> Boolean.compare(o2.isMidChest(), o1.isMidChest())).collect(Collectors.toList());
    }
}
