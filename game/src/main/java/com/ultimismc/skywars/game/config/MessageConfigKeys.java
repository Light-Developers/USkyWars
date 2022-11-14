package com.ultimismc.skywars.game.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.directplan.directlib.config.ConfigEntry;
import xyz.directplan.directlib.config.replacement.ReplacementBoundary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DirectPlan
 */
@AllArgsConstructor
@Getter
public enum MessageConfigKeys implements ConfigEntry {

    SKYWARS_GAME_SCOREBOARD_DISPLAYNAME("skywars-game.scoreboard.display-name", "&b&lSKYWARS"),
    SKYWARS_GAME_WAITING_SCOREBOARD_LINES("skywars-game.scoreboard-waiting.lines",
            Arrays.asList("&7%servertime_MM/dd/yyyy% &8%server-id%",
                    " ",
                    "&fPlayers: &a%current-players/%maximum-players",
                    " ",
                    "%game-status%",
                    " ",
                    "&fMap: &a%map%",
                    "&fMode: &a%mode%",
                    " ",
                    "&3play.ultimismc.com"
                    )),
    SKYWARS_GAME_STARTED_SCOREBOARD_LINES("skywars-game.scoreboard-started.lines",
            Arrays.asList("&7%servertime_MM/dd/yyyy% &8%server-id%",
                    " ",
                    "&fNext Event:",
                    "&a%next-event%",
                    " ",
                    "&fPlayers left: &a%players-left%",
                    " ",
                    "&fMap: %map%",
                    "&fMode: %mode%",
                    " ",
                    "&3play.ultimismc.com"
                    ))

    ,

    JOIN_MESSAGE("join-message", "%player% &ehas joined (&b%current-players%&e/&b%maximum-players%&e)"),
    QUIT_MESSAGE("quit-message", "%player% &ehas quit!"),

    GAME_STARTS_IN_MESSAGE("game-starts-in-message", "&eThe game starts in %time%&e!"),

    ;




    private final String key;
    @Setter
    private Object value;
    private boolean forceEntryDeclaration;
    private final Map<String, ReplacementBoundary> replacementBoundaries = new HashMap<>();

    MessageConfigKeys(String key, Object value) {
        this(key, value, true);
    }
}
