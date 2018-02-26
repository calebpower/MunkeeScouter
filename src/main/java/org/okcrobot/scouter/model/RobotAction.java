package org.okcrobot.scouter.model;

import java.sql.Timestamp;

import org.okcrobot.scouter.model.timer.GamePhase;
import org.okcrobot.scouter.ui.MainMenu.Action;

public class RobotAction {
  
  private GamePhase phase = null;
  private String text = null;
  private Timestamp timestamp = null;
  
  public RobotAction(String text, GamePhase phase) {
    this.phase = phase;
    this.text = text;
  }
  
  public RobotAction setDate(Timestamp timestamp) {
    this.timestamp = timestamp;
    return this;
  }
}
