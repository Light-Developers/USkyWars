package com.ultimismc.skywars.game.events;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.game.chest.ChestRefillSkyWarsEvent;
import com.ultimismc.skywars.game.handler.GameHandler;
import lombok.Getter;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class SkyWarsEventHandler {

    private final SkyWarsPlugin plugin;
    private final GameHandler gameHandler;

    private final LinkedList<SkyWarsEvent> events = new LinkedList<>();
    private final Iterator<SkyWarsEvent> eventIterator;
    @Getter private SkyWarsEvent nextEvent;
    private long eventStartedAt;
    private BukkitTask task;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

    @Getter private final List<SkyWarsEventUpdater> updaters = new ArrayList<>();

    public SkyWarsEventHandler(SkyWarsPlugin plugin, GameHandler gameHandler) {
        this.plugin = plugin;
        this.gameHandler = gameHandler;

        events.addLast(new ChestRefillSkyWarsEvent(180000));
        events.addLast(new TestSkyWarsEvent("Gay"));
        events.addLast(new TestSkyWarsEvent("Never Gonna Give You Up"));
        events.addLast(new TestSkyWarsEvent("Morocco"));

        eventIterator = events.iterator();
    }

    public void startNextEvent() {
        if(task == null) {
            task = plugin.getServer().getScheduler().runTaskTimer(plugin, new SkyWarsEventRunnable(this), 20L, 20L);
        }
        eventStartedAt = System.currentTimeMillis();

        if(!eventIterator.hasNext()) return;
        nextEvent = eventIterator.next();
    }

    public void executeNextEvent() {
        if(nextEvent == null) return;
        nextEvent.executeEvent(gameHandler);
        startNextEvent();
    }

    public long getUntilNextEvent() {
        long timePassed = (System.currentTimeMillis() - eventStartedAt);
        return (nextEvent.getScheduledIn() - timePassed);
    }

    public String getTimeLeftForNextEvent() {
        long timeLeft = getUntilNextEvent();
        return dateFormat.format(timeLeft);
    }

    public String getNextEventDisplayFormat() {
        return (nextEvent.getName() + " " + getTimeLeftForNextEvent());
    }

    public void addUpdater(SkyWarsEventUpdater eventUpdater) {
        eventUpdater.update(this);
        updaters.add(eventUpdater);
    }

    public void removeUpdater(SkyWarsEventUpdater eventUpdater) {
        updaters.remove(eventUpdater);
    }
}
