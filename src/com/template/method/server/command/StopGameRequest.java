package com.template.method.server.command;

import com.template.method.client.command.StopClient;
import com.template.method.server.TetrisServer;
import com.template.method.server.ServerOutput;


/**
 * The request for server to gently close connection and end the whole game
 */
public class StopGameRequest implements ServerRequestable {

    private String playerName;

    public StopGameRequest() {
    }

    public StopGameRequest(String playerName) {
        this.playerName = playerName;
    }

    public void execute(TetrisServer tetrisServer) {

        if (playerName != null) {
            tetrisServer.getLog().info("The player \"" + playerName + "\" requests to end the game.");
        }

        for (ServerOutput serverOutput : tetrisServer.getServerOutputs()) {
            serverOutput.addSendable(new StopClient());

            synchronized (serverOutput.getTickDummy()) {
                serverOutput.getTickDummy().notifyAll();
            }
        }

        tetrisServer.breakServer = true;
        tetrisServer.getTicker().destroyTickTask();
    }
}
