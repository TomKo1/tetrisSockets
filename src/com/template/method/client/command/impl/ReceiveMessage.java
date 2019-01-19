package com.template.method.client.command.impl;

import com.template.method.client.TetrisClient;
import com.template.method.client.command.Clientable;

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

    public void setMessage(String message) {
        this.message = message;
    }
}
