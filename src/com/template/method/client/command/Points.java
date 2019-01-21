package com.template.method.client.command;


import com.template.method.client.TetrisClient;

/**
* Command responsible for setting points on the client side
*/
public class Points implements ClientRequestable {

    private int points;

    public Points(int points) {
        this.points = points;
    }

    public void execute(TetrisClient tetrisClient) {
        tetrisClient.setFinalPoints(points);
    }

    public String getMessageKey() {
        return "tetris.client.command.impl.Points";
    }
}
