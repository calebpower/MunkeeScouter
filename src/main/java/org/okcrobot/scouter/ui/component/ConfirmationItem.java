package org.okcrobot.scouter.ui.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.okcrobot.scouter.ui.OptionListener;

public class ConfirmationItem extends JPanel {
  
  private boolean alive = true;
  private JButton deleteButton = null;
  private OptionListener listener = null;
  
  public ConfirmationItem(String action, long time) {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    time /= 1000; //scrub milliseconds
    add(new JLabel(action + " ("//add 0 for padding if necessary
      + (time / 60 < 10 ? "0" : "") + (time / 60) + ":"
      + (time % 60 < 10 ? "0" : "") + (time % 60) + ")"));
    add(Box.createHorizontalGlue());
    add(Box.createRigidArea(new Dimension(20, 0)));
    deleteButton = new JButton("X");
    deleteButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent arg0) {
        System.out.println("X");
        if(listener != null) listener.onOptionUpdate(deleteButton);
      }
    });
    add(deleteButton);
  }
  
  public ConfirmationItem setListener(OptionListener listener) {
    this.listener = listener;
    return this;
  }
  
  public Component getDeletionComponent() {
    return deleteButton;
  }
  
}
