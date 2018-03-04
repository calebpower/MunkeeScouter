package org.okcrobot.scouter.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONObject;
import org.okcrobot.scouter.model.RobotActionList;
import org.okcrobot.scouter.model.SerializedMatch;
import org.okcrobot.scouter.ui.component.BorderedPanel;
import org.okcrobot.scouter.ui.component.ConfirmationItem;
import org.okcrobot.scouter.ui.component.DynamicGridBagConstraints;
import org.okcrobot.scouter.ui.component.HelpfulTextbox;
import org.okcrobot.scouter.ui.component.NumberSpinnerPanel;

/**
 * The confirmation window to be shown to make final edits.
 * 
 * @author Caleb L. Power
 */
public class ConfirmationWindow extends BasicWindow implements OptionListener {
  private static final long serialVersionUID = -3378083047652029892L;
  
  /**
   * Indicates an action to be performed by the confirmation window.
   * 
   * @author Caleb L. Power
   */
  public static enum Action { //order matters skrubz
    
    /**
     * Indicates that the user has elected to save the match data.
     */
    SAVE("Save"),
    
    /**
     * Indicates that the confirmation window is resetting a match to values
     * made before the user modifies them.
     */
    RESET("Reset"),
    
    /**
     * Indicates that the user has elected to discard the match data.
     */
    CANCEL("Cancel");
    
    private JButton button;
    
    /**
     * Overloaded constructor to set the button text.
     * 
     * @param text the text to be shown on the button
     */
    private Action(String text) {
      button = new JButton(text);
    }
    
    /**
     * Retrieves the enumerable associated with a given object.
     * This would generally be a JButton.
     * 
     * @param button the button
     * @return the Action associated with the button object or <code>null</code>
     *         if there is no Action associated with the given object
     */
    static Action getValue(Object button) {
      for(Action action : values())
        if(button == action.button) return action;
      return null;
    }
  }
  
  private Action currentState = null;
  private JPanel itemsPanel = null;
  private JPanel northPanel = null;
  private JPanel centerPanel = null;
  private JPanel southPanel = null;
  private JScrollPane itemsScrollPane = null;
  private JTextArea commentArea = null;
  private HelpfulTextbox teamNumberTextbox = null;
  private HelpfulTextbox matchNumberTextbox = null;
  private List<ConfirmationItem> confirmationItems = null;
  private NumberSpinnerPanel totalAlliancePointSpinner = null;
  private RobotActionList robotActions = null;
  
  /**
   * Null constructor to set up the confirmation window.
   */
  public ConfirmationWindow() {
    super("MunkeeScouter Match Confirmation", 500, 600, 90, 90);
    
    DynamicGridBagConstraints constraints = new DynamicGridBagConstraints()
        .setWeightX(1)
        .setWeightY(1)
        .setInsets(new Insets(5, 0, 5, 0))
        .setFill(GridBagConstraints.HORIZONTAL);
    
    northPanel = new BorderedPanel();
    northPanel.setLayout(new GridBagLayout());
    teamNumberTextbox = new HelpfulTextbox("Team Number");
    northPanel.add(teamNumberTextbox, constraints);
    matchNumberTextbox = new HelpfulTextbox("Match Number");
    northPanel.add(matchNumberTextbox, constraints.increaseGridY());
    
    constraints.setFill(GridBagConstraints.BOTH);
    centerPanel = new BorderedPanel();
    centerPanel.setLayout(new GridBagLayout());
    itemsPanel = new JPanel();
    itemsPanel.setLayout(new GridBagLayout());
    itemsScrollPane = new JScrollPane(itemsPanel);
    itemsScrollPane.getViewport().setPreferredSize(itemsScrollPane.getSize());
    centerPanel.add(itemsScrollPane, constraints.setGridHeight(4));
    commentArea = new JTextArea();
    centerPanel.add(commentArea, constraints.setGridHeight(1).setGridY(4));

    southPanel = new JPanel();
    southPanel.setLayout(new GridBagLayout());
    commentArea = new JTextArea("", 5, 20);
    commentArea.setLineWrap(true);
    BorderedPanel commentPanel = new BorderedPanel("Additional Comments");
    commentPanel.setLayout(new GridBagLayout());
    constraints = new DynamicGridBagConstraints()
        .setFill(GridBagConstraints.BOTH)
        .setWeightX(1)
        .setWeightY(1);
    commentPanel.add(new JScrollPane(commentArea), constraints);
    
    totalAlliancePointSpinner = new NumberSpinnerPanel("Total Alliance Points", 0, null);
    JPanel totalAlliancePanel = new JPanel();
    totalAlliancePanel.add(totalAlliancePointSpinner);
    southPanel.add(commentPanel, constraints);
    southPanel.add(totalAlliancePanel, constraints.increaseGridY());
    ConfirmationWindowButtonListener buttonListener = new ConfirmationWindowButtonListener();
    constraints.increaseGridX().setGridY(0).setGridHeight(1);
    for(Action action : Action.values()) {
      action.button.addActionListener(buttonListener);
      southPanel.add(action.button, constraints);
      constraints.increaseGridY();
    }
    
    getContentPane().add(northPanel, BorderLayout.NORTH);
    getContentPane().add(centerPanel, BorderLayout.CENTER);
    getContentPane().add(southPanel, BorderLayout.SOUTH);
    
    confirmationItems = new ArrayList<>();
  }
  
