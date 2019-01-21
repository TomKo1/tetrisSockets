package com.template.method.server;

import com.template.method.client.command.*;
import com.template.method.gui.figure.Figure;
import com.template.method.server.command.StartGameRequest;
import com.template.method.server.command.StopGameRequest;
import com.template.method.server.command.Logable;
import com.template.method.server.utilities.TickTask;
import java.io.*;
import java.net.*;
import java.util.*;


/**
 *  Creates server connection and encapsulates out/inputstream of
 *  the server.
 */
public class TetrisServer {

    protected Socket clientSocket = null;
    // client's out/instream wrappers
    protected List<ServerInput> serverInputs;
    protected List<ServerOutput> serverOutputs;
    // figures
    protected List<Figure> figures;
    protected int numberClients = 0;
    protected int numberPlayers = 1;
    protected ServerSocket serverSocket = null;
    // synchronization object
    protected final Object tickDummy = new Object();
    //tetris client points array
    public int[] clientPoints = null;
    // time between which clock sends tick object
    protected int time = 4000;
    // server ticker (clock)
    private TickTask ticker;
    //break tetris server
    public boolean breakServer = false;
    private Logable log;



    public TetrisServer(Logable log) {
        this.log = log;

        serverInputs = new ArrayList<>();
        serverOutputs = new ArrayList<>();

        figures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Figure f = new Figure(120, 60, 20, 20);
            figures.add(f);
        }
    }



    public void runTetrisServer(int port, int playTime) {

        try {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException ioe) {
            log.error("Tetris Server: Couldn't listen on port: " + port);
            ioe.printStackTrace();
        }

        log.info("The absence of players: " + numberPlayers);

        while (numberPlayers > 0) {
            try {
                clientSocket = serverSocket.accept();

                ServerOutput output = new ServerOutput(clientSocket, tickDummy);
                ServerInput input = new ServerInput(clientSocket, output, this);
                output.start();
                input.start();

                // send list of figures to the client
                output.addSendable(new FigureResponse(figures));
                synchronized (tickDummy) {
                    tickDummy.notifyAll();
                }

                // send id of client to client
                output.addSendable(new ClientId(numberPlayers));
                synchronized (tickDummy) {
                    tickDummy.notifyAll();
                }

                serverInputs.add(input);
                serverOutputs.add(output);

                numberPlayers--;

                log.info("The absence of players: " + numberPlayers);

                numberClients++;

                try {
                    Thread.sleep(time);
                }
                catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            catch (IOException ioe) {

                log.error("The tetris client accept failed on port: " + port);

                ioe.printStackTrace();
            }
        }

        log.info("Log on time is over.");

        log.info("The game starts with " + serverInputs.size() + " players");

        // send start requests to all clients
        for (ServerOutput serverOutput : serverOutputs) {
            serverOutput.addSendable(new StartGameRequest());
            log.info("The server sends the start game command.");
            serverOutput.addSendable(new StartChat());
            log.info("The servers sends start chat command");
        }

        // new timer task
        ticker = new TickTask(serverOutputs, 1000, tickDummy);
        ticker.start();

        while (playTime > 0) {

            if (breakServer) {
                break;
            }

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            playTime -= 1000;
        }

        // final points to all players
        int i = 0;
        for (ServerOutput serverOutput : serverOutputs) {
            serverOutput.addSendable(new Points(clientPoints[i]));

            log.info("Sending client points to each client.");

            synchronized (serverOutput.getTickDummy()) {
                serverOutput.getTickDummy().notifyAll();
            }

            i++;
        }

        log.info("The game is over.");

        try {
            Thread.sleep(2000);
        }

        catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        //send stop game command to each client
        for (ServerOutput serverOutput : serverOutputs) {

            serverOutput.addSendable(new Points(clientPoints[i]));

            synchronized (serverOutput.getTickDummy()) {
                serverOutput.getTickDummy().notifyAll();
            }

            serverOutput.addSendable(new StopGameRequest());
            synchronized (serverOutput.getTickDummy()) {
                serverOutput.getTickDummy().notifyAll();
            }
        }

        stopTick();
    }


    /**
     * Broadcast given message to all chat windows
     * @param message message to be broadcasted
     */
    public void broadcastToAllPlayers(String message) {
        for(ServerOutput serverOutput : serverOutputs) {
            serverOutput.addSendable(new ReceiveMessage(message));
        }
    }


    /**
     * Stops the ticker and the server
     */
    public void stopTick() {
        this.breakServer = true;
        this.ticker.destroyTickTask();
    }


    public void setNumberPlayers(int i) {
        this.numberPlayers = i;
        this.clientPoints = new int[i];
    }

    public List<ServerOutput> getServerOutputs() {
        return serverOutputs;
    }

    public Logable getLog() {
        return log;
    }


    public TickTask getTicker() {
        return ticker;
    }

}
