package org.okcrobot.scouter.model;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.okcrobot.scouter.ui.component.ConfirmationItem;

/**
 * Serialized object containing data from a single match.
 * 
 * @author Caleb L. Power
 */
public class SerializedMatch {
  
  private List<SerializedAction> actions = null;
  private String team = null;
  private String match = null;
  private String comments = null;
  private int alliancePoints = 0;
  
  /**
   * Overloaded constructor to interpret non-serialized data.
   * 
   * @param confirmationItems the actions that the robot took
   * @param team the team numbe
   * @param match the match number
   * @param comments the user's comments
   * @param alliancePoints the total number of points the alliance won
   */
  public SerializedMatch(List<ConfirmationItem> confirmationItems,
      String team, String match, String comments, int alliancePoints) {
    this.actions = new LinkedList<>();
    for(ConfirmationItem confirmationItem : confirmationItems) {
      if(confirmationItem.isAlive()) {
        actions.add(new SerializedAction(confirmationItem.getAction(),
            confirmationItem.getTimeSpinner().getMinute(),
            confirmationItem.getTimeSpinner().getSecond(),
            confirmationItem.getTimeSpinner().getMillisecond()));
      }
    }
    this.team = team;
    this.match = match;
    this.comments = comments;
    this.alliancePoints = alliancePoints;
  }
  
  public SerializedMatch(JSONObject json) throws JSONException {
    this.actions = new LinkedList<>();
    
    //TODO finish this    
  }
  
  /**
   * Serializes the data stored within the confirmation window.
   * 
   * @return serialized data in JSON format
   */
  public JSONObject serialize() {
    
    JSONArray actionArray = new JSONArray();
    for(SerializedAction action : actions) {
      actionArray.put(new JSONObject()
          .put("action", action.action)
          .put("time", new JSONObject()
              .put("minute", action.minute)
              .put("second", action.second)
              .put("millisecond", action.millisecond)));
    }
    
    return new JSONObject()
        .put("actions", actionArray)
        .put("alliancePoints", alliancePoints)
        .put("comments", comments)
        .put("match", match)
        .put("team", team);
  }
  
  /**
   * A struct-like data structure that groups actions and respective times
   * 
   * @author Caleb L. Power
   */
  private class SerializedAction {
    private String action = null;
    private int minute = 0;
    private int second = 0;
    private int millisecond = 0;
    
    /**
     * Overloaded constructor to set the metadata for a particular action
     * 
     * @param action the action
     * @param minute the minute unit of the time
     * @param second the second unit of the time
     * @param millisecond the millisecond unit of the time
     */
    private SerializedAction(String action, int minute, int second, int millisecond) {
      this.action = action;
      this.minute = minute;
      this.second = second;
      this.millisecond = millisecond;
    }
  }
  
}
