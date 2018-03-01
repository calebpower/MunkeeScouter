package org.okcrobot.scouter.model;

import java.util.LinkedList;
import java.util.List;

public class ActionList {
  
  int current = -1;
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
    nudge();
    return true;
  }
  
  public boolean prev() {
    if(current == 0) return false;
    current--;
    nudge();
    return true;
  }
  
  public void add(RobotAction action) {
    actions.add(action);
  }
  
  public boolean remove(int i) {
    if(actions.size() <= i) return false;
    actions.remove(i);
    if(current >= actions.size())
      current = -1;
    nudge();
    return true;
  }
  
  private void nudge() {
    for(int i = 0; i < actions.size(); i++)
      actions.get(i).getSelectable().setSelected(current == i);
  }
  
  public List<RobotAction> list() {
    return actions;
  }
  
}
