package com.template.method.server.command;


import com.template.method.server.TetrisServer;

/**
 * Request for server to start whole game
 */
public class StartGameRequest implements ServerRequestable {

    private String playerName;

    public StartGameRequest() {
    }

    public StartGameRequest(String clientName) {
        this.playerName = clientName;
    }

    public void execute(TetrisServer tetrisServer) {
        tetrisServer.getLog().info("The player \"" + playerName + "\" requests to start the game.");
    }
}
