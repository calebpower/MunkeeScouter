package org.okcrobot.scouter.ui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JRootPane;

/**
 * Movable JFrame (for use on undecorated windows).
 * 
 * @author Caleb L. Power
 */
public class BasicWindow extends JFrame {
  private static final long serialVersionUID = 6008002742862552466L;
  
  int posX, posY;
  
  /**
   * Null constructor.
   * 
   * @param title window title 
   * @param width window width
   * @param height window height
   * @param x window starting x position
   * @param y window starting y position
   */
  public BasicWindow(String title, int width, int height, int x, int y) {
    
    posX = x;
    posY = y;
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle(title);
    setSize(width, height);
    setLocation(x, y);
    setResizable(false);
    setUndecorated(true);
    getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    
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
