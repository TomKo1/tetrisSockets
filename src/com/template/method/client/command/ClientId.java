package com.template.method.client.command;

import com.template.method.client.TetrisClient;

/**
 * Command responsible for setting the client id
 * on the client side - send by server
 */
public class ClientId implements ClientRequestable {

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
