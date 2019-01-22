package com.template.method.gui.battlefield;

import com.template.method.client.TetrisClient;
import com.template.method.gui.ClientFrame;
import com.template.method.gui.TetrisFrame;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class BattleFieldKeyListenerTest {

    @Test
    void shouldMoveBLockRightWhenRightArrowClicked() {
        KeyEvent ke = new KeyEvent(new JButton(), 23, 3, 3, KeyEvent.VK_RIGHT, '→');
        BattleFieldKeyListener sut = new BattleFieldKeyListener(new BattleField(new TetrisClient(), new ClientFrame(new TetrisFrame())));

        sut.keyPressed(ke);

        //TODO: implement this tests

    }
}

