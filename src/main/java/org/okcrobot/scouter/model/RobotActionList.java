package org.okcrobot.scouter.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom list built to contain robot actions and times associated with those
 * actions. Includes a cursor.
 * 
 * @author Caleb L. Power
 */
public class RobotActionList {
  
  int current = -1;
  private List<RobotAction> actions = null;
  private List<Timestamp> times = null;
  
  /**
   * Null constructor.
   */
  public RobotActionList() {
    actions = new ArrayList<>();
    times = new ArrayList<>();
  }
  
  /**
   * Overloaded constructor to add pre-defined actions.
   * Null times are associated with these actions.
   * 
   * @param actions the pre-defined robot actions
   */
  public RobotActionList(RobotAction[] actions) {
    this();
    for(RobotAction action : actions) {
      this.actions.add(action);
      this.times.add(null);
    }
  }
  
  /**
   * Resets the RobotActionList.
   */
  public void clear() {
    actions.clear();
    times.clear();
    current = -1;
  }
  
  /**
   * Retrieves the robot action at the current cursor.
   * 
   * @return RobotAction the current robot action
   */
  public RobotAction getAction() {
    return actions.get(current);
  }
  
  /**
   * Retrieves the time that the current robot action occurred.
   * 
   * @return SQL time stamp at which the current action took place
   */
  public Timestamp getTime() {
    return times.get(current); 
  }
  
  /**
   * Moves the cursor forward one unit and notifies elements to highlight
   * themselves accordingly in the GUI.
   * 
   * @return this RobotActionList object if there was an element at the next
   *         index or <code>null</code> if there was no element at the next
   *         index
   */
  public RobotActionList next() {
    if(hasNext()) return null;
    current++;
    nudge();
    return this;
  }
  
  /**
   * Moves the cursor backward one unit and notifies elements to highlight
   * themselves accordingly in the GUI.
   * 
   * @return this RobotActionList object if there was an element at the previous
   *         index or <code>null</code> if there was no element at the previous
   *         index
   */
  public RobotActionList previous() {
    if(hasPrevious()) return null;
    current--;
    nudge();
    return this;
  }
  
  /**
   * Determines whether or not there is a data element at the index previous to
   * the current index.
   * 
   * @return In a system where n denotes the current index, <code>true</code>
   *         if there is an element at index n-1 or <code>false</code> if there
   *         is no element at index n-1
   */
  public boolean hasPrevious() {
    return current <= 0;
  }
  
  /**
   * Determines whether or not there is a data element at the index immediately
   * after the current index.
   * 
   * @return In a system where n denotes the current index, <code>true</code>
   *         if there is an element at index n+1 or <code>false</code> if there
   *         is no element at index n+1
   */
  public boolean hasNext() {
    return current >= actions.size() - 1;
  }
  
  /**
   * Adds a robot action and associated timestamp to the list.
   * 
   * @param action the action performed by the robot
   * @param timestamp the time at which the action was executed
   */
  public void add(RobotAction action, Timestamp timestamp) {
    actions.add(action);
    times.add(timestamp);
  }
  
  /**
   * Removes an element at a particular index and resets the cursor if necessary.
   * Also notifies GUI elements to highlight themselves accordingly.
   * 
   * @param i index at which the element to be removed exists
   * @return <code>true</code> if the index to be removed was in range or
   *         <code>false</code> if the index to be removed was out of range
   */
  public boolean remove(int i) {
    if(i < 0 || actions.size() <= i) return false;
    actions.remove(i);
    times.remove(i);
    if(current >= actions.size())
      current = -1;
    nudge();
    return true;
  }
  
  /**
   * Nudges (notifies) the GUI elements associated with various actions
   * so that they highlight themselves in accordance with the current index.
   */
  private void nudge() {
    for(int i = 0; i < actions.size(); i++)
      actions.get(i).getSelectable().setSelected(current == i);
  }
  
  /**
   * Retrieves the list of robot actions.
   * 
   * @return List of robot actions
   */
  public List<RobotAction> getList() {
    return actions;
  }
  
}
