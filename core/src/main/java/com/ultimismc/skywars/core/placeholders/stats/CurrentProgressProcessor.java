package com.ultimismc.skywars.core.placeholders.stats;

import com.ultimismc.skywars.core.placeholders.UserPlaceholderProcessor;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserStatistics;

/**
 * @author DirectPlan
 */
public class CurrentProgressProcessor implements UserPlaceholderProcessor {

    @Override
    public String process(User user, String value) {
        UserStatistics userStatistics = user.getStatistics();
        int nextLevelProgress = userStatistics.getNextLevelProgress();
        if(nextLevelProgress < 0) {
            return "MAXED";
        }
        return String.valueOf(nextLevelProgress);
    }
}
