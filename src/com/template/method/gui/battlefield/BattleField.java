package com.template.method.gui.battlefield;

import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;

import com.template.method.client.TetrisClient;
import com.template.method.gui.figure.Block;
import com.template.method.gui.figure.Figure;
import com.template.method.gui.ClientFrame;
import com.template.method.server.command.ClientPoints;
import com.template.method.server.command.FigureRequest;
import com.template.method.server.command.StopGameRequest;


/**
 * Class representing battle field (tetris game) frame
 */
public class BattleField extends JFrame {

    //figure list
    protected List<Figure> figures;
    protected String playerName;

    //array describing occupied blocks on the battllefield
    protected Block[][] blocksOnBattleField;
    protected TetrisClient tetrisClient;
    // object to lock the thread
    protected final Object clientDummy;
    // first figure on the list
    public Figure firstFigure;
    // for each new figure
    public boolean firstTime;
    protected BattleFieldRect battlefieldBackground;
    protected ClientFrame clientFrame ;

    public BattleField(TetrisClient tetrisClient, ClientFrame clientFrame) {
        super("Tetris game");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        setResizable(false);
        setPreferredSize(new Dimension(280, 480));
        setSize(new Dimension( 280, 480));
        addKeyListener(new BattleFieldKeyListener(this));
        setVisible(true);
        enableInputMethods(true);
        setFocusable(true);
        requestFocus();

        battlefieldBackground = new BattleFieldRect();
        add(battlefieldBackground);
        this.clientFrame = clientFrame;

        figures = new ArrayList<>();
        blocksOnBattleField = new Block[13][31];

        this.tetrisClient = tetrisClient;
        this.clientDummy = tetrisClient.getClientDummy();
        this.playerName = tetrisClient.getPlayerName();

        firstTime = true;
    }

