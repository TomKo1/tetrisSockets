package com.template.method.gui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

// TODO: extract sut to @BeforeClass?

class ClientFrameTest {


    @Test
    void hostActionShouldBeInitialized() {
        ClientFrame sut = new ClientFrame(new TetrisFrame());
        assertNotNull(sut.getHostAction());
    }

    @Test
    void startServiceActionShouldBeInitialized() {
        ClientFrame sut = new ClientFrame(new TetrisFrame());
        assertNotNull(sut.getStartServerAction());
    }

    @Test
    void objectGUIshouldBeInitializedAfterConstructing() {
        ClientFrame sut = new ClientFrame(new TetrisFrame());

        // initialized by private method createPanels
        assertNotNull(sut.getClientPanel());
        assertNotNull(sut.getClientServerPanel());
        assertNotNull(sut.getFigurePanel());
        assertNotNull(sut.getPointsPanel());

        // initiaized by initUI method
        assertNotNull(sut.getClientName());
        assertNotNull(sut.getHost());
        assertNotNull(sut.getPort());
        assertNotNull(sut.getDefaultStartServerBtn());
    }



    @Test
    void setPointsMethodShouldAddPoints() {
        ClientFrame sut = new ClientFrame(new TetrisFrame());

        sut.setPoints(100);
        JLabel label = (JLabel)sut.getPointsPanel().getComponent(1);

        assertEquals(100, Integer.parseInt(label.getText()));

    }


    @ParameterizedTest
    @ValueSource(strings = { "ff0000", "00ff00", "ffc800", "0000ff", "ffff00", "ffafaf", "404040"})
    void previewNextFigureMethodShouldPreviewProperFigures(String color) {
        ClientFrame sut = new ClientFrame(new TetrisFrame());
        Color awtColor = new Color(Integer.parseInt(color, 16));

        // check if lastFrame have proper color
        sut.previewNextFigure(awtColor);
        // check if figure was added to figure panel
        assertEquals(awtColor, sut.getLastFigure().getBlockOne().getColor());
    }


}