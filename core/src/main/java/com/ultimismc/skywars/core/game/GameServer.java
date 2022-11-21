package com.ultimismc.skywars.core.game;

import lombok.Data;

/**
 * @author DirectPlan
 */
@Data
public class GameServer {

    private final String serverId;
    private final GameType gameType;
    private final TeamType teamType;

    private final Map map;

    private int spectators;

    private boolean setupMode;

    public String getGameName() {
        return gameType.getName();
    }

    public String getGameDisplayName() {
        return gameType.getDisplayName();
    }

    public String getTeamName() {
        return teamType.getName();
    }

    public String getName() {
        return teamType.getName() + " " + gameType.getName();
    }

    public int getMaximumPlayers() {
        return teamType.getMaximumPlayers();
    }

    public String getMapName() {
        return map.getName();
    }

    public void setMapName(String name) {
        map.setName(name);
    }

    public boolean isSoloGame() {
        return teamType.isSolo();
    }
}
