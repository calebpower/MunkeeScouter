package org.okcrobot.scouter.model;

import java.awt.Component;
import java.sql.Timestamp;

import org.okcrobot.scouter.model.timer.GamePhase;
import org.okcrobot.scouter.ui.OptionListener;
import org.okcrobot.scouter.ui.Selectable;
import org.okcrobot.scouter.ui.component.CheckboxPanel;
import org.okcrobot.scouter.ui.component.NumberSpinnerPanel;

public enum RobotAction {
  
  AUTONOMOUS_DID_NOT_MOVE("Didn't move", false),
  AUTONOMOUS_CROSSED_LINE("Crossed the line", 0, null),
  AUTONOMOUS_SCORED_CORRECT_SWITCH("Scored on the correct switch", 0, null),
  AUTONOMOUS_SCORED_CORRECT_SCALE("Scored on the correct scale", 0, null),
  AUTONOMOUS_SCORED_WRONG_SWITCH("Scored on the wrong swtich", 0, null),
  AUTONOMOUS_SCORED_WRONG_SCALE("Scored on the wrong scale", 0, null),
  TELEOP_DID_NOT_MOVE("Didn't move", false),
  TELEOP_PICKED_UP_A_CUBE("Picked up a cube", 0, null),
  TELEOP_SCORED_CORRECT_SWITCH("Scored cube on correct switch", 0, null),
  TELEOP_PUT_ON_SCALE("Put cube on scale", 0, null),
  TELEOP_PUT_CUBE_WRONG_SWITCH("Put cube on wrong switch", 0, null),
  TELEOP_PUT_CUBE_EXCAHNGE("Put cube in exchange", 0, null),
  ENDGAME_ON_PLATFORM("Made it onto platform", false),
  ENDGAME_CLIMBED("Climbed", false),
  ENDGAME_HELPED_PARTNER_CLIMB("Helped partner climb", 0, 2);
  
  private GamePhase phase = null;
  private Selectable component = null;
  private String text = null;
  private Timestamp timestamp = null;
  
  private RobotAction(String text) throws RuntimeException {
    switch(name().split("_")[0]) {
    case "AUTONOMOUS":
      phase = GamePhase.AUTONOMOUS;
      break;
    case "TELEOP":
      phase = GamePhase.TELE_OP;
      break;
    case "ENDGAME":
      phase = GamePhase.END_GAME;
      break;
    default:
      throw new RuntimeException("BADLY FORMED ROBOT ACTION");
    }
    this.text = text;
  }
  
  private RobotAction(String text, boolean selected) {
    this(text);
    component = new CheckboxPanel(text, selected);
  }
  
  private RobotAction(String text, Comparable<Integer> min, Comparable<Integer> max) {
    this(text);
    component = new NumberSpinnerPanel(text, min, max);
  }
  
  public RobotAction setDate(Timestamp timestamp) {
    this.timestamp = timestamp;
    return this;
  }
  
  @Override public String toString() {
    return text;
  }
  
  public GamePhase getPhase() {
    return phase;
  }
  
  public Timestamp getTimestamp() {
    return timestamp;
  }
  
  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
  
  public Selectable getSelectable() {
    return component;
  }
  
  public Component getComponent() {
    try {
      return (Component)component;
    } catch(ClassCastException e) { }
    
    return null;
  }
  
  public Component getComponent(OptionListener listener) {
    component.setListener(listener);
    return getComponent();
  }
}
