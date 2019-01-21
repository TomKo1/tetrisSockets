package com.template.method.gui.figure;

import java.awt.*;
import javax.swing.*;

/**
 * Class representing single  block in whole figure
 */
public class Block extends JComponent {

  //width of the block
  protected int blockWidth = 20;
  //height of the block
  protected int blockHeight = 20;
  //x-coordinate of the block
  protected int blockX = 0;
  //y-coordinate of the block
  protected int blockY = 0;
  //color of the tetris block
  protected Color color = Color.RED;

  /**
   * standard constructor
   */
  public Block() {

  }

  /**
   * Initializes of the block with width, height and color
   *
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
   * Paints the component
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
