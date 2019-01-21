package com.template.method.client.command;

import com.template.method.client.TetrisClient;

/**
 *  Command send by server in order to receive message that was broadcasted across the room
 */
public class ReceiveMessage implements Clientable {

    private String message;

    public ReceiveMessage(String message) {
        this.message = message;
    }

    public void execute(TetrisClient tetrisClient) {
        tetrisClient.printReceivedMessage(message);
    }

    public String getMessageKey() {
        return "tetris.client.command.impl.ReceiveMessage";
    }
}
