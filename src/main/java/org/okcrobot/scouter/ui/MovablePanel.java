package org.okcrobot.scouter.ui;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MovablePanel extends JFrame {
  
  int posX = 0;
  int posY = 0;
  
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
