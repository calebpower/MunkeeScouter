package org.okcrobot.scouter.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import org.okcrobot.scouter.model.timer.GamePhase;
import org.okcrobot.scouter.ui.component.CheckboxPanel;
import org.okcrobot.scouter.ui.component.DynamicGridBagConstraints;
import org.okcrobot.scouter.ui.component.NumberSpinnerPanel;
import org.okcrobot.scouter.ui.component.OptionGroup;

public class MatchWindow extends JFrame {
  
  private JPanel centerPanel = null;
  private Map<Component, GamePhase> options = null;
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
    
    options = new LinkedHashMap<Component, GamePhase>() {{
      put(new CheckboxPanel("Didn't move", false), GamePhase.AUTONOMOUS);
      put(new NumberSpinnerPanel("Crossed the line", 0, null), GamePhase.AUTONOMOUS);
      put(new NumberSpinnerPanel("Scored on the correct switch", 0, null), GamePhase.AUTONOMOUS);
      put(new NumberSpinnerPanel("Scored on the correct scale", 0, null), GamePhase.AUTONOMOUS);
      put(new NumberSpinnerPanel("Scored on the wrong switch", 0, null), GamePhase.AUTONOMOUS);
      put(new NumberSpinnerPanel("Scored on the wrong scale", 0, null), GamePhase.AUTONOMOUS);
      put(new CheckboxPanel("Didn't move", false), GamePhase.TELE_OP);
      put(new NumberSpinnerPanel("Picked up a cube", 0, null), GamePhase.TELE_OP);
      put(new NumberSpinnerPanel("Scored cube on own switch", 0, null), GamePhase.TELE_OP);
      put(new NumberSpinnerPanel("Put cube on scale", 0, null), GamePhase.TELE_OP);
      put(new NumberSpinnerPanel("Put cube on wrong switch", 0, null), GamePhase.TELE_OP);
      put(new NumberSpinnerPanel("Put cube in exchange", 0, null), GamePhase.TELE_OP);
      put(new CheckboxPanel("Made it onto platform", false), GamePhase.END_GAME);
      put(new CheckboxPanel("Climbed", false), GamePhase.END_GAME);
      put(new NumberSpinnerPanel("Helped partner climb", 0, 2), GamePhase.END_GAME);
    }};
    
    autonomousOptionGroup = new OptionGroup("Autonomous");
    teleopOptionGroup = new OptionGroup("Tele-Op");
    endgameOptionGroup = new OptionGroup("End Game");
    
    for(Component component : options.keySet()) {
      switch(options.get(component)) {
      case AUTONOMOUS:
        autonomousOptionGroup.add(component);
        break;
      case TELE_OP:
        teleopOptionGroup.add(component);
        break;
      case END_GAME:
        endgameOptionGroup.add(component);
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
