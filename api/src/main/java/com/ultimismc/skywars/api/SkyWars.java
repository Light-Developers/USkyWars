package com.ultimismc.skywars.api;

import org.bukkit.entity.Player;

/**
 * @author DirectPlan
 */
public interface SkyWars {

    void increaseCoins(Player user, int coins);

    int getCoins(Player player);
}
