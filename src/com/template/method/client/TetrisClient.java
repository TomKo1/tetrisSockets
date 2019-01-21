package com.template.method.client;

import com.template.method.gui.ChatFrame;
import com.template.method.gui.ClientFrame;
import com.template.method.gui.battlefield.BattleField;
import com.template.method.server.command.StartGameRequest;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 *  Encapsulates client logic - client output/input
 */
public class TetrisClient implements Serializable {

    // object used for synchronization puropses
    private final Object clientDummy = new Object();
    private BattleField battleField;
    private Socket clientSocket;
    private ChatFrame chatFrame;
    private String playerName = "Player";
    private String host = "localhost";
    private int port = 8181;
    protected boolean isClientRunning;
    private ClientInput input;
    private ClientOutput output;
    protected int clientId = 0;
    protected ClientFrame clientFrame;

    public TetrisClient() { }

    public TetrisClient(String host, int port, ClientFrame clientFrame, String clientName) {
        this.host = host;
        this.port = port;
        this.clientFrame = clientFrame;
        this.playerName = clientName;
        run();
    }


    public void  printReceivedMessage(String message) {
        chatFrame.printReceivedMessage(message);
    }

    /**
     * Opens a client side connection to the server, initializes output/input objects(threads)
     * It initializes battle field, client name and sends StartGameRequest to the server.
     */
    public void run() {

        try {
            clientSocket = new Socket(host, port);

            output = new ClientOutput(clientSocket, clientDummy, playerName);
            input = new ClientInput(clientSocket, this);
            output.start();

            battleField = new BattleField(this, clientFrame);

            battleField.setVisible(true);


            input.start();

            output.setClientName(playerName);

            output.addSendable(new StartGameRequest(playerName));
        } catch(IOException e) {
            JOptionPane.showMessageDialog(clientFrame.getContentPane(), "Nie można znalezc dzialajacego servera:" + host + ":" + port, "Blad polaczenia", JOptionPane.ERROR_MESSAGE);
        }

        try {
            synchronized (clientDummy) {
                clientDummy.notifyAll();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Starts the Swing chat frame
     */
    public void showChatFrame() {

        chatFrame = new ChatFrame(this);
        EventQueue.invokeLater(() -> chatFrame.start());

    }

    /**
     * Sets the isClientRunning flag to false
     */
    public void stopGame() {
        this.isClientRunning = false;
    }

    /**
     * Shows clients their final results
     */
    public void setFinalPoints(int clientPoints) {
        this.battleField.showFinalPointDialog(clientPoints);
    }


    public Object getClientDummy() {
        return clientDummy;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public ClientOutput getOutput() {
        return output;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
