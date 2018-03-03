package org.okcrobot.scouter.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.okcrobot.scouter.model.RobotAction;
import org.okcrobot.scouter.model.RobotActionList;
import org.okcrobot.scouter.model.timer.GamePhase;
import org.okcrobot.scouter.model.timer.MatchTimer;
import org.okcrobot.scouter.model.timer.TimerListener;
import org.okcrobot.scouter.ui.component.BorderedPanel;
import org.okcrobot.scouter.ui.component.DynamicGridBagConstraints;
import org.okcrobot.scouter.ui.component.HelpfulTextbox;
import org.okcrobot.scouter.ui.component.NumberSpinnerPanel;
import org.okcrobot.scouter.ui.component.OptionGroup;
import org.okcrobot.scouter.ui.keyboard.KeyListener;
import org.okcrobot.scouter.ui.keyboard.KeyMonitor;

/**
 * GUI window that records the match in progress.
 * 
 * @author Caleb L. Power
 */
public class MatchWindow extends BasicWindow implements KeyListener, OptionListener, TimerListener {
  private static final long serialVersionUID = 9038948431674394324L;

  /**
   * Actions associated with various buttons on main menu.
   * 
   * @author Caleb L. Power
   */
  public static enum State {
    /**
     * Indicates that the match window is or was closed.
     */
    CLOSED,
    
    /**
     * Indicates that the match window is currently visible.
     */
    
    VISIBLE,
    
    /**
     * Indicates that the save/review window needs to be opened.
     */
    SAVING
  }
  
  private RobotActionList possibleActions = null;
  private RobotActionList actionTracker = null;
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
  private MatchTimer timer = null;
  private NumberSpinnerPanel totalAllianceSpinner = null;
  private State currentState = null;
  
  /**
   * Null constructor for the match window.
   * Does not display window in and of itself.
   */
  public MatchWindow() {
    super("MunkeeScouter Match Window", 700, 425, 75, 75);
    currentState = State.CLOSED;
    
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
    resetButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent arg0) {
        if(JOptionPane.showOptionDialog(null,
            "Are you sure you want to reset this match?",
            "Warning!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null, null, null) == 0) {
          timer.stop();
          timer.reset();
          resetButton.setEnabled(false);
          startButton.setEnabled(true);
          possibleActions.reset();
        }
      }
    });
    matchInfoPanel.add(resetButton, constraints.setGridX(0).setGridY(1).setGridWidth(2));
    startButton = new JButton("START");
    startButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        timer.start();
        resetButton.setEnabled(true);
        startButton.setEnabled(false);
        possibleActions.enable().next();
      }
    });
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
    
    possibleActions = new RobotActionList(RobotAction.values());
    actionTracker = new RobotActionList();
    
    for(RobotAction action : possibleActions.getList()) //dynamically get all possible actions
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
    exitButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent event) {
        if(JOptionPane.showOptionDialog(null,
            "Are you sure you want to exit without saving?",
            "Warning!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null, null, null) == 0)
          currentState = State.CLOSED;
      }
    });
    saveButton = new JButton("SAVE");
    saveButton.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent event) {
        currentState = State.SAVING;
      }
    });
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
      component.setFocusable(false);
    }
    
    timer = new MatchTimer().addListener(this);
    Thread timerThread = new Thread(timer);
    timerThread.setDaemon(true);
    timerThread.start();
  }
  
  /**
   * Resets values and displays the window.
   * 
   * @return State the final state of the match window (indicates the next
   *         action that should be taken by the program)
   */
  public State display() {
    actionTracker.clear();
    currentState = State.VISIBLE;
    timer.stop();
    timer.reset();
    possibleActions.reset();
    resetButton.setEnabled(false);
    startButton.setEnabled(true);
    
    setVisible(true);
    setFocusable(true);
    requestFocusInWindow();
    while(currentState == State.VISIBLE) {
      try {
        Thread.sleep(100L);
      } catch(InterruptedException e) {}
    }
    setVisible(false);
    return currentState;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onOptionUpdate(Component component) {
    for(int i = 0; i < possibleActions.size(); i++)
      if(possibleActions.getAction(i).getSelectable().equals(component)) {
        possibleActions.setCursor(i);
        actionTracker.add(possibleActions.getAction(i), timer.getTime());
        System.out.println("Added " + actionTracker.next().getAction().name() + " at " + actionTracker.getTime());
        break;
      }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onKeyPress(int key) {
    if(possibleActions.isEnabled()) switch(key) {
    case KeyEvent.VK_LEFT:
      possibleActions.previous();
      return;
    case KeyEvent.VK_RIGHT:
      possibleActions.next();
      return;
    case KeyEvent.VK_UP:
      possibleActions.getAction().getSelectable().onKeyUp();
      return;
    case KeyEvent.VK_DOWN:
      possibleActions.getAction().getSelectable().onKeyDown();
      return;
    }
    
    switch(key) {
    case KeyEvent.VK_SPACE:
      startButton.doClick();
    }
  }
  
  /**
   * Recursively retrieves all (nested) components in a Swing GUI container
   * @param container the container to search
   * @return a list of all components
   */
  private List<Component> getAllComponents(final Container container) {
    Component[] components = container.getComponents();
    List<Component> componentList = new ArrayList<>();
    for(Component component : components) {
      componentList.add(component);
      if(component instanceof Container)
        componentList.addAll(getAllComponents((Container)component));
    }
    return componentList;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void update(long time) {
    time /= 1000; //scrub milliseconds
    timeTextField.setText( //add 0 for padding if necessary
        (time / 60 < 10 ? "0" : "") + (time / 60) + ":"
      + (time % 60 < 10 ? "0" : "") + (time % 60));
    GamePhase phase = GamePhase.getPhaseAt(time);
    if(!timer.isRunning() || phase == null) //indicate phase in textbox
      phaseTextField.setText("READY");
    else
      phaseTextField.setText(phase.toString());
    for(GamePhase optionGroupPhase : optionGroups.keySet()) //highlight as appropriate
      optionGroups.get(optionGroupPhase).toggleHighlight(timer.isRunning() && optionGroupPhase == phase);
  }
  
  public RobotActionList getRobotActions() {
    return actionTracker;
  }
  
}
