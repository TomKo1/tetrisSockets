package com.template.method.gui.figure;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

//TODO: implement this tests
class FigureTest {


    @Test
    void makeFigure() {
        Figure sut = new Figure(20,21,23,24);



    }

    @Test
    void makeFigureOne() {
        Figure sut = new Figure(20,21,23,24);

        // figure position is by default 0
        sut.makeFigureOne();

        assertEquals(Color.RED, sut.getBlockOne().getColor());
        assertEquals(Color.RED, sut.getBlockTwo().getColor());
        assertEquals(Color.RED, sut.getBlockThree().getColor());
        assertEquals(Color.RED, sut.getBlockFour().getColor());
        assertEquals(Color.RED, sut.getRandomFigureColor());
    }

    @Test
    void makeFigureTwo() {
        Figure sut = new Figure(20,21,23,24);

        // figure position is by default 0
        sut.makeFigureTwo();

        assertEquals(Color.GREEN, sut.getBlockOne().getColor());
        assertEquals(Color.GREEN, sut.getBlockTwo().getColor());
        assertEquals(Color.GREEN, sut.getBlockThree().getColor());
        assertEquals(Color.GREEN, sut.getBlockFour().getColor());
        assertEquals(Color.GREEN, sut.getRandomFigureColor());
    }

    @Test
    void makeFigureThree() {
        Figure sut = new Figure(20,21,23,24);

        // figure position is by default 0
        sut.makeFigureThree();

        assertEquals(Color.ORANGE, sut.getBlockOne().getColor());
        assertEquals(Color.ORANGE, sut.getBlockTwo().getColor());
        assertEquals(Color.ORANGE, sut.getBlockThree().getColor());
        assertEquals(Color.ORANGE, sut.getBlockFour().getColor());
        assertEquals(Color.ORANGE, sut.getRandomFigureColor());
    }

    @Test
    void makeFigureFour() {
        Figure sut = new Figure(20,21,23,24);

        // figure position is by default 0
        sut.makeFigureFour();

        assertEquals(Color.BLUE, sut.getBlockOne().getColor());
        assertEquals(Color.BLUE, sut.getBlockTwo().getColor());
        assertEquals(Color.BLUE, sut.getBlockThree().getColor());
        assertEquals(Color.BLUE, sut.getBlockFour().getColor());
        assertEquals(Color.BLUE, sut.getRandomFigureColor());
    }

    @Test
    void makeFigureFive() {
        Figure sut = new Figure(20,21,23,24);

        // figure position is by default 0
        sut.makeFigureFive();

        assertEquals(Color.YELLOW, sut.getBlockOne().getColor());
        assertEquals(Color.YELLOW, sut.getBlockTwo().getColor());
        assertEquals(Color.YELLOW, sut.getBlockThree().getColor());
        assertEquals(Color.YELLOW, sut.getBlockFour().getColor());
        assertEquals(Color.YELLOW, sut.getRandomFigureColor());
    }

    @Test
    void makeFigureSix() {
        Figure sut = new Figure(20,21,23,24);

        // figure position is by default 0
        sut.makeFigureSix();

        assertEquals(Color.PINK, sut.getBlockOne().getColor());
        assertEquals(Color.PINK, sut.getBlockTwo().getColor());
        assertEquals(Color.PINK, sut.getBlockThree().getColor());
        assertEquals(Color.PINK, sut.getBlockFour().getColor());
        assertEquals(Color.PINK, sut.getRandomFigureColor());
    }

    @Test
    void makeFigureSeven() {
        Figure sut = new Figure(20,21,23,24);

        // figure position is by default 0
        sut.makeFigureSeven();

        assertEquals(Color.DARK_GRAY, sut.getBlockOne().getColor());
        assertEquals(Color.DARK_GRAY, sut.getBlockTwo().getColor());
        assertEquals(Color.DARK_GRAY, sut.getBlockThree().getColor());
        assertEquals(Color.DARK_GRAY, sut.getBlockFour().getColor());
        assertEquals(Color.DARK_GRAY, sut.getRandomFigureColor());

    }

    @Test
    void moveDown() {
    }

    @Test
    void moveLeft() {
    }

    @Test
    void moveRight() {
    }

    @Test
    void rotateLeft() {
    }

    @Test
    void preRotate() {
    }

    @Test
    void rotateRight() {
    }

    @Test
    void removeBlocks() {
    }
}