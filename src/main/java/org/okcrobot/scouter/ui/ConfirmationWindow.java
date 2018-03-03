package org.okcrobot.scouter.ui;

import org.okcrobot.scouter.model.RobotActionList;

public class ConfirmationWindow extends BasicWindow {
  
  private RobotActionList robotActions = null;
  
  public ConfirmationWindow() {
    
    super("MunkeeScouter Match Confirmation", 300, 600, 90, 90);
    
  }
  
  public void display(RobotActionList robotActions) {
    this.robotActions = robotActions;
  }
  
  
  
}
