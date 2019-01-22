package com.template.method.gui.figure;

import java.awt.*;
import javax.swing.*;
import java.io.*;


/**
 *  Class encapsulating bock creating and managment
 */
public class Figure extends JComponent implements Serializable {

    private Block blockOne;
    private Block blockTwo;
    private Block blockThree;
    private Block blockFour;
    //rotate position of the tetris figure
    private int figurePosition = 0;
    //color of the tetris figure
    protected Color randomFigureColor = null;
    // figure type 1,2,3,4,5,6 or 7
    protected int figureType = 0;
    //x start position of the figure on the  battle field
    protected int startX = 20;
    //y start position of the figure on the battle field
    private int startY = 20;
    //tetris block width
    protected int blockWidth = 20;

    //tetris block heigth
    protected int blockHeight = 20;

    //size of each block
    protected int blockSize = 20;

    //add x value for shift left, right and down
    protected int addX = 20;

    //add x value for shift left, right and down
    protected int addY = 20;

    //static tetris block width -> addForInfoPanel
    protected static int sBlockWidth = 20;

    //static tetris block height -> addForInfoPanel
    protected static int sBlockHeight = 20;


    /**
     * Initializes the tetris figure with size and start position on the battle field.
     *
     * @param startX int x start position of figure on the tetris battle field
     * @param startY int y start position of figure on the tetris battle field
     * @param width  int Width of each  block
     * @param height int Height of each  block
     */
    public Figure(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.blockWidth = width;
        this.blockHeight = height;
        this.addX = width;
        this.addY = height;
        this.blockSize = ((width + height) / 2);
        this.makeFigure();
    }

    public Figure() {}

    /**
     * Force swing component repaint

     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Spawns the first figure (from one to seven)
     *
     */
    public void makeFigure() {
        this.figureType = (int) (Math.random() * 8);
        if (this.figureType == 0) {
            this.makeFigure();
        }
        else {
            switch (this.figureType) {
                case 1:
                    this.makeFigureOne();
                    break;
                case 2:
                    this.makeFigureTwo();
                    break;
                case 3:
                    this.makeFigureThree();
                    break;
                case 4:
                    this.makeFigureFour();
                    break;
                case 5:
                    this.makeFigureFive();
                    break;
                case 6:
                    this.makeFigureSix();
                    break;
                case 7:
                    this.makeFigureSeven();
                    break;
            }
        }
    }

