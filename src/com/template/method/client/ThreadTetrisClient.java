package com.template.method.client;

import com.template.method.gui.ClientFrame;


//TODO: get rid of this
/**
 *  Initializes the thread starting client object
 */
public class ThreadTetrisClient extends Thread {

    protected String host = "localhost";
    protected int port = 8181;
    protected String playerName;
    protected ClientFrame clientFrame;
    public TetrisClient tetrisClient;

    public ThreadTetrisClient(String host, int port, String clientName, ClientFrame clientFrame) {
        this.host = host;
        this.port = port;
        this.playerName = clientName;
        this.clientFrame = clientFrame;

        start();
    }

    public void run() {
        tetrisClient = new TetrisClient(host, port, clientFrame, playerName);
    }
}
