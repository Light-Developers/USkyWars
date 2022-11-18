package com.ultimismc.skywars.game.handler.scoreboard;

import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.game.handler.GameHandler;
import xyz.directplan.directlib.scoreboard.ScoreboardManager;

import java.util.Arrays;

/**
 * @author DirectPlan
 */
public class AccompaniedGameScoreboard extends GameScoreboard {

    public AccompaniedGameScoreboard(ScoreboardManager scoreboardManager, GameHandler gameHandler) {
        super(scoreboardManager, gameHandler);
    }

    @Override
    public GameScoreboardInfo getGameScoreboard(User user) {
        String mapName = gameServer.getMapName();
        String modeName = gameServer.getGameName();

        return new GameScoreboardInfo("&bGiga Chad", Arrays.asList("Cool man",
                " ",
                "A very good uhh, scoreboard for",
                " ",
                "&fMap: &a" + mapName,
                "&fMode: &a" + modeName));
    }
}
