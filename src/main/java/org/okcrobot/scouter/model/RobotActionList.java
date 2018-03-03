package org.okcrobot.scouter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom list built to contain robot actions and times associated with those
 * actions. Includes a cursor.
 * 
 * @author Caleb L. Power
 */
public class RobotActionList {
  
  boolean enabled = true;
  int current = -1;
  private List<RobotAction> actions = null;
  private List<Long> times = null;
  
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
   * Clears the RobotActionList.
   *
   * @return this RobotActionList object
   */
  public RobotActionList clear() {
    actions.clear();
    times.clear();
    current = -1;
    return this;
  }
  
  /**
   * Disables and resets the values of the components in this list.
   * 
   * @return this RobotActionList object
   */
  public RobotActionList reset() {
    for(RobotAction action : actions) {
      action.getSelectable().reset();
      action.getSelectable().setEnabled(false);
    }
    current = -1;
    enabled = false;
    nudge();
    return this;
  }
  
  /**
   * Enables the values of the components in this list.
   * 
   * @return this RobotActionList object
   */
  public RobotActionList enable() {
    for(RobotAction action : actions)
      action.getSelectable().setEnabled(true);
    enabled = true;
    return this;
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
   * Retrieves the robot action at the specified index.
   * 
   * @param i the specified index
   * @return the datum at the specified index
   */
  public RobotAction getAction(int i) {
    return actions.get(i);
  }
  
  /**
   * Retrieves the time at which the current robot action occurred.
   * 
   * @return timestamp at which the current action took place
   */
  public long getTime() {
    return times.get(current); 
  }
  
  /**
   * Retrieves the time at which the robot action at the specified index occurred.
   * 
   * @param i the specified index
   * @return the datum at the specified index
   */
  public long getTime(int i) {
    return times.get(i);
  }
  
  /**
   * Moves the cursor to the specified index, provided that the requested cursor
   * is in bounds. If the requested cursor position is out of bounds, then the
   * current cursor remains unchanged.
   * 
   * @param i the new index
   * @return <code>true</code> if the new index is in bounds or
   *         <code>false</code> if the new index is out of bounds
   */
  public boolean setCursor(int i) {
    if(i < 0 || i >= actions.size()) return false;
    current = i;
    nudge();
    return true;
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
  public void add(RobotAction action, long timestamp) {
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
  
  /**
   * Determines whether or not the elements in the list have been enabled.
   * 
   * @return <code>true</code> if the elements have been enabled or
   *         <code>false</code> if the elements have been disabled
   */
  public boolean isEnabled() {
    return enabled;
  }
  
  /**
   * Retrieves the number of elements in the list.
   * 
   * @return integer representation of the size of the list
   */
  public int size() {
    return actions.size();
  }
  
}
