package org.okcrobot.scouter.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.okcrobot.scouter.model.ActionList;
import org.okcrobot.scouter.model.RobotAction;
import org.okcrobot.scouter.model.timer.GamePhase;
import org.okcrobot.scouter.ui.component.BorderedPanel;
import org.okcrobot.scouter.ui.component.DynamicGridBagConstraints;
import org.okcrobot.scouter.ui.component.HelpfulTextbox;
import org.okcrobot.scouter.ui.component.NumberSpinnerPanel;
import org.okcrobot.scouter.ui.component.OptionGroup;
import org.okcrobot.scouter.ui.keyboard.KeyListener;
import org.okcrobot.scouter.ui.keyboard.KeyMonitor;

public class MatchWindow extends JFrame implements KeyListener, OptionListener {
  
  private ActionList actionList = null;
  private HelpfulTextbox teamNumberTextbox = null;
  private HelpfulTextbox matchNumberTextbox = null;
  private JButton exitButton = null;
  private JButton resetButton = null;
  private JButton startButton = null;
  private JButton saveButton = null;
  private JTextField timeTextField = null;
  private JTextField phaseTextField = null;
  private JPanel northPanel = null;
  private JPanel centerPanel = null;
  private JPanel southPanel = null;
  private JTextArea commentArea = null;
  private KeyMonitor keyMonitor = null;
  private Map<GamePhase, OptionGroup> optionGroups = null;
  private NumberSpinnerPanel totalAllianceSpinner = null;
  
  public MatchWindow() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("MunkeeScouter Match Window");
    setSize(700, 425);
    setLocation(75, 75);
    setResizable(false);
    setUndecorated(true);
    getRootPane().setWindowDecorationStyle(JRootPane.NONE);

    DynamicGridBagConstraints constraints = new DynamicGridBagConstraints()
        .setWeightX(1)
        .setWeightY(1)
        .setInsets(new Insets(5, 0, 5, 0))
        .setFill(GridBagConstraints.HORIZONTAL);
    
    northPanel = new JPanel();
    GridLayout northLayout = new GridLayout(1,2);
    northLayout.setHgap(20);
    northLayout.setVgap(20);
    northPanel.setLayout(new GridLayout(1, 2));
    
    JPanel teamInfoPanel = new BorderedPanel();
    northPanel.add(teamInfoPanel);
    teamInfoPanel.setLayout(new GridBagLayout());
    teamNumberTextbox = new HelpfulTextbox("Team Number");
    teamInfoPanel.add(teamNumberTextbox, constraints);
    matchNumberTextbox = new HelpfulTextbox("Match Number");
    teamInfoPanel.add(matchNumberTextbox, constraints.increaseGridY());    
    
    JPanel matchInfoPanel = new BorderedPanel();
    northPanel.add(matchInfoPanel);
    matchInfoPanel.setLayout(new GridBagLayout());
    timeTextField = new JTextField("MM:SS");
    timeTextField.setEditable(false);
    timeTextField.setHorizontalAlignment(JTextField.CENTER);
    matchInfoPanel.add(timeTextField, constraints.setGridX(0).setGridY(0).setGridWidth(3));
    phaseTextField = new JTextField("PHASE");
    phaseTextField.setEditable(false);
    phaseTextField.setHorizontalAlignment(JTextField.CENTER);
    matchInfoPanel.add(phaseTextField, constraints.shiftGridX(3).setGridWidth(1));
    resetButton = new JButton("RESET");
    matchInfoPanel.add(resetButton, constraints.setGridX(0).setGridY(1).setGridWidth(2));
    startButton = new JButton("START");
    matchInfoPanel.add(startButton, constraints.shiftGridX(2));
    
    centerPanel = new JPanel();
    centerPanel.setLayout(new GridBagLayout());
    constraints = new DynamicGridBagConstraints();
    
    optionGroups = new LinkedHashMap<>();
    for(GamePhase phase : GamePhase.values()) {
      OptionGroup optionGroup = new OptionGroup(phase.toString());
      optionGroups.put(phase, optionGroup);
      centerPanel.add(optionGroup, constraints);
      constraints.increaseGridX();
    }
    
    actionList = new ActionList(RobotAction.values());
    
    for(RobotAction action : actionList.list())
      optionGroups.get(action.getPhase()).add(action.getComponent(this));
    
    for(OptionGroup optionGroup : optionGroups.values())
      optionGroup.pad();
    
    southPanel = new JPanel();
    southPanel.setLayout(new GridBagLayout());
    commentArea = new JTextArea(5, 20);
    commentArea.setLineWrap(true);
    BorderedPanel commentPanel = new BorderedPanel("Additional Comments");
    commentPanel.setLayout(new GridBagLayout());
    constraints = new DynamicGridBagConstraints()
        .setFill(GridBagConstraints.BOTH)
        .setWeightX(1)
        .setWeightY(0);
    commentPanel.add(new JScrollPane(commentArea), constraints);
    exitButton = new JButton("EXIT");
    saveButton = new JButton("SAVE");
    totalAllianceSpinner = new NumberSpinnerPanel("Total Alliance Points", 0, null);
    BorderedPanel totalAlliancePanel = new BorderedPanel();
    totalAlliancePanel.add(totalAllianceSpinner);
    southPanel.add(commentPanel, constraints);
    southPanel.add(totalAlliancePanel, constraints.increaseGridY());
    southPanel.add(exitButton, constraints.increaseGridX().setGridY(0).setGridHeight(2));
    southPanel.add(saveButton, constraints.increaseGridX());
    
    getContentPane().add(northPanel, BorderLayout.NORTH);
    getContentPane().add(centerPanel, BorderLayout.CENTER);
    getContentPane().add(southPanel, BorderLayout.SOUTH);
    
    keyMonitor = new KeyMonitor().register(this);
    addKeyListener(keyMonitor);
    for(Component component : getAllComponents(this)) {
      component.addKeyListener(keyMonitor);
    }
  }
  
  public void display() {
    setVisible(true);
    setFocusable(true);
    requestFocusInWindow();
  }

  @Override public void onOptionUpdate(Component selectable) {
    for(RobotAction action : RobotAction.values())
      if(action.getSelectable().equals(selectable)) break;
  }

  @Override public void onKeyPress(int key) {
    switch(key) {
    case KeyEvent.VK_LEFT:
      actionList.prev();
      break;
    case KeyEvent.VK_RIGHT:
      actionList.next();
      break;
    case KeyEvent.VK_UP:
      actionList.get().getSelectable().onKeyUp();
      break;
    case KeyEvent.VK_DOWN:
      actionList.get().getSelectable().onKeyDown();
    }
  }
  
  private List<Component> getAllComponents(final Container c) {
    Component[] components = c.getComponents();
    List<Component> componentList = new ArrayList<>();
    for(Component component : components) {
      componentList.add(component);
      if(component instanceof Container)
        componentList.addAll(getAllComponents((Container)component));
    }
    return componentList;
  }
  
}
