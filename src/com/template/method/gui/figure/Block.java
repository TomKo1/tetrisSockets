package com.template.method.gui.figure;

import java.awt.*;
import javax.swing.*;

/**
 * <p>Headline: GUI.Block</p>
 * <p>Description: This class creates a Tetris block.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Organisation: Tetris Connection</p>
 * @author Gath Sebastian, gath, gath@inf.uni-konstanz.de, 01/556108
 * @author Hug Holger, hug, hug@inf.uni-konstanz.de, 01/566368
 * @author Raedle Roman, raedler, raedler@inf.uni-konstanz.de, 01/546759
 * @author Weiler Andreas, weiler, weiler@inf.uni-konstanz.de, 01/560182
 * @version 1.0
 */

public class Block extends JComponent {

  //width of the tetris block
  protected int blockWidth = 20;

  //height of the teris block
  protected int blockHeight = 20;

  //x-coordinate of the tetris block
  protected int blockX = 0;

  //y-coordinate of the tetris block
  protected int blockY = 0;

  //color of the tetris block
  protected Color color = Color.RED;

  /**
   * standard constructor
   */
  public Block() {

  }

  /**
   * Initialization of the tetris block with width, height and color.
   *
   * @param width int Width of the tetris block
   * @param height int Heigth of the tetris block
   * @param x x-coordinate of the tetris block
   * @param y y-coordinate of the tetris block
   * @param color Color Color of the tetris block
   */
  public Block(int width, int height, int x, int y, Color color) {
    this.blockWidth = width;
    this.blockHeight = height;
    this.blockX = x;
    this.blockY = y;
    this.setLocation(x, y);
    this.setSize(width, height);
    this.color = color;
  }

  /**
   * Draw a filled rectangle as tetris block and a draw rectangle as
   *
   * @param g Graphics Graphics object -> tetris block
   */
  public void paintComponent(Graphics g) {
    g.setColor(color);
    g.fill3DRect(0, 0, 19, 19, true);
    g.setColor(Color.WHITE);
    g.drawRect(0, 0, 20, 20);
  }

  public Color getColor() {
    return color;
  }
}
