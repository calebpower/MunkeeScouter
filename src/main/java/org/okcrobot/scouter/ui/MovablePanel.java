package org.okcrobot.scouter.ui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

/**
 * Movable JFrame (for use on undecorated windows).
 * 
 * @author Caleb L. Power
 */
public class MovablePanel extends JFrame {
  private static final long serialVersionUID = 6008002742862552466L;
  
  int posX = 0;
  int posY = 0;
  
  /**
   * Null constructor.
   */
  public MovablePanel() {
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        posX = e.getX();
        posY = e.getY();
      }
    });
    
    addMouseMotionListener(new MouseAdapter() {
      public void mouseDragged(MouseEvent e) {
        Rectangle rectangle = getBounds();
        setBounds(e.getXOnScreen() - posX,
            e.getYOnScreen() - posY,
            rectangle.width,
            rectangle.height);
      }
    });
  }
  
}
