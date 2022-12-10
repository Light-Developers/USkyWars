package com.ultimismc.skywars.game.mode;

import com.ultimismc.skywars.game.handler.AbstractGame;
import com.ultimismc.skywars.game.handler.GameHandler;
import com.ultimismc.skywars.game.user.UserGameSession;
import org.bukkit.entity.Player;
import xyz.directplan.directlib.PluginUtility;

/**
 * @author DirectPlan
 */
public class InsaneGame extends AbstractGame {

    public InsaneGame(GameHandler gameHandler) {
        super(gameHandler, new InsaneChestRegistry());
    }

    @Override
    public void prepareUser(UserGameSession user) {
        super.prepareUser(user);

    }

    @Override
    public void startGame() {
        super.startGame();

        gameHandler.broadcastFunction(user -> {
            Player player = user.getPlayer();
            PluginUtility.sendTitle(player, 20, 40, 20, "&c&lINSANE MODE");
        });
    }
}
