package com.template.method.gui;

import java.awt.*;
import java.util.*;
import java.text.DateFormat;
import javax.swing.*;

import com.template.method.server.TetrisServer;
import com.template.method.server.ThreadServer;
import com.template.method.server.logging.Logable;


/**
 * <p>Headline: GUI.ServerFrame</p>
 * <p>Description: This class implements a Tetris Server GUI.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Organisation: Tetris Connection</p>
 *
 * @author Gath Sebastian, gath, gath@inf.uni-konstanz.de, 01/556108
 * @author Hug Holger, hug, hug@inf.uni-konstanz.de, 01/566368
 * @author Raedle Roman, raedler, raedler@inf.uni-konstanz.de, 01/546759
 * @author Weiler Andreas, weiler, weiler@inf.uni-konstanz.de, 01/560182
 * @version 1.0
 */

public class ServerFrame extends JFrame implements Logable {

    private String LINE_SEPARATOR = System.getProperty("line.separator");

    private TetrisFrame tetrisFrame;

    //text area for server client traffic
    private JTextArea viewLog;

    //server port
    protected int port = 8181;

    //number of tetris player used for tetris server
    protected int numberPlayers = 1;

    //play time
    protected int playTime = 100000;

    //tetris server
    public TetrisServer tetrisServer = null;

    private DateFormat dateFormat = DateFormat.getDateTimeInstance();

    /**
     * Initialization of tetris server tetrisFrame with port, number of players, play time and tetris tetrisFrame window.
     */
    public ServerFrame(int port, int number, int playTime, TetrisFrame tetrisFrame) {

        this.tetrisFrame = tetrisFrame;
        this.tetrisFrame.initServerLog();

        try {
            this.port = port;
            this.numberPlayers = number;
            this.playTime = playTime;

            new ThreadServer(port, numberPlayers, playTime, this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void info(String message) {
        logMessage(message);
    }

    public void error(String message) {
        logMessage(message);
    }

    /**
     * Prints parameter message to message area for server client traffic.
     *
     * @param message Strifng message to print on message area
     */
    public void logMessage(String message) {
        tetrisFrame.printServerLog(dateFormat.format(new Date()) + ": " + message + LINE_SEPARATOR);
    }
}
