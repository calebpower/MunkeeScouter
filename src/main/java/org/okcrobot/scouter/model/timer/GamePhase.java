package org.okcrobot.scouter.model.timer;

public enum GamePhase {
  AUTONOMOUS("Autonomous", 0L),
  
  TELE_OP("Tele-Op", 15L),
  
  END_GAME("End Game", 135L);
  
  private long startTime;
  private String title = null;
  
  private GamePhase(String title, long startTime) {
    this.title = title;
    this.startTime = startTime;
  }
  
  public static GamePhase getPhaseAt(long seconds) {
    GamePhase closestPhase = null;
    for(GamePhase phase : values())
      if(closestPhase == null || (phase.startTime > closestPhase.startTime && phase.startTime <= seconds))
        closestPhase = phase;
    return closestPhase;
  }
  
  @Override public String toString() {
    return title;
  }
}
