package com.ultimismc.gamescaler.communication.listener;

import com.ultimismc.gamescaler.Server;
import com.ultimismc.gamescaler.ServerManager;
import com.ultimismc.gamescaler.communication.ServerChannelConstants;
import com.ultimismc.gamescaler.serializer.Serializer;

/**
 * @author DirectPlan
 */
public class UpdateChannelListener<T extends Server> extends ServerListener<T> {

    public UpdateChannelListener(ServerManager<T> serverManager) {
        super(serverManager, ServerChannelConstants.SERVER_UPDATE, serverManager.getGameClazz());
    }

    @Override
    public void onMessageReceived(T server, Serializer serializer) {

        log("Some server data has been received:");
        log(" - ID: " + server.getId());
        log(" - Display Name: " + server.getDisplayName());
        log(" - Players: " + server.getOnlinePlayers() + "/" + server.getMaximumPlayers());
        log(" - A lobby: " + server.isLobby());
        log(" - Whitelisted: " + server.isWhitelisted());

        serverManager.updateServer(server);
    }
}
