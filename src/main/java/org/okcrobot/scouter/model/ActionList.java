package org.okcrobot.scouter.model;

import java.util.LinkedList;
import java.util.List;

public class ActionList {
  
  int current = 0;
  private List<RobotAction> actions = null;
  
  public ActionList() {
    actions = new LinkedList<>();
  }
  
  public ActionList(RobotAction[] actions) {
    this();
    for(RobotAction action : actions)
      this.actions.add(action);
  }
  
  public RobotAction get() {
    return actions.get(current);
  }
  
  public RobotAction get(int i) {
    current = i;
    return get();
  }
  
  public boolean next() {
    if(current >= actions.size() - 1) return false;
    current++;
    return true;
  }
  
  public boolean prev() {
    if(current == 0) return false;
    current--;
    return true;
  }
  
}
