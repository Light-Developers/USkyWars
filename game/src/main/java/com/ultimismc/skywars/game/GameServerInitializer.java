package com.ultimismc.skywars.game;

import com.ultimismc.skywars.core.SkyWarsPlugin;
import com.ultimismc.skywars.core.game.GameServer;
import com.ultimismc.skywars.core.game.GameType;
import com.ultimismc.skywars.core.game.TeamType;
import com.ultimismc.skywars.core.game.map.Chest;
import com.ultimismc.skywars.core.game.map.Island;
import com.ultimismc.skywars.core.game.map.Map;
import com.ultimismc.skywars.game.config.GameConfigKeys;
import com.ultimismc.skywars.game.config.MapConfigKeys;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import xyz.directplan.directlib.CustomLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
@RequiredArgsConstructor
public class GameServerInitializer {

    private final SkyWarsPlugin plugin;

    @Getter private GameServer gameServer;

    public void initializeServer() {

        GameInfo gameInfo = loadGameInfo();
        GameType gameType = gameInfo.getGameType();
        TeamType teamType = gameInfo.getTeamType();

        String serverId = gameInfo.getServerId();

        GameMap gameMap = loadGameMap();
        if(gameMap == null) {
            return;
        }
        gameServer = new GameServer(serverId, gameType, teamType, gameMap.toMap());

        if(gameServer.isSetupMode()) {
            plugin.log("Server is on Setup Mode. ");
            return;
        }
        plugin.log("Checking redis connection...");
        // Check if redis is established
        plugin.log("Sending startup payload for server " + gameInfo.getServerId() + "...");
        // Sending message to lobby about this server...
    }

    public void finalizeServer() {
        plugin.log("Saving game map...");
        saveGameMap();

        plugin.log("Sending shutdown payload for server " + gameServer.getServerId() + "...");
        // Send shutdown message to lobby.

        plugin.log("Saving configuration files...");
    }

    private GameInfo loadGameInfo() {
        String serverId = GameConfigKeys.SERVER_ID.getStringValue();
        String gameTypeString = GameConfigKeys.GAME_TYPE.getStringValue();
        String teamTypeString = GameConfigKeys.GAME_TEAM_TYPE.getStringValue();

        GameType gameType = GameType.valueOf(gameTypeString);
        TeamType teamType = TeamType.valueOf(teamTypeString);

        return new GameInfo(serverId, gameType, teamType);
    }

    private GameMap loadGameMap() {

        String name = MapConfigKeys.MAP_NAME.getStringValue();
        List<String> serializedIslands = MapConfigKeys.MAP_SERIALIZED_ISLANDS.getStringList();
        List<String> serializedChests = MapConfigKeys.MAP_SERIALIZED_CHESTS.getStringList();

        GameMap gameMap = new GameMap(name);


        for(String serializedIsland : serializedIslands) {

            if(serializedIsland.isEmpty()) continue;
            String[] args = serializedIsland.split("/");
            String serializedLocation = args[0];
            gameMap.addIsland(serializedLocation);
        }
        String worldName = MapConfigKeys.WORLD_NAME.getStringValue();
        World world = Bukkit.getWorld(worldName);
        if(world == null) {
            plugin.shutdown("World '" + worldName + "' does not exist. Shutting down");
            return null;
        }

        for(String serializedChest : serializedChests) {
            if(serializedChest.isEmpty()) continue;
            String[] args = serializedChest.split("/");
            String serializedLocation = args[0];
            CustomLocation customLocation = CustomLocation.stringToLocation(serializedLocation);
            boolean midChest = Boolean.parseBoolean(args[1]);

            Block block = world.getBlockAt(customLocation.toBukkitLocation());
            if(!(block instanceof org.bukkit.block.Chest)) continue;

            gameMap.addChest(block, midChest);
        }
        return gameMap;
    }

    private void saveGameMap() {

        Map map = gameServer.getMap();
        MapConfigKeys.MAP_NAME.setValue(map.getName());

        List<String> serializedChests = new ArrayList<>();
        List<String> serializedIslands = new ArrayList<>();

        for(Chest chest : map.getChests().values()) {
            Location location = chest.getLocation();
            String serializedLocation = CustomLocation.locationToString(location);
            boolean midChest = chest.isMidChest();
            serializedChests.add(serializedLocation + "/" + midChest);
        }
        for(Island island : map.getIslands()) {
            Location cageLocation = island.getCageLocation();
            String serializedCageLocation = CustomLocation.locationToString(cageLocation);
            serializedIslands.add(serializedCageLocation);
        }

        MapConfigKeys.MAP_SERIALIZED_CHESTS.setValue(serializedChests);
        MapConfigKeys.MAP_SERIALIZED_ISLANDS.setValue(serializedIslands);
    }

    @Data
    private static class GameInfo {

        private final String id;
        private final GameType gameType;
        private final TeamType teamType;

        public String getServerId() {
            char gameChar = gameType.name().charAt(0);
            char teamChar = teamType.name().charAt(0);
            return id + teamChar + gameChar;
        }
    }

    private static class GameMap {

        private final Map map;

        public GameMap(String name) {
            map = new Map(name);
        }

        public void addIsland(Island island) {
            map.addIsland(island);
        }

        public void addChest(Block block, boolean midChest) {
            map.addChest(block, midChest);
        }

        public void addIsland(String serializedCageLocation) {
            CustomLocation customLocation = CustomLocation.stringToLocation(serializedCageLocation);
            Location cageLocation = customLocation.toBukkitLocation();
            addIsland(new Island(cageLocation));
        }

        public Map toMap() {
            return map;
        }
    }
}
