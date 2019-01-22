package com.template.method.client.command;

import com.template.method.client.TetrisClient;
import com.template.method.gui.ChatFrame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartChatTest {

    @Test
    void execute() {
        StartChat sut = new StartChat();
        TetrisClient tc = new TetrisClient();
        tc.setChatFrame(new ChatFrame(tc));

        sut.execute(tc);

        assertTrue(tc.isChatFrameDisplayed());
    }

    @Test
    void getMessageKey() {
            StartChat sut = new StartChat();

            assertEquals("com.template.method.client.StartChat", sut.getMessageKey());
    }
}