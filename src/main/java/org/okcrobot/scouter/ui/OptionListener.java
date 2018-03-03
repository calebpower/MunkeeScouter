package org.okcrobot.scouter.ui;

import java.awt.Component;

/**
 * Listens for option updates.
 * 
 * @author Caleb L. Power
 */
public interface OptionListener {
  
  /**
   * Performs an action when an option is updated.
   * 
   * @param component the component that was updated
   */
  public void onOptionUpdate(Component component);
  
}
