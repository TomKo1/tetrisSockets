package com.template.method.server.command;

import com.template.method.server.TetrisServer;

/**
 * Request for server to broadcast message from chat
 */
public class BroadcastMessage implements ServerRequestable {

    private String message;

    public BroadcastMessage(String message) {
        this.message = message;
    }


    public void execute(TetrisServer tetrisServer) {
        tetrisServer.broadcastToAllPlayers(message);
    }
}
