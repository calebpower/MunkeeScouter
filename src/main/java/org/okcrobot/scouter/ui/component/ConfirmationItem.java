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
  private long time = 0L;
  private OptionListener listener = null;
  private TimeSpinner timeSpinner = null;
  
  public ConfirmationItem(String action, long time) {
    this.time = time;
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    JLabel actionLabel = new JLabel(action);
    actionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    add(actionLabel);
    add(Box.createHorizontalGlue());
    add(Box.createRigidArea(new Dimension(20, 0)));
    timeSpinner = new TimeSpinner(time);
    timeSpinner.setAlignmentX(Component.RIGHT_ALIGNMENT);
    add(timeSpinner);
    add(Box.createRigidArea(new Dimension(20, 0)));
    deleteButton = new JButton("X");
    deleteButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
    deleteButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent arg0) {
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
  
  public void setAlive(boolean alive) {
    this.alive = alive;
  }
  
  public void resetTime() {
    timeSpinner.setTime(time);
  }
  
  public boolean isAlive() {
    return alive;
  }
  
  public boolean equals(Component component) {
    return this == component || deleteButton == component;
  }
  
  public TimeSpinner getTimeSpinner() {
    return timeSpinner;
  }
  
}
