package org.okcrobot.scouter.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import org.okcrobot.scouter.model.RobotAction;
import org.okcrobot.scouter.model.timer.GamePhase;
import org.okcrobot.scouter.ui.component.BorderedPanel;
import org.okcrobot.scouter.ui.component.DynamicGridBagConstraints;
import org.okcrobot.scouter.ui.component.HelpfulTextbox;
import org.okcrobot.scouter.ui.component.OptionGroup;

public class MatchWindow extends JFrame {
  
  private HelpfulTextbox teamNumberTextbox = null;
  private HelpfulTextbox matchNumberTextbox = null;
  private JPanel northPanel = null;
  private JPanel centerPanel = null;
  private JPanel southPanel = null;
  private Map<GamePhase, OptionGroup> optionGroups = null;
  
  public MatchWindow() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("MunkeeScouter Match Window");
    setSize(700, 700);
    setLocation(100, 100);
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
    northLayout.setHgap(5);
    northPanel.setLayout(new GridLayout(1, 2));
    
    JPanel teamInfoPanel = new BorderedPanel();
    teamInfoPanel.setLayout(new GridBagLayout());
    
    teamNumberTextbox = new HelpfulTextbox("Team Number");
    teamInfoPanel.add(teamNumberTextbox, constraints);
    matchNumberTextbox = new HelpfulTextbox("Match Number");
    teamInfoPanel.add(matchNumberTextbox, constraints.increaseGridY());    
    
    JPanel matchInfoPanel = new BorderedPanel();
    northPanel.add(teamInfoPanel);
    northPanel.add(matchInfoPanel);
    
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
    
    for(RobotAction action : RobotAction.values()) 
      optionGroups.get(action.getPhase()).add(action.getComponent());
    
    int maxComponentCount = 0;
    for(OptionGroup optionGroup : optionGroups.values()) {
      int count = optionGroup.getComponentCount();
      if(maxComponentCount < count) maxComponentCount = count;
      optionGroup.pad();
    }
    
    getContentPane().add(northPanel, BorderLayout.NORTH);
    getContentPane().add(centerPanel, BorderLayout.CENTER);
  }
  
  public void display() {
    setVisible(true);
    requestFocusInWindow();
  }
  
}
