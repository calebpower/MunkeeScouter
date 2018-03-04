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

import org.okcrobot.scouter.model.RobotActionList;
import org.okcrobot.scouter.ui.MatchWindow.State;
import org.okcrobot.scouter.ui.component.BorderedPanel;
import org.okcrobot.scouter.ui.component.ConfirmationItem;
import org.okcrobot.scouter.ui.component.DynamicGridBagConstraints;
import org.okcrobot.scouter.ui.component.HelpfulTextbox;
import org.okcrobot.scouter.ui.component.NumberSpinnerPanel;

public class ConfirmationWindow extends BasicWindow implements OptionListener {
  
  public static enum Action {
    RESET("Reset"),
    CANCEL("Cancel"),
    SAVE("Save");
    
    private JButton button;
    
    private Action(String text) {
      button = new JButton(text);
    }
    
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
    centerPanel.add(itemsScrollPane, constraints);
    
    southPanel = new BorderedPanel();
    ConfirmationWindowButtonListener buttonListener = new ConfirmationWindowButtonListener();
    for(Action action : Action.values()) {
      action.button.addActionListener(buttonListener);
      southPanel.add(action.button);
    }
    
    getContentPane().add(northPanel, BorderLayout.NORTH);
    getContentPane().add(centerPanel, BorderLayout.CENTER);
    getContentPane().add(southPanel, BorderLayout.SOUTH);
    
    confirmationItems = new ArrayList<>();
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
  
  public ConfirmationWindow setComments(String comments) {
    commentArea.setText(comments);
    return this;
  }
  
  public ConfirmationWindow setAlliancePoints(int alliancePoints) {
    totalAlliancePointSpinner.setValue(alliancePoints);
    return this;
  }
  
  public Action display() {
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
  
  @Override public void onOptionUpdate(Component component) {
    for(int i = 0; i < confirmationItems.size(); i++) {
      if(confirmationItems.get(i).equals(component)) {
        confirmationItems.get(i).setAlive(false);
        redrawItems();
        break;
      }
    }
  }
  
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
          for(ConfirmationItem confirmationItem : confirmationItems)
            confirmationItem.setAlive(true);
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