  /**
   * Sets the team number.
   * 
   * @see MatchWindow#getTeamNumber()
   * @param teamNumber the team number
   * @return this ConfirmationWindow object
   */
  public ConfirmationWindow setTeam(String teamNumber) {
    teamNumberTextbox.setValue(teamNumber);
    return this;
  }
  
  /**
   * Sets the match number.
   * 
   * @see MatchWindow#getMatchNumber()
   * @param matchNumber the match number
   * @return this ConfirmationWindow object
   */
  public ConfirmationWindow setMatch(String matchNumber) {
    matchNumberTextbox.setValue(matchNumber);
    return this;
  }
  
  /**
   * Sets the robot actions.
   * 
   * @see MatchWindow#getRobotActions()
   * @param robotActions the robot actions
   * @return this ConfirmationWindow object
   */
  public ConfirmationWindow setRobotActions(RobotActionList robotActions) {
    this.robotActions = robotActions;
    
    confirmationItems.clear();
    if(robotActions.size() > 0) {
      robotActions.setCursor(0);
      do {
        confirmationItems.add(new ConfirmationItem(
            robotActions.getAction().toString(),
            robotActions.getTime()).setListener(this));
      } while(robotActions.next() != null);
    }
    
    return this;
  }
  
  /**
   * Sets the comments.
   * 
   * @see MatchWindow#getComments()
   * @param comments the text for the comment area
   * @return this ConfirmationWindow object
   */
  public ConfirmationWindow setComments(String comments) {
    commentArea.setText(comments);
    return this;
  }
  
  /**
   * Sets the total number of alliance points won during a match.
   * 
   * @see MatchWindow#getTotalAlliancePoints()
   * @param alliancePoints the total number of alliance points that were won
   * @return this ConfirmationWindow object
   */
  public ConfirmationWindow setAlliancePoints(int alliancePoints) {
    totalAlliancePointSpinner.setValue(alliancePoints);
    return this;
  }
  
  /**
   * Displays the confirmation window.
   * 
   * @return Action enumerable denoting the final operation of the confirmation window
   */
  public Action display() {
    currentState = null;
    redrawItems();
    setVisible(true);
    setFocusable(true);
    requestFocusInWindow();
    while(currentState == null) {
      try {
        Thread.sleep(100L);
      } catch(InterruptedException e) {}
    }
    setVisible(false);
    return currentState;
  }
  
  /**
   * Redraws items in the center panel.
   */
  private void redrawItems() {
    for(Component component : getAllComponents(itemsPanel)) {
      if(component instanceof ConfirmationItem)
        itemsPanel.remove(component);
    }
    
    if(robotActions != null) {
      DynamicGridBagConstraints constraints = new DynamicGridBagConstraints()
          .setWeightX(0)
          .setWeightY(0)
          .setInsets(new Insets(0, 0, 10, 0))
          .setFill(GridBagConstraints.BOTH)
          .setGridY(0);
      
      for(ConfirmationItem confirmationItem : confirmationItems) {
        if(!confirmationItem.isAlive()) continue;
        itemsPanel.add(confirmationItem, constraints);
        constraints.increaseGridY();
      }
      
      constraints.setWeightY(1);
      itemsPanel.add(new JLabel());
    }
    
    centerPanel.revalidate();
    centerPanel.repaint();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onOptionUpdate(Component component) {
    for(int i = 0; i < confirmationItems.size(); i++) {
      if(confirmationItems.get(i).equals(component)) {
        confirmationItems.get(i).setAlive(false);
        redrawItems();
        break;
      }
    }
  }
  
  /**
   * Serializes the data stored within the confirmation window.
   * 
   * @return serialized data in JSON format
   */
  public JSONObject serialize() {
    return new SerializedMatch(confirmationItems,
        teamNumberTextbox.getValue(),
        matchNumberTextbox.getValue(),
        commentArea.getText(),
        totalAlliancePointSpinner.getValue()).serialize();
  }
  
  /**
   * Action listener for buttons on the confirmation window.
   * 
   * @author Caleb L. Power
   */
  private class ConfirmationWindowButtonListener implements ActionListener {
    @Override public void actionPerformed(ActionEvent event) {
      Action action = Action.getValue(event.getSource());
      switch(action) {
      case CANCEL:
        
        if(JOptionPane.showOptionDialog(null,
            "Are you sure that you want to cancel?",
            "Warning!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null, null, null) == 0) {
          currentState = Action.CANCEL;
        }
        
        break;
      case RESET:
        
        if(JOptionPane.showOptionDialog(null,
            "Are you sure you want to reset this window?",
            "Warning!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null, null, null) == 0) {
          for(ConfirmationItem confirmationItem : confirmationItems) {
            confirmationItem.resetTime();
            confirmationItem.setAlive(true);
          }
          redrawItems();
        }
        
        break;
      case SAVE:
        
        if(JOptionPane.showOptionDialog(null,
            "Are you sure that you are ready to save and exit?",
            "Warning!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null, null, null) == 0) {
          currentState = Action.SAVE;
        }
        
        break;
      default:
        break;
      }
    }
  }
  
}
