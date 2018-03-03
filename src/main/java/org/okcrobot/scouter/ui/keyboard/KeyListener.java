package org.okcrobot.scouter.ui.keyboard;

/**
 * Listens to the {@link KeyMonitor} for key presses.
 * 
 * @author Caleb L. Power
 */
public interface KeyListener {
  
  /**
   * Performs an action when a keyboard key is pressed.
   * 
   * @param key the key code of the key that was pressed.
   */
  public void onKeyPress(int key);
  
}
