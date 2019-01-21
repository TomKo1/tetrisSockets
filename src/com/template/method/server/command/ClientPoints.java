package com.template.method.server.command;


import com.template.method.server.TetrisServer;

/**
 * Request for server to update points for specific player
 */
public class ClientPoints implements ServerRequestable {

    private String clientName;

    private int clientID;

    private int points;

    public ClientPoints(String clientName, int clientID, int points) {
        this.clientName = clientName;
        this.clientID = clientID;
        this.points = points;
    }

    public void execute(TetrisServer tetrisServer) {

        tetrisServer.clientPoints[clientID - 1] += points;

        int max = tetrisServer.clientPoints[0];

        for (int i = 1; i < tetrisServer.clientPoints.length; i++) {
            if (max < tetrisServer.clientPoints[i]) {
                max = tetrisServer.clientPoints[i];
            }
        }

        if (max % 100 == 0) {
            tetrisServer.getTicker().accelerateTick(1.25);
        }
    }
}
