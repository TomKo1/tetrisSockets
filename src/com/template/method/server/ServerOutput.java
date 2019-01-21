package com.template.method.server;



import com.template.method.shared.Commandable;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Manages server output stream  - writes commands for clients to the stream in the loop.
 */
public class ServerOutput extends Thread {

    protected Socket clientSocket;
    protected ObjectOutputStream outputStream;
    //TODO: use java.concurent API
    protected List<Commandable> commandables;
    // synchronization object
    private final Object tickDummy;


    public ServerOutput(Socket clientSocket, Object tickDummy) {
        this.clientSocket = clientSocket;
        this.tickDummy = tickDummy;

        commandables = new ArrayList<>();

        try {
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Writes in the loop commands from list ('queue') of commands
     */
    public void run() {
        while (true) {
            try {
                synchronized (tickDummy) {
                    tickDummy.wait();
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
                    System.out.println("write object bug");
                    ioe.printStackTrace();
                }
            }
        }
    }

    /**
     * Adds to the list of commands
     *
     */
    public void addSendable(Commandable commandable) {
        commandables.add(commandable);
    }

    public Object getTickDummy() {
        return tickDummy;
    }
}
