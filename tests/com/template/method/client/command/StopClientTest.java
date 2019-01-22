package com.template.method.client.command;

import com.template.method.client.TetrisClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StopClientTest {

    @Test
    void execute() {
        StopClient sut = new StopClient();
        TetrisClient tc = new TetrisClient();

        sut.execute(tc);

        assertFalse(tc.isClientRunning());

    }

    @Test
    void getMessageKey() {
        StopClient sut = new StopClient();

        assertEquals("tetris.client.command.impl.StopClient", sut.getMessageKey());
    }
}