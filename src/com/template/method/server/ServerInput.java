package com.template.method.server;

import com.template.method.server.command.ServerRequestable;
import java.io.*;
import java.net.*;


/**
 * Encapsulates server input stream and manages it
 */
public class ServerInput extends Thread {

    private Socket clientSocket;
    private ServerOutput serverOutput;
    private ObjectInputStream inputStream = null;
    public TetrisServer tetrisServer;

    public ServerInput(Socket client, ServerOutput serverOutput, TetrisServer tetrisServer) {
        this.serverOutput = serverOutput;
        this.clientSocket = client;
        this.tetrisServer = tetrisServer;

        try {
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Reads the input stream in the loop and executes the requests (only the valid
     * request thanks to ServerRequestable inheritance of such objects)
     */
    public void run() {

        while (true) {
            try {
                Object o = inputStream.readObject();

                if (o instanceof ServerRequestable) {
                    ((ServerRequestable) o).execute(tetrisServer);
                }
                // ignore other not valid requests
            }
            catch (IOException | ClassNotFoundException ioe) {
                tetrisServer.getLog().error(ioe.getMessage());
                ioe.printStackTrace();
            }
        }
    }
}
