package org.okcrobot.scouter.model.timer;

/**
 * Listens for notifications from the timer.
 * 
 * @author Caleb
 */
public interface TimerListener {
  
  /**
   * Performs an action when the timer updates.
   * 
   * @param time the match time
   */
  public void update(long time);
  
}