    /**
     * Creates figure one
     */
    public void makeFigureOne() {
        figurePosition %= 2;
        if (figurePosition == 0) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.RED);
            this.blockTwo = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.RED);
            this.blockThree = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.RED);
            this.blockFour = new Block(blockWidth, blockHeight, startX - (2 * addX), startY, Color.RED);
        }
        else if (figurePosition == 1) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.RED);
            this.blockTwo = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.RED);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.RED);
            this.blockFour = new Block(blockWidth, blockHeight, startX, startY - (2 * addY), Color.RED);
        }
        this.randomFigureColor = Color.RED;
        this.add(blockOne);
        this.add(blockTwo);
        this.add(blockThree);
        this.add(blockFour);
    }

    /**
     * Creates figure two
     */
    public void makeFigureTwo() {
        if (figurePosition == 0) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.GREEN);
            this.blockTwo = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.GREEN);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.GREEN);
            this.blockFour = new Block(blockWidth, blockHeight, startX, startY - (2 * addY), Color.GREEN);
        }
        else if (figurePosition == 1) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.GREEN);
            this.blockTwo = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.GREEN);
            this.blockThree = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.GREEN);
            this.blockFour = new Block(blockWidth, blockHeight, startX + (2 * addX), startY, Color.GREEN);
        }
        else if (this.figurePosition == 2) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.GREEN);
            this.blockTwo = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.GREEN);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.GREEN);
            this.blockFour = new Block(blockWidth, blockHeight, startX, startY + (2 * addY), Color.GREEN);
        }
        else if (this.figurePosition == 3) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.GREEN);
            this.blockTwo = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.GREEN);
            this.blockThree = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.GREEN);
            this.blockFour = new Block(blockWidth, blockHeight, startX - (2 * addX), startY, Color.GREEN);
        }
        this.randomFigureColor = Color.GREEN;
        this.add(blockOne);
        this.add(blockTwo);
        this.add(blockThree);
        this.add(blockFour);
    }

    /**
     * Creates figure three
     */
    public void makeFigureThree() {
        if (this.figurePosition == 0) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.ORANGE);
            this.blockTwo = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.ORANGE);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.ORANGE);
            this.blockFour = new Block(blockWidth, blockHeight, startX, startY - (2 * addY), Color.ORANGE);
        }
        else if (this.figurePosition == 1) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.ORANGE);
            this.blockTwo = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.ORANGE);
            this.blockThree = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.ORANGE);
            this.blockFour = new Block(blockWidth, blockHeight, startX + (2 * addX), startY, Color.ORANGE);
        }
        else if (this.figurePosition == 2) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.ORANGE);
            this.blockTwo = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.ORANGE);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.ORANGE);
            this.blockFour = new Block(blockWidth, blockHeight, startX, startY + (2 * addY), Color.ORANGE);
        }
        else if (this.figurePosition == 3) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.ORANGE);
            this.blockTwo = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.ORANGE);
            this.blockThree = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.ORANGE);
            this.blockFour = new Block(blockWidth, blockHeight, startX - (2 * addX), startY, Color.ORANGE);
        }
        this.randomFigureColor = Color.ORANGE;
        this.add(blockOne);
        this.add(blockTwo);
        this.add(blockThree);
        this.add(blockFour);
    }

    /**
     * Creates figure four
     */
    public void makeFigureFour() {
        if (this.figurePosition == 0) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.BLUE);
            this.blockTwo = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.BLUE);
            this.blockThree = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.BLUE);
            this.blockFour = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.BLUE);
        }
        else if (this.figurePosition == 1) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.BLUE);
            this.blockTwo = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.BLUE);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.BLUE);
            this.blockFour = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.BLUE);
        }
        else if (this.figurePosition == 2) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.BLUE);
            this.blockTwo = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.BLUE);
            this.blockThree = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.BLUE);
            this.blockFour = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.BLUE);
        }
        else if (this.figurePosition == 3) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.BLUE);
            this.blockTwo = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.BLUE);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.BLUE);
            this.blockFour = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.BLUE);
        }
        this.randomFigureColor = Color.BLUE;
        this.add(blockOne);
        this.add(blockTwo);
        this.add(blockThree);
        this.add(blockFour);
    }

    /**
     * Creates figure five
     */
    public void makeFigureFive() {
        figurePosition %= 4;
        if (this.figurePosition == 0) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.YELLOW);
            this.blockTwo = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.YELLOW);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.YELLOW);
            this.blockFour = new Block(blockWidth, blockHeight, startX + (1 * addX), startY - (1 * addY), Color.YELLOW);
        }
        this.randomFigureColor = Color.YELLOW;
        this.add(blockOne);
        this.add(blockTwo);
        this.add(blockThree);
        this.add(blockFour);
    }

    /**
     * Creates figure six
     */
    public void makeFigureSix() {
        figurePosition %= 2;
        if (this.figurePosition == 0) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.PINK);
            this.blockTwo = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.PINK);
            this.blockThree = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.PINK);
            this.blockFour = new Block(blockWidth, blockHeight, startX - (1 * addX), startY - (1 * addY), Color.PINK);
        }
        else if (this.figurePosition == 1) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.PINK);
            this.blockTwo = new Block(blockWidth, blockHeight, startX - (1 * addX), startY, Color.PINK);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.PINK);
            this.blockFour = new Block(blockWidth, blockHeight, startX + (1 * addX), startY - (1 * addY), Color.PINK);
        }
        this.randomFigureColor = Color.PINK;
        this.add(blockOne);
        this.add(blockTwo);
        this.add(blockThree);
        this.add(blockFour);
    }

    /**
     * Creates figure three
     */
    public void makeFigureSeven() {
        figurePosition %= 2;
        if (this.figurePosition == 0) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.DARK_GRAY);
            this.blockTwo = new Block(blockWidth, blockHeight, startX, startY + (1 * addY), Color.DARK_GRAY);
            this.blockThree = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.DARK_GRAY);
            this.blockFour = new Block(blockWidth, blockHeight, startX + (1 * addX), startY - (1 * addY), Color.DARK_GRAY);
        }
        else if (this.figurePosition == 1) {
            this.blockOne = new Block(blockWidth, blockHeight, startX, startY, Color.DARK_GRAY);
            this.blockTwo = new Block(blockWidth, blockHeight, startX + (1 * addX), startY, Color.DARK_GRAY);
            this.blockThree = new Block(blockWidth, blockHeight, startX, startY - (1 * addY), Color.DARK_GRAY);
            this.blockFour = new Block(blockWidth, blockHeight, startX - (1 * addX), startY - (1 * addY), Color.DARK_GRAY);
        }
        this.randomFigureColor = Color.DARK_GRAY;
        this.add(blockOne);
        this.add(blockTwo);
        this.add(blockThree);
        this.add(blockFour);
    }


    /**
     * Returns  figure color.
     */
    public Color getColor() {
        return this.randomFigureColor;
    }

    /**
     * Returns the highest x-coordinate of all four tetris blocks.
     */
    public int getHighestX() {
        int max = this.blockOne.getX();
        if (this.blockTwo.getX() > max) {
            max = this.blockTwo.getX();
        }
        if (this.blockThree.getX() > max) {
            max = this.blockThree.getX();
        }
        if (this.blockFour.getX() > max) {
            max = this.blockFour.getX();
        }
        return max;
    }

    /**
     * Returns the lowest x-coordinate of all four tetris blocks.
     */
    public int getLowestX() {
        int min = this.blockOne.getX();
        if (this.blockTwo.getX() < min) {
            min = this.blockTwo.getX();
        }
        if (this.blockThree.getX() < min) {
            min = this.blockThree.getX();
        }
        if (this.blockFour.getX() < min) {
            min = this.blockFour.getX();
        }

        return min;
    }

    /**
     * Returns the highest y-coordinate of all four tetris blocks.
     */
    public int getHighestY() {
        int max = this.blockOne.getY();
        if (this.blockTwo.getY() > max) {
            max = this.blockTwo.getY();
        }
        if (this.blockThree.getY() > max) {
            max = this.blockThree.getY();
        }
        if (this.blockFour.getY() > max) {
            max = this.blockFour.getY();
        }
        return max;
    }


    /**
     * Moves figure down
     */
    public void moveDown() {
        this.setLocation(this.getX(), this.getY() + (1 * addY));
        this.repaint();
    }

    /**
     * Moves figure left
     */
    public void moveLeft() {
        this.setLocation(this.getX() - (1 * addX), this.getY());
        this.repaint();
    }

    /**
     * Moves figure right
     */
    public void moveRight() {
        this.setLocation(this.getX() + (1 * addX), this.getY());
        this.repaint();
    }

    /**
     * Rotates the figure to the left
     */
    public void rotateLeft() {
        this.removeAll();
        if (this.randomFigureColor.equals(Color.RED)) {
            this.figurePosition = (this.figurePosition + 1) % 2;
            this.makeFigureOne();
        }
        else if (this.randomFigureColor.equals(Color.GREEN)) {
            this.figurePosition = (this.figurePosition + 1) % 4;
            this.makeFigureTwo();
        }
        else if (this.randomFigureColor.equals(Color.ORANGE)) {
            this.figurePosition = (this.figurePosition + 1) % 4;
            this.makeFigureThree();
        }
        else if (this.randomFigureColor.equals(Color.BLUE)) {
            this.figurePosition = (this.figurePosition + 1) % 4;
            this.makeFigureFour();
        }
        else if (this.randomFigureColor.equals(Color.YELLOW)) {
            this.figurePosition = (this.figurePosition + 1) % 4;
            this.makeFigureFive();
        }
        else if (this.randomFigureColor.equals(Color.PINK)) {
            this.figurePosition = (this.figurePosition + 1) % 2;
            this.makeFigureSix();
        }
        else if (this.randomFigureColor.equals(Color.DARK_GRAY)) {
            this.figurePosition = (this.figurePosition + 1) % 2;
            this.makeFigureSeven();
        }
        this.repaint();
    }

    /**
     * Check if the rotation is allowed
     *
     * @param color color of the block used to check what kind of block is on the board
     * @return int[] positions of the block after rotation
     */
    public int[] preRotate(Color color) {
        int position = this.figurePosition;
        int[] preX = new int[2];
        int preLowestX = 0;
        int preHighestX = 420;
        if (color.equals(Color.RED)) {
            if (position == 0) {
                position = 1;
            }
            else if (position == 1) {
                position = 0;
            }
            if (position == 0) {
                preLowestX = this.getX() + (3 * addX);
                preHighestX = this.getX() + (6 * addX);
            }
            else if (position == 1) {
                preLowestX = this.getX() + (5 * addX);
                preHighestX = this.getX() + (6 * addX);
            }
        }
        else if (color.equals(Color.GREEN)) {
            if (position == 0) {
                position = 1;
            }
            else if (position == 1) {
                position = 2;
            }
            else if (position == 2) {
                position = 3;
            }
            else if (position == 3) {
                position = 0;
            }
            if (position == 0) {
                preLowestX = this.getX() + (3 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
            else if (position == 1) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (7 * addX);
            }
            else if (position == 2) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
            else if (position == 3) {
                preLowestX = this.getX() + (3 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
        }
        else if (color.equals(Color.ORANGE)) {
            if (position == 0) {
                position = 1;
            }
            else if (position == 1) {
                position = 2;
            }
            else if (position == 2) {
                position = 3;
            }
            else if (position == 3) {
                position = 0;
            }
            if (position == 0) {
                preLowestX = this.getX() + (3 * addX);
                preHighestX = this.getX() + (6 * addX);
            }
            else if (position == 1) {
                preLowestX = this.getX() + (5 * addX);
                preHighestX = this.getX() + (7 * addX);
            }
            else if (position == 2) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
            else if (position == 3) {
                preLowestX = this.getX() + (3 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
        }
        else if (color.equals(Color.BLUE)) {
            if (position == 0) {
                position = 1;
            }
            else if (position == 1) {
                position = 2;
            }
            else if (position == 2) {
                position = 3;
            }
            else if (position == 3) {
                position = 0;
            }
            if (position == 0) {
                preLowestX = this.getX() + (5 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
            else if (position == 1) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
            else if (position == 2) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
            else if (position == 3) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (6 * addX);
            }
        }
        else if (color.equals(Color.PINK)) {
            if (position == 0) {
                position = 1;
            }
            else if (position == 1) {
                position = 0;
            }
            if (position == 0) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
            else if (position == 1) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (6 * addX);
            }
        }
        else if (color.equals(Color.DARK_GRAY)) {
            if (position == 0) {
                position = 1;
            }
            else if (position == 1) {
                position = 0;
            }
            if (position == 0) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (4 * addX);
            }
            else if (position == 1) {
                preLowestX = this.getX() + (4 * addX);
                preHighestX = this.getX() + (6 * addX);
            }
        }
        preX[0] = preLowestX;
        preX[1] = preHighestX;
        return preX;
    }

    /**
     *  Rotates block to the right
     */
    public void rotateRight() {
        this.removeAll();
        if (this.randomFigureColor.equals(Color.RED)) {
            this.figurePosition--;
            if (this.figurePosition == -1) {
                this.figurePosition = 1;
            }
            this.makeFigureOne();
        }
        else if (this.randomFigureColor.equals(Color.GREEN)) {
            this.figurePosition--;
            if (this.figurePosition == -1) {
                this.figurePosition = 3;
            }
            this.makeFigureTwo();
        }
        else if (this.randomFigureColor.equals(Color.ORANGE)) {
            this.figurePosition--;
            if (this.figurePosition == -1) {
                this.figurePosition = 3;
            }
            this.makeFigureThree();
        }
        else if (this.randomFigureColor.equals(Color.BLUE)) {
            this.figurePosition--;
            if (this.figurePosition == -1) {
                this.figurePosition = 3;
            }
            this.makeFigureFour();
        }
        else if (this.randomFigureColor.equals(Color.YELLOW)) {
            this.figurePosition--;
            if (this.figurePosition == -1) {
                this.figurePosition = 3;
            }
            this.makeFigureFive();
        }
        else if (this.randomFigureColor.equals(Color.PINK)) {
            this.figurePosition--;
            if (this.figurePosition == -1) {
                this.figurePosition = 1;
            }
            this.makeFigureSix();
        }
        else if (this.randomFigureColor.equals(Color.DARK_GRAY)) {
            this.figurePosition--;
            if (this.figurePosition == -1) {
                this.figurePosition = 1;
            }
            this.makeFigureSeven();
        }
        this.repaint();
    }


    public Component getFigureOne() {
        Figure returnFigure = new Figure();
        returnFigure.blockOne = new Block(sBlockWidth, sBlockHeight, 80, 20, Color.RED);
        returnFigure.blockTwo = new Block(sBlockWidth, sBlockHeight, 60, 20, Color.RED);
        returnFigure.blockThree = new Block(sBlockWidth, sBlockHeight, 40, 20, Color.RED);
        returnFigure.blockFour = new Block(sBlockWidth, sBlockHeight, 20, 20, Color.RED);
        returnFigure.setVisible(true);
        returnFigure.add(returnFigure.blockOne);
        returnFigure.add(returnFigure.blockTwo);
        returnFigure.add(returnFigure.blockThree);
        returnFigure.add(returnFigure.blockFour);
        return returnFigure;
    }

    public Component getFigureTwo() {
        Figure returnFigure = new Figure();
        returnFigure.blockOne = new Block(sBlockWidth, sBlockHeight, 80, 40, Color.GREEN);
        returnFigure.blockTwo = new Block(sBlockWidth, sBlockHeight, 60, 40, Color.GREEN);
        returnFigure.blockThree = new Block(sBlockWidth, sBlockHeight, 80, 20, Color.GREEN);
        returnFigure.blockFour = new Block(sBlockWidth, sBlockHeight, 80, 0, Color.GREEN);
        returnFigure.setVisible(true);
        returnFigure.add(returnFigure.blockOne);
        returnFigure.add(returnFigure.blockTwo);
        returnFigure.add(returnFigure.blockThree);
        returnFigure.add(returnFigure.blockFour);
        return returnFigure;
    }


    public Component getFigureThree() {
        Figure returnFigure = new Figure();
        returnFigure.blockOne = new Block(sBlockWidth, sBlockHeight, 80, 40, Color.ORANGE);
        returnFigure.blockTwo = new Block(sBlockWidth, sBlockHeight, 100, 40, Color.ORANGE);
        returnFigure.blockThree = new Block(sBlockWidth, sBlockHeight, 80, 20, Color.ORANGE);
        returnFigure.blockFour = new Block(sBlockWidth, sBlockHeight, 80, 0, Color.ORANGE);
        returnFigure.setVisible(true);
        returnFigure.add(returnFigure.blockOne);
        returnFigure.add(returnFigure.blockTwo);
        returnFigure.add(returnFigure.blockThree);
        returnFigure.add(returnFigure.blockFour);
        return returnFigure;
    }


    public Component getFigureFour() {
        Figure returnFigure = new Figure();
        returnFigure.blockOne = new Block(sBlockWidth, sBlockHeight, 80, 40, Color.BLUE);
        returnFigure.blockTwo = new Block(sBlockWidth, sBlockHeight, 80, 60, Color.BLUE);
        returnFigure.blockThree = new Block(sBlockWidth, sBlockHeight, 100, 40, Color.BLUE);
        returnFigure.blockFour = new Block(sBlockWidth, sBlockHeight, 80, 20, Color.BLUE);
        returnFigure.setVisible(true);
        returnFigure.add(returnFigure.blockOne);
        returnFigure.add(returnFigure.blockTwo);
        returnFigure.add(returnFigure.blockThree);
        returnFigure.add(returnFigure.blockFour);
        return returnFigure;
    }


    public Component getFigureFive() {
        Figure returnFigure = new Figure();
        returnFigure.blockOne = new Block(sBlockWidth, sBlockHeight, 80, 40, Color.YELLOW);
        returnFigure.blockTwo = new Block(sBlockWidth, sBlockHeight, 100, 40, Color.YELLOW);
        returnFigure.blockThree = new Block(sBlockWidth, sBlockHeight, 80, 20, Color.YELLOW);
        returnFigure.blockFour = new Block(sBlockWidth, sBlockHeight, 100, 20, Color.YELLOW);
        returnFigure.setVisible(true);
        returnFigure.add(returnFigure.blockOne);
        returnFigure.add(returnFigure.blockTwo);
        returnFigure.add(returnFigure.blockThree);
        returnFigure.add(returnFigure.blockFour);
        return returnFigure;
    }


    public Component getFigureSix() {
        Figure returnFigure = new Figure();
        returnFigure.blockOne = new Block(sBlockWidth, sBlockHeight, 80, 40, Color.PINK);
        returnFigure.blockTwo = new Block(sBlockWidth, sBlockHeight, 80, 60, Color.PINK);
        returnFigure.blockThree = new Block(sBlockWidth, sBlockHeight, 60, 40, Color.PINK);
        returnFigure.blockFour = new Block(sBlockWidth, sBlockHeight, 60, 20, Color.PINK);
        returnFigure.setVisible(true);
        returnFigure.add(returnFigure.blockOne);
        returnFigure.add(returnFigure.blockTwo);
        returnFigure.add(returnFigure.blockThree);
        returnFigure.add(returnFigure.blockFour);
        return returnFigure;
    }


    public Component getFigureSeven() {
        Figure returnFigure = new Figure();
        returnFigure.blockOne = new Block(sBlockWidth, sBlockHeight, 80, 40, Color.DARK_GRAY);
        returnFigure.blockTwo = new Block(sBlockWidth, sBlockHeight, 80, 60, Color.DARK_GRAY);
        returnFigure.blockThree = new Block(sBlockWidth, sBlockHeight, 100, 40, Color.DARK_GRAY);
        returnFigure.blockFour = new Block(sBlockWidth, sBlockHeight, 100, 20, Color.DARK_GRAY);
        returnFigure.setVisible(true);
        returnFigure.add(returnFigure.blockOne);
        returnFigure.add(returnFigure.blockTwo);
        returnFigure.add(returnFigure.blockThree);
        returnFigure.add(returnFigure.blockFour);
        return returnFigure;
    }

    /**
     * Removes all block
     */
    public void removeBlocks() {
        this.removeAll();
    }



    public Block getBlockOne() {
        return blockOne;
    }

    public Block getBlockTwo() {
        return blockTwo;
    }

    public Block getBlockThree() {
        return blockThree;
    }

    public Block getBlockFour() {
        return blockFour;
    }


    public int getFigurePosition() {
        return figurePosition;
    }

    public void setFigurePosition(int position) {
        this.figureType = position;
    }

    public Color getRandomFigureColor() {
        return this.randomFigureColor;
    }


    public int getStartY() {
        return startY;
    }

}
