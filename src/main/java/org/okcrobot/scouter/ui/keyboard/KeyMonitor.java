package org.okcrobot.scouter.ui.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Monitors key presses and sends notifications to key listeners.
 * 
 * @author Caleb L. Power
 */
public class KeyMonitor extends KeyAdapter {
  
  private List<KeyListener> listeners = null;
  private List<Integer> pressedKeys = null;
  
  /**
   * Null constructor.
   */
  public KeyMonitor() {
    listeners = new ArrayList<>();
    pressedKeys = new Vector<>();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    if(pressedKeys.contains(keyCode)) return;
    synchronized(pressedKeys) {
      pressedKeys.add(keyCode);
    }
    for(KeyListener listener : listeners)
      listener.onKeyPress(keyCode);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void keyReleased(KeyEvent event) {
    synchronized(pressedKeys) {
      pressedKeys.remove(new Integer(event.getKeyCode()));
    }
  }
  
  /**
   * Registers a listener with the key monitor.
   * 
   * @param listener the key listener
   * @return this KeyMonitor object
   */
  public KeyMonitor register(KeyListener listener) {
    listeners.add(listener);
    return this;
  }
  
  /**
   * Deregisters a key listener.
   * 
   * @param listener the listener to be deregistered
   * @return <code>true</code> if the listener was registered in the first
   *         place or <code>false</code> if the listener wasn't registered.
   */
  public boolean deregister(KeyListener listener) {
    return listeners.remove(listener);
  }
  
}
