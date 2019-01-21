package com.template.method.client.command;

import com.template.method.client.TetrisClient;
import com.template.method.client.command.Clientable;

public class StartChat implements Clientable {

    public void execute(TetrisClient tetrisClient) {
        tetrisClient.showChatFrame();
    }

    public String getMessageKey() {
        return "com.template.method.client.StartChat";
    }
}
