package com.template.method.client.command;

import com.template.method.client.TetrisClient;
import com.template.method.client.command.Clientable;


/**
 * Command responsible for
 */
public class ClientId implements Clientable {

    private int id;

    public ClientId(int id) {
        this.id = id;
    }

    public void execute(TetrisClient tetrisClient) {
        tetrisClient.setClientId(id);
    }

    public String getMessageKey() {
        return "tetris.client.command.impl.ClientId";
    }
}
