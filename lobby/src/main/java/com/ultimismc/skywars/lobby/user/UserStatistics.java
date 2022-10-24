package com.ultimismc.skywars.lobby.user;

import com.ultimismc.skywars.lobby.game.GameStatistics;
import com.ultimismc.skywars.lobby.game.GameType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@Getter
@Setter
public class UserStatistics {

    private int level;
    private int coins, souls, tokens;

    private final Map<GameType, GameStatistics> gameStats = new HashMap<>();

    public GameStatistics getStatistics(GameType gameType) {
        return gameStats.get(gameType);
    }

    public GameStatistics getSoloStatistics() {
        return getStatistics(GameType.SOLO);
    }

    public GameStatistics getDoublesStatistics() {
        return getStatistics(GameType.DOUBLES);
    }

    public int getSoloWins() {
        GameStatistics soloStatistics = getSoloStatistics();
        if(soloStatistics == null) return 0;
        return soloStatistics.getWins();
    }

    public int getDoublesWins() {
        GameStatistics doublesStatistics = getDoublesStatistics();
        if(doublesStatistics == null) return 0;

        return doublesStatistics.getWins();
    }

    public int getSoloKills() {
        GameStatistics soloStatistics = getSoloStatistics();
        if(soloStatistics == null) return 0;

        return soloStatistics.getKills();
    }

    public int getDoublesKills() {
        GameStatistics doublesStatistics = getDoublesStatistics();
        if(doublesStatistics == null) return 0;

        return doublesStatistics.getKills();
    }
}
