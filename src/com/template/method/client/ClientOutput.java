package com.template.method.client;


import com.template.method.shared.Commandable;
import com.template.method.server.command.Serverable;

import java.io.*;
import java.net.*;
import java.util.*;


/**
 *  Implements client output (which sends command to the server-side) as a separate thread
 */
public class ClientOutput extends Thread {

    private ObjectOutputStream outputStream;
    private List<Commandable> commandables;
    // synchronizing object
    private final Object clientDummy;
    private String clientName;
    //game is over boolean
    public boolean gameIsOverNow = false;


    /**
     *
     * @param socket socket object representing cient-side connection
     * @param clientDummy synchronization (mutex) object
     * @param clientName client Name
     */
    public ClientOutput(Socket socket, Object clientDummy, String clientName) {
        this.clientDummy = clientDummy;
        this.clientName = clientName;

        commandables = new ArrayList<>();

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds command to list of commands to be sent to
     * the server
     */
    public void addSendable(Serverable sendable) {
        this.commandables.add(sendable);
    }


    /**
     *  Run method
     */
    public void run() {

        while (true) {
            try {
                synchronized (clientDummy) {
                    clientDummy.wait();
                }
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            while (!commandables.isEmpty()) {

                Commandable commandable = commandables.remove(0);

                try {
                    outputStream.writeObject(commandable);
                    outputStream.flush();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
