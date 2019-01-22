package com.template.method.client.command;

import com.template.method.client.TetrisClient;
import com.template.method.gui.ClientFrame;
import com.template.method.gui.TetrisFrame;
import com.template.method.gui.battlefield.BattleField;
import com.template.method.gui.figure.Figure;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FigureResponseTest {

    @Test
    void execute() {
        List<Figure> figurList = new ArrayList<>();
        figurList.add(new Figure());
        figurList.add(new Figure());
        figurList.add(new Figure());

        FigureResponse sut = new FigureResponse(figurList);
        TetrisClient tc = new TetrisClient();
        tc.setBattleField(new BattleField(new TetrisClient(), new ClientFrame(new TetrisFrame())));

        sut.execute(tc);

        assertIterableEquals(figurList, tc.getBattleField().getFigures());

    }

    @Test
    void getMessageKey() {
        FigureResponse sut = new FigureResponse(new ArrayList<Figure>());

        assertEquals("tetris.client.command.impl.FigureResponse", sut.getMessageKey());
    }



}