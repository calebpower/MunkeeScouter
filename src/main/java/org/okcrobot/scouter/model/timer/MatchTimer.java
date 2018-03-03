package org.okcrobot.scouter.model.timer;

import java.util.HashSet;
import java.util.Set;

public class MatchTimer implements Runnable {
  
  private boolean running = false;
  private long startTime = 0;
  private long timeCount = 0;
  private Set<TimerListener> listeners = null;
  
  public MatchTimer() {
    listeners = new HashSet<>();
  }
  
  private void notifyListeners(long time) {
    for(TimerListener listener : listeners)
      listener.update(time);
  }
  
  public void start() {
    System.out.println("Start");
    running = true;
  }
  
  public void stop() {
    running = false;
  }
  
  public void reset() {
    startTime = System.currentTimeMillis();
    timeCount = 0;
    notifyListeners(timeCount);
  }
  
  public MatchTimer addListener(TimerListener listener) {
    listeners.add(listener);
    return this;
  }
  
  @Override public void run() {
    for(;;) {
      if(running) {
        timeCount = System.currentTimeMillis() - startTime;
        notifyListeners(timeCount);
      }
      try {
        Thread.sleep(100L);
      } catch(InterruptedException e) { }
    }
  }

}
