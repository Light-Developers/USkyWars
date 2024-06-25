package com.ultimismc.skywars.core.storage;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.user.User;
import xyz.directplan.directlib.storage.StorageConnection;
import xyz.directplan.directlib.storage.misc.ConnectionCredentials;

import java.util.UUID;

/**
 * @author DirectPlan
 */
public class MongoStorageConnection extends StorageConnection {

    private final SkyWarsPlugin plugin;
    private final UserMongoStorage userMongoStorage;

    public MongoStorageConnection(SkyWarsPlugin plugin, ConnectionCredentials credentials) {
        super("MongoDB", credentials);
        this.userMongoStorage = new UserMongoStorage();
        this.plugin = plugin;
    }

    @Override
    public void connect(String host, String user, String password,String database) {
        userMongoStorage.createTable(host,user,password,database);
    }

    public User loadUser(UUID uuid) {
        return userMongoStorage.load(uuid);
    }

    public void saveUser(User user) {
        userMongoStorage.save(user);
    }

    @Override
    public void close() {

    }
}
