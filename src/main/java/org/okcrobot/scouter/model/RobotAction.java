package org.okcrobot.scouter.model;

import java.awt.Component;

import org.okcrobot.scouter.model.timer.GamePhase;
import org.okcrobot.scouter.ui.OptionListener;
import org.okcrobot.scouter.ui.Selectable;
import org.okcrobot.scouter.ui.component.CheckboxPanel;
import org.okcrobot.scouter.ui.component.NumberSpinnerPanel;

/**
 * Various actions that the robot is able to perform during a match.
 * TODO in the future, this should be configurable.
 * 
 * @author Caleb L. Power
 */
public enum RobotAction {
  
  /**
   * Indicates that robot did not move during the autonomous mode.
   */
  AUTONOMOUS_DID_NOT_MOVE("Didn't move", false),
  
  /**
   * Indicates that the robot crossed the line during the autonomous mode.
   */
  AUTONOMOUS_CROSSED_LINE("Crossed the line", 0, null),
  
  /**
   * Indicates that the robot scored on the correct switch during the autonomous mode.
   */
  AUTONOMOUS_SCORED_CORRECT_SWITCH("Scored on the correct switch", 0, null),
  
  /**
   * Indicates that the robot scored on the correct scale during the autonomous mode.
   */
  AUTONOMOUS_SCORED_CORRECT_SCALE("Scored on the correct scale", 0, null),
  
  /**
   * Indicates that the robot scored on the wrong switch during the autonomous mode.
   */
  AUTONOMOUS_SCORED_WRONG_SWITCH("Scored on the wrong swtich", 0, null),
  
  /**
   * Indicates that the robot scored on the wrong scale during the autonomous mode.
   */
  AUTONOMOUS_SCORED_WRONG_SCALE("Scored on the wrong scale", 0, null),
  
  /**
   * Indicates that the robot did not move during the tele-op phase.
   */
  TELEOP_DID_NOT_MOVE("Didn't move", false),
  
  /**
   * Indicates that the robot picked up a cube during the tele-op phase.
   */
  TELEOP_PICKED_UP_A_CUBE("Picked up a cube", 0, null),
  
  /**
   * Indicates that the robot scored on the correct switch during the tele-op phase.
   */
  TELEOP_SCORED_CORRECT_SWITCH("Scored cube on correct switch", 0, null),
  
  /**
   * Indicates that the robot put a cube on a scale during the tele-op phase.
   */
  TELEOP_PUT_ON_SCALE("Put cube on scale", 0, null),
  
  /**
   * Indicates that the robot put a cube on the wrong switch during the tele-op phase.
   */
  TELEOP_PUT_CUBE_WRONG_SWITCH("Put cube on wrong switch", 0, null),
  
  /**
   * Indicates that the robot put a cube in the exchange during the tele-op phase.
   */
  TELEOP_PUT_CUBE_EXCAHNGE("Put cube in exchange", 0, null),
  
  /**
   * Indicates that the robot made it onto the platform during the end game.
   */
  ENDGAME_ON_PLATFORM("Made it onto platform", false),
  
  /**
   * Indicates that the robot climbed during the end game.
   */
  ENDGAME_CLIMBED("Climbed", false),
  
  /**
   * Indicates that the robot helped a partner climb during the end game.
   */
  ENDGAME_HELPED_PARTNER_CLIMB("Helped partner climb", 0, 2);
  
  private GamePhase phase = null;
  private Selectable component = null;
  private String text = null;
  
  /**
   * Overloaded constructor to set the text of the particular action.
   * 
   * @param text the text tied to the specified action
   * @throws RuntimeException to be thrown if the programmer was dumb
   */
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
  
  /**
   * Overloaded constructor to additionally specify a checkbox GUI component
   * 
   * @param text the text to be associated with the action
   * @param selected <code>true</code> if the checkbox should be initially selected or
   *                 <code>false</code> if the checkbox should be initially deselected
   */
  private RobotAction(String text, boolean selected) {
    this(text);
    component = new CheckboxPanel(text, selected);
  }
  
  /**
   * Overloaded constructor to additionally specify a spinner GUI component
   * 
   * @param text the text to be associated with the action
   * @param min the minimum value that the spinner may display
   * @param max the maximum value that the spinner may display
   */
  private RobotAction(String text, Comparable<Integer> min, Comparable<Integer> max) {
    this(text);
    component = new NumberSpinnerPanel(text, min, max);
  }
  
  /**
   * Overrides {@link Object#toString()} to display the text
   */
  @Override public String toString() {
    return text;
  }
  
  /**
   * Returns the game phase associated with the particular action.
   * 
   * @return GamePhase the action's game phase
   */
  public GamePhase getPhase() {
    return phase;
  }
  
  /**
   * Returns the Selectable GUI component associated with the action in question.
   * 
   * @return Selectable the selectable GUI component
   */
  public Selectable getSelectable() {
    return component;
  }
  
  /**
   * Retrieves the GUI component associated with the action in question.
   * 
   * @return Component the GUI component
   */
  public Component getComponent() {
    try {
      return (Component)component;
    } catch(ClassCastException e) { }
    
    return null;
  }
  
  /**
   * Ties an option listener to the component before retrieving it.
   * 
   * @see RobotAction#getComponent()
   * @param listener the option listener that should be tied to the component
   * @return the component in question
   */
  public Component getComponent(OptionListener listener) {
    component.setListener(listener);
    return getComponent();
  }
}
