package com.template.method.server;


import com.template.method.server.command.Logable;

/**
 * Separate thread which starts tetrisserver
 */
public class ThreadServer extends Thread {

    private int port;
    private int numberPlayers;
    private int playTime;
    private Logable log;

    public ThreadServer(int port, int number, int playTime, Logable log) {
        this.port = port;
        this.numberPlayers = number;
        this.playTime = playTime;
        this.log = log;

        start();
    }

    /**
     * Configures tetris server and starts it
     */
    public void run() {
        try {
            TetrisServer tetrisServer = new TetrisServer(log);
            tetrisServer.setNumberPlayers(numberPlayers);
            tetrisServer.runTetrisServer(port, playTime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
