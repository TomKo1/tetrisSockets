package com.template.method.gui.battlefield;

import java.awt.*;
import javax.swing.*;

/**
 * Class encapsulates the battle field background
 */
public class BattleFieldRect extends JComponent {

  public BattleFieldRect() {

  }

  /**
   * Painta the tetris battle field backgound.
   *
   * @param g the battle field background
   */
  public void paintComponent(Graphics g) {
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, 440, 40);
    g.fillRect(0, 20, 19, 441);
    g.fillRect(19, 440, 440, 20);
    g.fillRect(260, 40, 20, 420);
  }
}
