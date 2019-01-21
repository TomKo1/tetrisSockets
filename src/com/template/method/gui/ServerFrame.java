package com.template.method.gui;


import java.util.*;
import java.text.DateFormat;
import com.template.method.server.TetrisServer;
import com.template.method.server.ThreadServer;
import com.template.method.server.logging.Logable;


/**
 * This class represents the server part of general window
 */
public class ServerFrame implements Logable {

    // get system  line separator
    private String LINE_SEPARATOR = System.getProperty("line.separator");
    private TetrisFrame tetrisFrame;
    protected int port = 8181;
    protected int numberPlayers = 1;
    protected int playTime = 100000;
    public TetrisServer tetrisServer = null;
    private DateFormat dateFormat = DateFormat.getDateTimeInstance();


    /**
     * Initializes itself and starts Server theread with given port, numberOfPlayers, playTime and log window
     *
     * @param port port for ThreadServer
     * @param numberOfPlayers number of players in the game
     * @param playTime speed of blocks
     * @param tetrisFrame the game window
     */
    public ServerFrame(int port, int numberOfPlayers, int playTime, TetrisFrame tetrisFrame) {

        this.tetrisFrame = tetrisFrame;
        this.tetrisFrame.initServerLog();

        try {
            this.port = port;
            this.numberPlayers = numberOfPlayers;
            this.playTime = playTime;

            new ThreadServer(port, numberPlayers, playTime, this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * More convenient method to log errors from server
     * it delegates message printing to more general
     * logMessage() method
     * @param message message to be logged
     */
    //TODO: change font to be blue?
    public void info(String message) {
        logMessage(message);
    }

    /**
     * More convenient method to log errors from server
     * it delegates message printing to more general
     * logMessage() method
    */
    //TODO: change font to be red
    public void error(String message) {
        logMessage(message);
    }

    /**
     *
     * Prints log in window responsible for logging
     * messages from server
     * @param message message to be logged
     */
    public void logMessage(String message) {
        tetrisFrame.printServerLog(dateFormat.format(new Date()) + ": " + message + LINE_SEPARATOR);
    }


    public TetrisFrame getTetrisFrame() {
        return  tetrisFrame;
    }
}
