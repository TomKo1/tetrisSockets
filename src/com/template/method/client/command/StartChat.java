package com.template.method.client.command;

import com.template.method.client.TetrisClient;

/**
 *  Request received from server when there is a need to start a chat
 */
public class StartChat implements ClientRequestable {

    public void execute(TetrisClient tetrisClient) {
        tetrisClient.showChatFrame();
    }

    public String getMessageKey() {
        return "com.template.method.client.StartChat";
    }
}
