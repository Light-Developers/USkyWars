package com.ultimismc.skywars.core.storage;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.ultimismc.skywars.core.game.features.level.Level;
import com.ultimismc.skywars.core.game.features.level.Prestige;
import com.ultimismc.skywars.core.user.User;
import com.ultimismc.skywars.core.user.UserCacheHandler;
import org.bson.Document;


import java.util.UUID;

public class UserMongoStorage extends UserCacheHandler<UUID, User> {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> usersCollection;


    public void createTable(String username, String database, String password, String host) {
        String authParams = username + ":" + password + "@";
        String authSource = "/?authSource=" + "admin";
        String uri = "mongodb://" + authParams + host + ":" + "27017" + authSource;
        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        this.mongoClient = MongoClients.create(settings);
        this.mongoDatabase = mongoClient.getDatabase(database);
        this.usersCollection = mongoDatabase.getCollection("sky_users");
    }

    public MongoClient mongo() {
        return mongoClient;
    }

    public MongoDatabase database() {
        return mongoDatabase;
    }


    public MongoCollection<Document> datadoc() {
        return usersCollection;
    }


    public boolean checkIfExist(UUID uuid) {
        return usersCollection.find(Filters.eq("uuid", uuid.toString())).iterator().hasNext();
    }

    //load user from database (mongodb)
    public User load(UUID key) {
        Document document = usersCollection.find(Filters.eq("uuid", key.toString())).first();
        if (document != null) {
            System.out.println("found docs, loading user!");
            User skyUser = new User(key);
            skyUser.setName(document.getString("name"));
            return skyUser;
        } else {
            // Handle the case where no document is found for the given key
            return new User(key);
        }
    }


    //save the user from mongodb
    public void save(User user) {
        Document document = new Document();
        document.put("uuid", user.getUuid().toString());
        document.put("name", user.getName());

        usersCollection.replaceOne(Filters.eq("uuid", user.getUuid().toString()), document, new ReplaceOptions().upsert(true));
    }
}
