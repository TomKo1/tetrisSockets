package com.template.method.client.command;

import com.template.method.client.TetrisClient;


/**
 *  Request received from server-side that sets the isRunning flag to be false
 *  and gently stops the game on the client-side
 */
public class StopClient implements ClientRequestable {

    public void execute(TetrisClient tetrisClient) {
        tetrisClient.stopGame();
    }

    public String getMessageKey() {
        return "tetris.client.command.impl.StopClient";
    }
}
