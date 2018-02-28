package org.okcrobot.scouter.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import org.okcrobot.scouter.model.RobotAction;
import org.okcrobot.scouter.model.timer.GamePhase;
import org.okcrobot.scouter.ui.component.CheckboxPanel;
import org.okcrobot.scouter.ui.component.DynamicGridBagConstraints;
import org.okcrobot.scouter.ui.component.NumberSpinnerPanel;
import org.okcrobot.scouter.ui.component.OptionGroup;

public class MatchWindow extends JFrame {
  
  private JPanel centerPanel = null;
  private Map<GamePhase, OptionGroup> optionGroups = null;
  
  public MatchWindow() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("MunkeeScouter Match Window");
    setSize(700, 700);
    setLocation(100, 100);
    setResizable(false);
    setUndecorated(true);
    getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    
    centerPanel = new JPanel();
    centerPanel.setLayout(new GridBagLayout());
    DynamicGridBagConstraints constraints = new DynamicGridBagConstraints();
    
    optionGroups = new LinkedHashMap<>();
    for(GamePhase phase : GamePhase.values()) {
      OptionGroup optionGroup = new OptionGroup(phase.toString());
      optionGroups.put(phase, optionGroup);
      centerPanel.add(optionGroup, constraints);
      constraints.setGridX(constraints.gridx + 1);
    }
    
    for(RobotAction action : RobotAction.values()) 
      optionGroups.get(action.getPhase()).add(action.getComponent());
    
    int maxComponentCount = 0;
    for(OptionGroup optionGroup : optionGroups.values()) {
      int count = optionGroup.getComponentCount();
      if(maxComponentCount < count) maxComponentCount = count;
      optionGroup.pad();
    }
    
    getContentPane().add(centerPanel, BorderLayout.CENTER);
  }
  
  public void display() {
    setVisible(true);
  }
  
}
