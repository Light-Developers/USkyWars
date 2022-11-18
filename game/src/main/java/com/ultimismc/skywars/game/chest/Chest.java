package com.ultimismc.skywars.game.chest;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.block.Block;

/**
 * @author DirectPlan
 */
@Data
public class Chest {

    private final Block block;
    private final boolean midChest;

    public Location getLocation() {
        return block.getLocation();
    }
}
