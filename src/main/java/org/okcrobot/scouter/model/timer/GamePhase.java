package org.okcrobot.scouter.model.timer;

public enum GamePhase {
  AUTONOMOUS("Autonomous"),
  
  TELE_OP("Tele-Op"),
  
  END_GAME("End Game");
  
  private String title = null;
  
  private GamePhase(String title) {
    this.title = title;
  }
  
  @Override public String toString() {
    return title;
  }
}
