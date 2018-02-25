package org.okcrobot.scouter.model.timer;

import java.util.HashSet;
import java.util.Set;

public class MatchTimer implements Runnable {
  
  private boolean running = false;
  private int time = 0;
  private Set<TimerListener> listeners = null;
  
  public MatchTimer() {
    listeners = new HashSet<>();
  }
  
  private void notifyListeners() {
    for(TimerListener listener : listeners)
      listener.update(time);
  }
  
  public void reset() {
    time = 0;
    notifyListeners();
  }
  
  public MatchTimer addListener(TimerListener listener) {
    listeners.add(listener);
    return this;
  }
  
  @Override public void run() {
    for(;;) {
      if(running) {
        time++;
        notifyListeners();
      }
      try {
        Thread.sleep(100L);
      } catch(InterruptedException e) { }
    }
  }

}
