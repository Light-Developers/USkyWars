package xyz.directplan.directlib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * @author DirectPlan
 */
public class TagUtil {

    private final static String TAG_PREFIX = "_dlib_team";

    public static void setTag(Player player, Player other, String group, String tag) {
        Scoreboard scoreboard = player.getScoreboard();
        if(scoreboard == null) {
            scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        }

        String teamTag = group + TAG_PREFIX;
        Team team = scoreboard.getTeam(teamTag);
        if(team == null) {
            team = scoreboard.registerNewTeam(teamTag);
        }
        team.setPrefix(tag);

        String otherName = other.getName();
        if(team.hasEntry(otherName)) {
            return;
        }
        team.addEntry(otherName);

        player.setScoreboard(scoreboard);
    }

    public static void clearTag(Player player, String group) {
        Scoreboard scoreboard = player.getScoreboard();
        if(scoreboard == null) return;

        String teamTag = group + TAG_PREFIX;
        Team team = scoreboard.getTeam(teamTag);
        if(team == null) return;
        team.removeEntry(player.getName());
    }
}
