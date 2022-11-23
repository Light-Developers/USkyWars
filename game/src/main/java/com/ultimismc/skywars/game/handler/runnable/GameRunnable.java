package com.ultimismc.skywars.game.handler.runnable;

import com.ultimismc.skywars.core.game.features.kits.Kit;
import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class GameRunnable implements Runnable {

    private final GameHandler gameHandler;

    @Override
    public void run() {

        if(!gameHandler.hasStarted()) {
            gameHandler.broadcastFunction(user -> {
                Kit kit = user.getSetting(Kit.class, "kit");
                PluginUtility.sendActionBar(user.getPlayer(), "&eSelected Kit: &a" + kit.getName());
            });
            return;
        }
        long gameTime = gameHandler.getGameTime();
        gameHandler.setGameTime(gameTime += 1000L);


        gameHandler.updateScoreboard();
    }
}
