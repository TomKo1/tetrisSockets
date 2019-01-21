package com.template.method.client;


import com.template.method.client.command.ClientRequestable;
import com.template.method.server.TetrisServer;
import com.template.method.server.command.Tickable;
import com.template.method.server.command.ServerLog;
import java.io.*;
import java.net.*;
import javax.swing.*;


/**
 *  Implements the client input behaviour as a separate thread
 */
public class ClientInput extends Thread {


    protected TetrisClient tetrisClient;
    protected ObjectInputStream in;
    protected Socket serverSocket;
    protected TetrisServer tetrisServer;


    /**
     * Initializes the object with socket bound to the server
     * and tetrisclient object
     */
    ClientInput(Socket server, TetrisClient client) {
        this.serverSocket = server;
        this.tetrisClient = client;
        try {
            this.in = new ObjectInputStream(serverSocket.getInputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Start tetris client in thread.
     */
    public void run() {
        while (true) {
            try {
                Object o = in.readObject();

                if (o instanceof Tickable) {
                    // ticable object is a request for battlefield to be repainted
                    tetrisClient.getBattleField().newRepaint();
                }
                else if (o instanceof ClientRequestable) {
                    // wait until the previous command was done
                    synchronized (tetrisClient.getClientDummy()) {
                        tetrisClient.getClientDummy().notifyAll();
                    }

                    // execute request from server
                    ClientRequestable clientRequestable = (ClientRequestable) o;

                    clientRequestable.execute(tetrisClient);

                    tetrisClient.getOutput().addSendable(new ServerLog(ServerLog.Type.INFO, clientRequestable.getMessageKey()));
                }
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(tetrisClient.getBattleField().getContentPane(), "Połączenie z serverem utracone!", "Błąd", JOptionPane.ERROR_MESSAGE);
                ioe.printStackTrace();
            }
            catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
        }
    }
}
