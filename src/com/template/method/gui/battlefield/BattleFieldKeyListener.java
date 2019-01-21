package com.template.method.gui.battlefield;


import java.awt.event.*;


/**
 * Encapsulates the button press handling
 */
public class BattleFieldKeyListener extends KeyAdapter {

  //tetris battle field
  protected BattleField battleField = null;

  /**
   * Initializes the battle field key listener with tetris battle field.
   */
  public BattleFieldKeyListener(BattleField bf) {
    this.battleField = bf;
  }

  /**
   * Handles the key pressing
   */
  public void keyPressed(KeyEvent evt) {
    int keyCode = evt.getKeyCode();

    if (this.battleField.firstFigure != null) {
      switch (keyCode) {
        case KeyEvent.VK_RIGHT: {
          if (this.battleField.checkShiftRight(this.battleField.firstFigure)) {
            this.battleField.firstFigure.moveRight();
          }
          break;
        }
        case KeyEvent.VK_LEFT: {
          if (this.battleField.ckeckShiftLeft(this.battleField.firstFigure)) {
            this.battleField.firstFigure.moveLeft();
          }
          break;
        }
        case KeyEvent.VK_DOWN: {
          if (this.battleField.ckeckShiftDown(this.battleField.firstFigure)) {
            this.battleField.firstFigure.moveDown();
          }
          break;
        }
        case KeyEvent.VK_Y: {
          if (this.battleField.checkRotateLeft(this.battleField.firstFigure)) {
            this.battleField.firstFigure.rotateLeft();
          }
          break;
        }
        case KeyEvent.VK_X: {
          if (this.battleField.checkRotate(this.battleField.firstFigure)) {
            this.battleField.firstFigure.rotateRight();
          }
          break;
        }
      }
    }
  }
}
