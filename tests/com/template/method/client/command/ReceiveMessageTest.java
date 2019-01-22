package com.template.method.client.command;

import com.template.method.client.TetrisClient;
import com.template.method.gui.ChatFrame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceiveMessageTest {

    @Test
    void execute() {
        String testMessage = "test message";
        ReceiveMessage sut = new ReceiveMessage(testMessage);
        TetrisClient tc = new TetrisClient();
        tc.setChatFrame(new ChatFrame(tc));

        sut.execute(tc);

        assertEquals(testMessage, tc.getChatFrame().getjTextArea().getText());
    }

    @Test
    void getMessageKey() {
        String testMessage = "test message";
        ReceiveMessage sut = new ReceiveMessage(testMessage);

        assertEquals("tetris.client.command.impl.ReceiveMessage", sut.getMessageKey());
    }
}