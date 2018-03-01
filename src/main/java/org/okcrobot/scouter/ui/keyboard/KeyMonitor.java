package org.okcrobot.scouter.ui.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyMonitor extends KeyAdapter {
  
  List<KeyListener> listeners = null;
  
  public KeyMonitor() {
    listeners = new ArrayList<>();
  }
  
  @Override public void keyPressed(KeyEvent event) {
    for(KeyListener listener : listeners)
      listener.onKeyPress(event.getKeyCode());
  }
  
  public KeyMonitor register(KeyListener listener) {
    listeners.add(listener);
    return this;
  }
  
  public boolean deregister(KeyListener listener) {
    return listeners.remove(listener);
  }
  
}
