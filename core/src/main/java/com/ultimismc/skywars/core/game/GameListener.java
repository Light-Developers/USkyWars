package com.ultimismc.skywars.core.game;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class GameListener implements Listener {

    private final UserManager userManager;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        User user = userManager.getCachedUser(player);
        if(user.isScramble()) return;

        String levelPrefix = user.getLevelDisplayName(true);

        String format = event.getFormat();
        String midSyntax = "&r";
        if(user.getRank().isDefaultRank()) {
            midSyntax = "&8┃ &r";
        }

        String displayPrefix = levelPrefix + " " + midSyntax;
        event.setFormat(PluginUtility.translateMessage(displayPrefix) + format);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
    }
}
