package com.template.method.gui;

import com.template.method.client.TetrisClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatFrameTest {

    @Test
    void start() {
        ChatFrame chatFrame = new ChatFrame(new TetrisClient());

        chatFrame.start();

        assertNotNull(chatFrame.getjTextArea());
        assertNotNull(chatFrame.getJTextField());
        assertNotNull(chatFrame.getTetrisClient());
        assertTrue(chatFrame.isVisible());
    }

    @Test
    void printReceivedMessage() {
        ChatFrame chatFrame = new ChatFrame(new TetrisClient());

        String testMessage = "test message";

        chatFrame.printReceivedMessage(testMessage);

        assertEquals(testMessage, chatFrame.getjTextArea().getText());


    }
}