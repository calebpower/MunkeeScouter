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

/**
 * Dynamic confirmation item to be shown in the confirmation window.
 * 
 * @author Caleb L. Power
 */
public class ConfirmationItem extends JPanel {
  private static final long serialVersionUID = -8116179073584852956L;

  private boolean alive = true;
  private JButton deleteButton = null;
  private long time = 0L;
  private OptionListener listener = null;
  private TimeSpinner timeSpinner = null;
  
  /**
   * Overloaded constructor to tie and action to a particular time.
   * 
   * @param action the action performed by the robot
   * @param time the at which the robot performed said action
   */
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
  
  /**
   * Sets the option listener (for item deletion).
   * 
   * @param listener the option listener
   * @return this ConfirmationItem object
   */
  public ConfirmationItem setListener(OptionListener listener) {
    this.listener = listener;
    return this;
  }
  
  /**
   * Retrieves the button that is used to delete the item.
   * 
   * @return Component the component (button) that is used for deletion
   */
  public Component getDeletionComponent() {
    return deleteButton;
  }
  
  /**
   * Sets the state of the confirmation item for visibility purposes.
   * 
   * @param alive <code>true</code> if the item is alive and should be visible or
   *              <code>false</code> if the item is dead and should be invisible
   */
  public void setAlive(boolean alive) {
    this.alive = alive;
  }
  
  /**
   * Resets the time to the initial value.
   */
  public void resetTime() {
    timeSpinner.setTime(time);
  }
  
  /**
   * Determines whether or not the confirmation item should be alive.
   * 
   * @return <code>true</code> if the item should be considered alive or
   *         <code>false</code> if the item should be considered dead
   */
  public boolean isAlive() {
    return alive;
  }
  
  /**
   * Determines whether or not the component is equal to another. Also checks
   * the deletion object, and treats said object as being equivalent to this
   * confirmation item object.
   * 
   * @param component the component to be used for comparison
   * @return <code>true</code> if either the confirmation item or the deletion
   *         component are equal to the given component or <code>false</code>
   *         if the neither the component item or the deletion component are
   *         equivalent to the given component
   */
  public boolean equals(Component component) {
    return this == component || deleteButton == component;
  }
  
  /**
   * Retrieves the time spinner.
   * 
   * @return TimeSpinner the time spinner
   */
  public TimeSpinner getTimeSpinner() {
    return timeSpinner;
  }
  
}