    /**
     * Repaints complete BattleField every time a tick is recognized in client input
     * it moves current figure down or save it in 'occupied' array and takes next figure
     */
    public void newRepaint() {
        //first time figure shown on battleField
        if (firstTime) {

            if (figures != null && figures.size() > 0) {
                firstFigure = figures.remove(0);
                clientFrame.previewNextFigure((figures.get(0)).getColor());
                add(firstFigure);
                validate();
            }

            firstTime = false;
        }

        //check shifting down figure
        if (this.ckeckShiftDown(firstFigure)) {
            this.firstFigure.moveDown();
        }
        else {
            firstTime = true;
            if (figures.size() <= 5) {

                //send get figure list request
                tetrisClient.getOutput().addSendable(new FigureRequest(playerName));

                try {
                    synchronized (clientDummy) {
                        clientDummy.notifyAll();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (firstFigure.getY() <= 20) {
                System.out.println("battle field is full");

                //send stop game request to server
                this.tetrisClient.getOutput().addSendable(new StopGameRequest(playerName));
                try {
                    synchronized (clientDummy) {
                        this.clientDummy.notifyAll();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                this.tetrisClient.getOutput().gameIsOverNow = true;
                return;
            }
            int x = this.firstFigure.getX();
            int y = this.firstFigure.getY();

            // save blocksoffigure in array; positions are taken from location ob battleField;
            // blocksize 20 pixel
            blocksOnBattleField[(x + firstFigure.getBlockOne().getX()) / 20][(y + firstFigure.getBlockOne().getY()) / 20] = firstFigure.getBlockOne();
            blocksOnBattleField[(x + firstFigure.getBlockTwo().getX()) / 20][(y + firstFigure.getBlockTwo().getY()) / 20] = firstFigure.getBlockTwo();
            blocksOnBattleField[(x + firstFigure.getBlockThree().getX()) / 20][(y + firstFigure.getBlockThree().getY()) / 20] = firstFigure.getBlockThree();
            blocksOnBattleField[(x + firstFigure.getBlockFour().getX()) / 20][(y + firstFigure.getBlockFour().getY()) / 20] = firstFigure.getBlockFour();

            firstFigure.removeBlocks();
            remove(firstFigure);

            //send set points of client command
            clientFrame.setPoints(10);

            tetrisClient.getOutput().addSendable(new ClientPoints(playerName, tetrisClient.getClientId(), 10));
            synchronized (tetrisClient.getClientDummy()) {
                tetrisClient.getClientDummy().notifyAll();
            }

            // delete complete lines and set points
            checkCompletness();

            if (blocksOnBattleField != null) {
                paintBlocksOnBattleField();
            }
        }
    }

    /**
     * Adds new list with figures
     *
     */
    public void addNewFigureList(List<Figure> list) {
        this.figures.addAll(list);
    }


    /**
     * Check if figure can move down
     *
     * @param figure current figure
     */
    public boolean ckeckShiftDown(Figure figure) {
        int x = figure.getX();
        int y = figure.getY();

        if (y + figure.getHighestY() < 420) {
            if (y + figure.getHighestY() < 0) {
                return true;
            }
            if ((blocksOnBattleField[(x + figure.getBlockOne().getX()) / 20][(y + figure.getBlockOne().getY() + 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockTwo().getX()) / 20][(y + figure.getBlockTwo().getY() + 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockThree().getX()) / 20][(y + figure.getBlockThree().getY() + 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX()) / 20][(y + figure.getBlockFour().getY() + 20) / 20] == null)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

//    /**
//     * looking for complete lines on battleField, deleting them; move upper lines down; points
//     */
//
////    public void checkCompletness() {
////        // mamy 20 rzedow
////        boolean[] filled = new boolean[29];
////
////        int row = 0;
////        // sprawdzamy od gory
////        for (int i = this.blocksOnBattleField[0].length - 1; i > 1; i--) {
////            System.out.println("Iteracja: " + row);
////            // od lewej do prawej
////            for (int j = 1; j < this.blocksOnBattleField.length; j++) {
////                // jezeli ktorykolwiek z blokow jest rowny null to nie jest zapelniona
////                // linia
////                if(blocksOnBattleField[j][i] == null){
////                    break;
////                }
//////
////                if(j == this.blocksOnBattleField.length -1) {
////                    filled[row] = true;
////                }
////
////            }
////            row++;
////        }
////        System.out.println("Wykryte pelne rzedy: ");
//////
////        for(int i = 0 ; i < 29 ; i++) {
////            if(filled[i]) System.out.println("Rzad: " +  i);
////        }
////
////    }
//
//    private void printOccupiedCelsArray() {
//
//        for(int i = 0 ; i < 13 ; i++) {
//            for(int j = 0 ; j < 31 ; j++) {
//                String text = blocksOnBattleField[i][j] != null ? "|" : "O";
//                System.out.print(text);
//            }
//            System.out.println();
//        }
//    }


    /**
     * Looks for complete lines on battleField, deleting them; move upper lines down; points
     */
    public void checkCompletness() {
        boolean filled = true;
        boolean moveDown = false;
        // number of completed lines
        int count = 0;

        // determines which lines are occupied
        for (int i = this.blocksOnBattleField[0].length - 1; i > 1; i--) {
            for (int j = 1; j < this.blocksOnBattleField.length; j++) {
                if (this.blocksOnBattleField[j][i] == null) {
                    filled = false;
                    j = this.blocksOnBattleField.length;
                }
            }
            if (filled) {
                moveDown = true;
                // dummy block as 'marker'
                this.blocksOnBattleField[0][i] = new Block();
                count++;
            }
            filled = true;
        }

        if (moveDown) {
            boolean control = false;

            //clone array blocksOnBattleField; delete blockOnBattleField
            Block[][] clone = (Block[][]) this.blocksOnBattleField.clone();
            this.blocksOnBattleField = null;
            this.blocksOnBattleField = new Block[13][31];

            //copy uncomplete lines frome clone to blockOnBattleField at new position
            for (int delete = 1; delete <= count; delete++) {
                for (int row = clone[0].length - 1; row >= count; row--) {
                    if (clone[0][row] == null && control == false) {
                        for (int col = clone.length - 1; col >= 0; col--) {
                            this.blocksOnBattleField[col][row] = clone[col][row];
                        }
                    }
                    else {
                        control = true;
                        for (int col = clone.length - 1; col >= 0; col--) {
                            this.blocksOnBattleField[col][row] = clone[col][row - delete];
                        }
                    }
                }
                control = false;
            }

            //clear intFrame
            removeAll();
            add(battlefieldBackground);
            paintBlocksOnBattleField();
            //send set points of client command
            this.clientFrame.setPoints(100);
            tetrisClient.getOutput().addSendable(new ClientPoints(playerName, tetrisClient.getClientId(), 100));
            synchronized (tetrisClient.getClientDummy()) {
                tetrisClient.getClientDummy().notifyAll();
            }
        }
    }



    /**
     * Checks if figure can move to left
     *
     */

    public boolean ckeckShiftLeft(Figure figure) {
        int x = figure.getX();
        int y = figure.getY();
        if (figure.getX() + figure.getLowestX() > 20) {
            return (blocksOnBattleField[(x + figure.getBlockOne().getX() - 20) / 20][(y + figure.getBlockOne().getY()) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockTwo().getX() - 20) / 20][(y + figure.getBlockTwo().getY()) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockThree().getX() - 20) / 20][(y + figure.getBlockThree().getY()) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() - 20) / 20][(y + figure.getBlockFour().getY()) / 20] == null);
        }
        else {
            return false;
        }
    }

    /**
     * Checks if figure can move to right
     */
    public boolean checkShiftRight(Figure figure) {
        int x = figure.getX();
        int y = figure.getY();
        if (figure.getX() + figure.getHighestX() <= 220) {
            return (this.blocksOnBattleField[(x + figure.getBlockOne().getX() + 20) / 20][(y + figure.getBlockOne().getY()) / 20] == null) &&
                    (this.blocksOnBattleField[(x + figure.getBlockTwo().getX() + 20) / 20][(y + figure.getBlockTwo().getY()) / 20] == null) &&
                    (this.blocksOnBattleField[(x + figure.getBlockThree().getX() + 20) / 20][(y + figure.getBlockThree().getY()) / 20] == null) &&
                    (this.blocksOnBattleField[(x + figure.getBlockFour().getX() + 20) / 20][(y + figure.getBlockFour().getY()) / 20] == null);
        }
        else {
            return false;
        }
    }

    /**
     * Checks if figure is allowed to be rotated
     */
    public boolean checkRotate(Figure figure) {
        int leftBorder = 0;
        int rightBorder = 260;

        // first figure
        if (figure.getColor().equals(Color.RED) && figure.getFigurePosition() == 0) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.RED) && figure.getFigurePosition() == 1) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        //second figure
        else if (figure.getColor().equals(Color.GREEN) && figure.getFigurePosition() == 0) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.GREEN) && figure.getFigurePosition() == 1) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.GREEN) && figure.getFigurePosition() == 2) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.GREEN) && figure.getFigurePosition() == 3) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        //third figure
        else if (figure.getColor().equals(Color.ORANGE) && figure.getFigurePosition() == 0) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.ORANGE) && figure.getFigurePosition() == 1) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.ORANGE) && figure.getFigurePosition() == 2) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.ORANGE) && figure.getFigurePosition() == 3) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        //fourth figure
        else if (figure.getColor().equals(Color.BLUE) && figure.getFigurePosition() == 0) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.BLUE) && figure.getFigurePosition() == 1) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.BLUE) && figure.getFigurePosition() == 2) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.BLUE) && figure.getFigurePosition() == 3) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        //sixth figure
        else if (figure.getColor().equals(Color.PINK) && figure.getFigurePosition() == 0) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.PINK) && figure.getFigurePosition() == 1) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        //seventh figure
        else if (figure.getColor().equals(Color.DARK_GRAY) && figure.getFigurePosition() == 0) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        else if (figure.getColor().equals(Color.DARK_GRAY) && figure.getFigurePosition() == 1) {
            if (figure.preRotate(figure.getColor())[0] > leftBorder &&
                    figure.preRotate(figure.getColor())[1] < rightBorder) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }


    /**
     * Check the left rotation of figure
     */
    public boolean checkRotateLeft(Figure figure) {
        int x = figure.getX();
        int y = figure.getStartY() + figure.getY();

        if (!checkRotate(figure)) {
            return false;
        }

        if (figure.getColor().equals(Color.RED) && figure.getFigurePosition() == 1) {
            if (blocksOnBattleField[(x + figure.getBlockFour().getX() + 20) / 20][(y + figure.getBlockFour().getY() - 20) / 20] == null &&
                    blocksOnBattleField[(x + figure.getBlockTwo().getX() - 20) / 20][(y + figure.getBlockTwo().getY() + 20) / 20] == null &&
                    blocksOnBattleField[(x + figure.getBlockOne().getX() - 40) / 20][(y + figure.getBlockFour().getY() + 40) / 20] == null) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.RED) && figure.getFigurePosition() == 0) {
            if (blocksOnBattleField[(x + figure.getBlockFour().getX() - 20) / 20][(y + figure.getBlockFour().getY() + 20) / 20] == null &&
                    blocksOnBattleField[(x + figure.getBlockTwo().getX() + 20) / 20][(y + figure.getBlockTwo().getY() - 20) / 20] == null &&
                    blocksOnBattleField[(x + figure.getBlockOne().getX() + 40) / 20][(y + figure.getBlockFour().getY() - 40) / 20] == null) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.PINK) && figure.getFigurePosition() == 0) {
            if ((blocksOnBattleField[(x + figure.getBlockThree().getX() + 20) / 20][(y + figure.getBlockThree().getY() - 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() + 40) / 20][(y + figure.getBlockFour().getY()) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.PINK) && figure.getFigurePosition() == 1) {
            if ((blocksOnBattleField[(x + figure.getBlockTwo().getX() + 20) / 20][(y + figure.getBlockTwo().getY() + 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() - 40) / 20][(y + figure.getBlockFour().getY()) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.DARK_GRAY) && figure.getFigurePosition() == 0) {
            if ((blocksOnBattleField[(x + figure.getBlockTwo().getX() - 20) / 20][(y + figure.getBlockTwo().getY() - 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX()) / 20][(y + figure.getBlockFour().getY() + 40) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.DARK_GRAY) && figure.getFigurePosition() == 1) {
            if ((blocksOnBattleField[(x + figure.getBlockTwo().getX() + 20) / 20][(y + figure.getBlockTwo().getY() - 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX()) / 20][(y + figure.getBlockFour().getY() - 40) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.BLUE) && figure.getFigurePosition() == 0) {
            if ((blocksOnBattleField[(x + figure.getBlockOne().getX() - 20) / 20][(y + figure.getBlockOne().getY()) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.BLUE) && figure.getFigurePosition() == 1) {
            if ((blocksOnBattleField[(x + figure.getBlockOne().getX()) / 20][(y + figure.getBlockOne().getY() - 20) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.BLUE) && figure.getFigurePosition() == 2) {
            if ((blocksOnBattleField[(x + figure.getBlockOne().getX() + 20) / 20][(y + figure.getBlockOne().getY()) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.BLUE) && figure.getFigurePosition() == 3) {
            if ((blocksOnBattleField[(x + figure.getBlockOne().getX()) / 20][(y + figure.getBlockOne().getY() + 20) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.GREEN) && figure.getFigurePosition() == 0) {
            if ((blocksOnBattleField[(x + figure.getBlockThree().getX() + 20) / 20][(y + figure.getBlockThree().getY() + 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() + 40) / 20][(y + figure.getBlockFour().getY() + 40) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.GREEN) && figure.getFigurePosition() == 1) {
            if ((blocksOnBattleField[(x + figure.getBlockThree().getX() - 20) / 20][(y + figure.getBlockThree().getY() + 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() - 40) / 20][(y + figure.getBlockFour().getY() + 40) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.GREEN) && figure.getFigurePosition() == 2) {
            if ((blocksOnBattleField[(x + figure.getBlockThree().getX() - 20) / 20][(y + figure.getBlockThree().getY() - 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() - 40) / 20][(y + figure.getBlockFour().getY() - 40) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.GREEN) && figure.getFigurePosition() == 3) {
            if ((blocksOnBattleField[(x + figure.getBlockThree().getX() + 20) / 20][(y + figure.getBlockThree().getY() - 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() + 40) / 20][(y + figure.getBlockFour().getY() - 40) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.ORANGE) && figure.getFigurePosition() == 0) {
            if ((blocksOnBattleField[(x + figure.getBlockTwo().getX() - 20) / 20][(y + figure.getBlockTwo().getY() + 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() + 40) / 20][(y + figure.getBlockFour().getY() + 40) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.ORANGE) && figure.getFigurePosition() == 1) {
            if ((blocksOnBattleField[(x + figure.getBlockTwo().getX() - 20) / 20][(y + figure.getBlockTwo().getY() - 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() - 40) / 20][(y + figure.getBlockFour().getY() + 40) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.ORANGE) && figure.getFigurePosition() == 2) {
            if ((blocksOnBattleField[(x + figure.getBlockTwo().getX() + 20) / 20][(y + figure.getBlockTwo().getY() - 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() - 40) / 20][(y + figure.getBlockFour().getY() - 40) / 20] == null)) {
                return true;
            }
        }
        else if (figure.getColor().equals(Color.ORANGE) && figure.getFigurePosition() == 3) {
            if ((blocksOnBattleField[(x + figure.getBlockTwo().getX() + 20) / 20][(y + figure.getBlockTwo().getY() + 20) / 20] == null) &&
                    (blocksOnBattleField[(x + figure.getBlockFour().getX() + 40) / 20][(y + figure.getBlockFour().getY() - 40) / 20] == null)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Paints each nonnull block in array on the battlefield
     */
    public void paintBlocksOnBattleField() {

        boolean foundBlock = false;

        for (int i = 0; i < this.blocksOnBattleField.length; i++) {
            for (int j = 0; j < this.blocksOnBattleField[i].length; j++) {
                if (this.blocksOnBattleField[i][j] != null) {
                    //System.outputStream.println("Block bei " + i*20 + " " + j*20 + " gefunden!");
                    this.blocksOnBattleField[i][j].setLocation(i * 20, j * 20);
                    this.blocksOnBattleField[i][j].setVisible(true);
                    add(this.blocksOnBattleField[i][j]);
                    foundBlock = true;
                }
            }
            if (foundBlock) {
                foundBlock = false;
            }
        }
    }

    /**
     *  Show client their final score
     */
    public void showFinalPointDialog(int points) {
        JOptionPane.showMessageDialog(this, "Zdobyłeś: " + points + " punktów.", "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
    }


}
