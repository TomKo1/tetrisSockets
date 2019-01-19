package com.template.method.server.command.impl;

import com.template.method.server.TetrisServer;
import com.template.method.server.command.Serverable;

public class BroadcastMessage implements Serverable {

    private String message;

    public BroadcastMessage(String message) {
        this.message = message;
    }


    public void execute(TetrisServer tetrisServer) {
        tetrisServer.broadcastToAllPlayers(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
