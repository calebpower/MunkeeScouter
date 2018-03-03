package org.okcrobot.scouter.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.okcrobot.scouter.model.RobotAction;
import org.okcrobot.scouter.model.RobotActionList;
import org.okcrobot.scouter.ui.component.BorderedPanel;
import org.okcrobot.scouter.ui.component.ConfirmationItem;
import org.okcrobot.scouter.ui.component.DynamicGridBagConstraints;
import org.okcrobot.scouter.ui.component.HelpfulTextbox;
import org.okcrobot.scouter.ui.component.NumberSpinnerPanel;

public class ConfirmationWindow extends BasicWindow implements OptionListener {
  
  public static enum Action {
    CANCEL("Cancel"),
    SAVE("Save");
    
    private JButton button;
    
    private Action(String text) {
      button = new JButton(text);
    }
  }
  
  private JPanel northPanel = null;
  private JPanel centerPanel = null;
  private JPanel southPanel = null;
  private JTextArea commentArea = null;
  private HelpfulTextbox teamNumberTextbox = null;
  private HelpfulTextbox matchNumberTextbox = null;
  private List<Component> deletionComponents = null;
  private NumberSpinnerPanel totalAlliancePointSpinner = null;
  private RobotActionList robotActions = null;
  
  public ConfirmationWindow() {
    super("MunkeeScouter Match Confirmation", 300, 600, 90, 90);
    
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
    
    centerPanel = new BorderedPanel();
    centerPanel.setLayout(new GridBagLayout());
    
    getContentPane().add(northPanel, BorderLayout.NORTH);
    
    deletionComponents = new ArrayList<>();
  }
  
  public ConfirmationWindow setTeam(String teamNumber) {
    teamNumberTextbox.setValue(teamNumber);
    return this;
  }
  
  public ConfirmationWindow setMatch(String matchNumber) {
    matchNumberTextbox.setValue(matchNumber);
    return this;
  }
  
  public ConfirmationWindow setRobotActions(RobotActionList robotActions) {
    this.robotActions = robotActions;
    return this;
  }
  
  public ConfirmationWindow setComments(String comments) {
    commentArea.setText(comments);
    return this;
  }
  
  public ConfirmationWindow setAlliancePoints(int alliancePoints) {
    totalAlliancePointSpinner.setValue(alliancePoints);
    return this;
  }
  
  public void display() {
    redrawItems();
    setVisible(true);
  }
  
  private void redrawItems() {
    centerPanel.removeAll();
    if(robotActions != null) {
      DynamicGridBagConstraints constraints = new DynamicGridBagConstraints()
          .setWeightX(0)
          .setWeightY(0)
          .setInsets(new Insets(0, 0, 10, 0))
          .setFill(GridBagConstraints.HORIZONTAL)
          .setGridY(-1);
      
      if(robotActions.size() > 0) {
        System.out.println("Checkpoint 1");
        robotActions.setCursor(0);
        do {
          System.out.println("Checkpoint 2");
          ConfirmationItem confirmationItem = new ConfirmationItem(
              robotActions.getAction().toString(),
              robotActions.getTime());
          centerPanel.add(confirmationItem);
          deletionComponents.add(confirmationItem.getDeletionComponent());
          System.out.println("Ding!");
        } while(robotActions.next() != null);
      }
    }
    centerPanel.validate();
  }
  
  @Override public void onOptionUpdate(Component component) {
    for(int i = 0; i < deletionComponents.size(); i++) {
      if(deletionComponents.get(i) == component) {
        deletionComponents.remove(i);
        robotActions.remove(i);
        redrawItems();
        break;
      }
    }
  }
  
  
  
}
