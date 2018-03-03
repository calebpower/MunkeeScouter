package org.okcrobot.scouter.ui.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class KeyMonitor extends KeyAdapter {
  
  private List<KeyListener> listeners = null;
  private List<Integer> pressedKeys = null;
  
  public KeyMonitor() {
    listeners = new ArrayList<>();
    pressedKeys = new Vector<>();
  }
  
  @Override public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    if(pressedKeys.contains(keyCode)) return;
    synchronized(pressedKeys) {
      pressedKeys.add(keyCode);
    }
    for(KeyListener listener : listeners)
      listener.onKeyPress(keyCode);
  }
  
  @Override public void keyReleased(KeyEvent event) {
    synchronized(pressedKeys) {
      pressedKeys.remove(new Integer(event.getKeyCode()));
    }
  }
  
  public KeyMonitor register(KeyListener listener) {
    listeners.add(listener);
    return this;
  }
  
  public boolean deregister(KeyListener listener) {
    return listeners.remove(listener);
  }
  
}
