package com.template.method.gui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TetrisFrameTest {

    @Test
    void tetrisFrameShouldBeCorrectlyInitialized() {
        TetrisFrame sut = new TetrisFrame();
        assertNotNull(sut.getPanelClientServer());
        assertNotNull(sut.getClientFrame());
    }
}