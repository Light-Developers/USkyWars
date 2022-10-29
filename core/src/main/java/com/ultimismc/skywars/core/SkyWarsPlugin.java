package com.ultimismc.skywars.core;

import com.ultimismc.skywars.core.config.ConfigKeys;
import com.ultimismc.skywars.core.game.features.FeatureHandler;
import com.ultimismc.skywars.core.placeholders.PlaceholderExpansionHandler;
import com.ultimismc.skywars.core.placeholders.SkyWarsPlaceholderExpansion;
import com.ultimismc.skywars.core.storage.UserStorage;
import com.ultimismc.skywars.core.user.UserListener;
import com.ultimismc.skywars.core.user.UserManager;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.directplan.directlib.config.BukkitConfigHandler;
import xyz.directplan.directlib.config.ConfigHandler;
import xyz.directplan.directlib.inventory.MenuListener;
import xyz.directplan.directlib.inventory.manager.MenuManager;

import java.util.logging.Level;

/**
 * @author DirectPlan
 */
@Getter
public abstract class SkyWarsPlugin extends JavaPlugin {

    protected ConfigHandler configHandler;
    protected UserStorage storage;
    protected UserManager userManager;
    protected MenuManager menuManager;

    protected CommandHandler commandHandler;
    protected UserListener userListener;
    protected FeatureHandler featureHandler;
    protected PlaceholderExpansionHandler placeholderExpansionHandler;

    public abstract void enable();

    public abstract void disable();

    @Override
    public void onEnable() {
        configHandler = new BukkitConfigHandler(this);
        configHandler.loadConfiguration("config.yml", ConfigKeys.class);

        storage = new UserStorage(this);
        storage.connect();

        userManager = new UserManager(this);
        menuManager = new MenuManager();
        featureHandler = new FeatureHandler(this);
        commandHandler = new CommandHandler(this);
        placeholderExpansionHandler = new PlaceholderExpansionHandler();

        featureHandler.initializeFeatures();

        placeholderExpansionHandler.registerPlaceholderExpansion(new SkyWarsPlaceholderExpansion(this));
        registerListeners(new MenuListener(menuManager), userListener = new UserListener(userManager));

        enable();
    }

    @Override
    public void onDisable() {
        disable();

        configHandler.saveConfigurations();
        userManager.saveUsers();

        storage.close();
    }

    public void registerListeners(Listener... listeners) {
        PluginManager pluginManager = getServer().getPluginManager();
        for(Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }

    public void log(String message) {
        getLogger().log(Level.INFO, message);
    }
}
