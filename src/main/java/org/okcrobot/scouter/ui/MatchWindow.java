package org.okcrobot.scouter.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
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
  //private Map<Component, GamePhase> options = null;
  private OptionGroup autonomousOptionGroup = null;
  private OptionGroup teleopOptionGroup = null;
  private OptionGroup endgameOptionGroup = null;
  
  public MatchWindow() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("MunkeeScouter Match Window");
    setSize(700, 700);
    setLocation(100, 100);
    setResizable(false);
    setUndecorated(true);
    getRootPane().setWindowDecorationStyle(JRootPane.NONE);

    autonomousOptionGroup = new OptionGroup("Autonomous");
    teleopOptionGroup = new OptionGroup("Tele-Op");
    endgameOptionGroup = new OptionGroup("End Game");
    
    for(RobotAction action : RobotAction.values()) {
      switch(action.getPhase()) {
      case AUTONOMOUS:
        autonomousOptionGroup.add(action.getComponent());
        break;
      case TELE_OP:
        teleopOptionGroup.add(action.getComponent());
        break;
      case END_GAME:
        endgameOptionGroup.add(action.getComponent());
      }
    }
    
    centerPanel = new JPanel();
    centerPanel.setLayout(new GridBagLayout());
    DynamicGridBagConstraints constraints = new DynamicGridBagConstraints();
    centerPanel.add(autonomousOptionGroup, constraints);
    centerPanel.add(teleopOptionGroup, constraints.setGridX(1));
    centerPanel.add(endgameOptionGroup, constraints.setGridX(2));
    
    getContentPane().add(centerPanel, BorderLayout.CENTER);
  }
  
  public void display() {
    setVisible(true);
  }
  
}
