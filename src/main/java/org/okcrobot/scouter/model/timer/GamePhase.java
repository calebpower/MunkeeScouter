package org.okcrobot.scouter.model.timer;

/**
 * Game phases for FRC.
 * 
 * @author Caleb L. Power
 */
public enum GamePhase {
  /**
   * Autonomous phase of an FRC match.
   */
  AUTONOMOUS("Autonomous", 0L),
  
  /**
   * Tele-Op phase of an FRC match.
   */
  TELE_OP("Tele-Op", 15L),
  
  /**
   * End Game (still technically Tele-Op) phase of an FRC match.
   */
  END_GAME("End Game", 135L);
  
  private long startTime;
  private String title = null;
  
  private GamePhase(String title, long startTime) {
    this.title = title;
    this.startTime = startTime;
  }
  
  /**
   * Returns the particular phase at a given number of seconds.
   * 
   * @param seconds the number of seconds since the start of the match
   * @return GamePhase denoting the current game phase or <code>null</code> if
   *         there is no matching game phase.
   */
  public static GamePhase getPhaseAt(long seconds) {
    GamePhase closestPhase = null;
    for(GamePhase phase : values())
      if(closestPhase == null || (phase.startTime > closestPhase.startTime && phase.startTime <= seconds))
        closestPhase = phase;
    return closestPhase;
  }
  
  /**
   * Overrides {@link Object#toString()} and returns the title.
   */
  @Override public String toString() {
    return title;
  }
}
