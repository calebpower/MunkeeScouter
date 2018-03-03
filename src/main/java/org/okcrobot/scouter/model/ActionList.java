package org.okcrobot.scouter.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActionList {
  
  int current = -1;
  private List<RobotAction> actions = null;
  private List<Timestamp> times = null;
  
  public ActionList() {
    actions = new ArrayList<>();
    times = new ArrayList<>();
  }
  
  public ActionList(RobotAction[] actions) {
    this();
    for(RobotAction action : actions) {
      this.actions.add(action);
      this.times.add(null);
    }
  }
  
  public RobotAction getAction() {
    return actions.get(current);
  }
  
  public Timestamp getTime() {
    return times.get(current); 
  }
  
  public ActionList next() {
    if(hasNext()) return null;
    current++;
    nudge();
    return this;
  }
  
  public ActionList previous() {
    if(hasPrevious()) return null;
    current--;
    nudge();
    return this;
  }
  
  public boolean hasPrevious() {
    return current <= 0;
  }
  
  public boolean hasNext() {
    return current >= actions.size() - 1;
  }
  
  public void add(RobotAction action, Timestamp timestamp) {
    actions.add(action);
    times.add(timestamp);
  }
  
  public boolean remove(int i) {
    if(actions.size() <= i) return false;
    actions.remove(i);
    times.remove(i);
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
