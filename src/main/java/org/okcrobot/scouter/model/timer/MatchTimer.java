package org.okcrobot.scouter.model.timer;

import java.util.HashSet;
import java.util.Set;

/**
 * Match timer.
 * 
 * @author Caleb L. Power
 */
public class MatchTimer implements Runnable {
  
  private boolean running = false;
  private long startTime = 0;
  private long timeCount = 0;
  private Set<TimerListener> listeners = null;
  
  /**
   * Null constructor for match timer.
   */
  public MatchTimer() {
    listeners = new HashSet<>();
  }
  
  /**
   * Notifies all timer listeners.
   * 
   * @param time the current match time
   */
  private void notifyListeners(long time) {
    for(TimerListener listener : listeners)
      listener.update(time);
  }
  
  /**
   * Starts the timer.
   */
  public void start() {
    running = true;
  }
  
  /**
   * Stops the timer.
   */
  public void stop() {
    running = false;
  }
  
  /**
   * Resets the timer.
   */
  public void reset() {
    startTime = System.currentTimeMillis();
    timeCount = 0;
    notifyListeners(timeCount);
  }
  
  /**
   * Retrieves the status of the timer.
   * 
   * @return <code>true</code> if the timer is running (better go catch it!) or
   *         <code>false</code> if the timer has stopped
   */
  public boolean isRunning() {
    return running;
  }
  
  /**
   * Adds a listener to the match timer.
   * 
   * @param listener the match timer listener
   * @return MatchTimer this match timer object
   */
  public MatchTimer addListener(TimerListener listener) {
    listeners.add(listener);
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
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
