package com.ultimismc.skywars.core.game.currency;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;
import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * @author DirectPlan
 */
@Getter
public class CoinCurrency implements Currency {

    private final ChatColor currencyColor = ChatColor.GOLD;

    @Override
    public void increaseCurrency(User user, int amount) {
        UserStatistics userStatistics = user.getStatistics();
        userStatistics.increaseCoins(amount);
    }

    @Override
    public void decreaseCurrency(User user, int amount) {
        UserStatistics userStatistics = user.getStatistics();
        userStatistics.decreaseCoins(amount);
    }

    @Override
    public boolean canAfford(User user, int amount) {
        UserStatistics userStatistics = user.getStatistics();
        return userStatistics.getCoins() >= amount;
    }
}
