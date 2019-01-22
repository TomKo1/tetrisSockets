package com.template.method.client.command;

import com.template.method.client.TetrisClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientIdTest {

    @Test
    void execute() {
        ClientId sut = new ClientId(20);
        TetrisClient tc = new TetrisClient();

        sut.execute(tc);

        assertEquals(20, tc.getClientId());

    }

    @Test
    void getMessageKey() {
        ClientId sut = new ClientId(20);

        assertEquals("tetris.client.command.impl.ClientId", sut.getMessageKey());
    }
}