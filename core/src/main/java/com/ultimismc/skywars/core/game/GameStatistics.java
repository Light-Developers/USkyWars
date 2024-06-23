package com.ultimismc.skywars.core.game;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DirectPlan
 */
@Getter
@Setter
public class GameStatistics {

    private int wins, losses;
    private int winstreak, bestWinstreak;

    private int kills, deaths, assists;
    private int bowKills, voidKills;
    private int arrowsShot, arrowsHit;
    private int chestsOpened;

    public void increaseWin() {
        wins++;
    }

    public void increaseKill() {
        kills++;
    }

    public void increaseAssists() {
        assists++;
    }

    public void increaseChestsOpened() {
        chestsOpened++;
    }
}
