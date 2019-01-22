package com.template.method.client.command;

import com.template.method.client.TetrisClient;
import com.template.method.gui.ClientFrame;
import com.template.method.gui.TetrisFrame;
import com.template.method.gui.battlefield.BattleField;
import com.template.method.gui.figure.Figure;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PointsTest {

    @Test
    void execute() {
        Points sut = new Points(3);
        TetrisClient tc = new TetrisClient();
        tc.setBattleField(new BattleField(new TetrisClient(), new ClientFrame(new TetrisFrame())));

        sut.execute(tc);

        assertTrue(tc.getBattleField().isShowingDialog());

    }

    @Test
    void getMessageKey() {

        Points sut = new Points(3);

        assertEquals("tetris.client.command.impl.Points", sut.getMessageKey());
    }
}