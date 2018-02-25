package org.okcrobot.scouter.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

/**
 * Main menu, to give user various top-level options.
 * 
 * @author Caleb L. Power
 */
public class MainMenu extends JFrame {
  private static final long serialVersionUID = 8166594334688102102L;
  
  private Action nextAction = null;
  private JLabel bannerImage = null;
  private JPanel buttonPanel = null;
  
  /**
   * Actions associated with various buttons on main menu.
   * 
   * @author Caleb L. Power
   */
  public static enum Action {
    /**
     * Denotes a request to load a file.
     */
    LOAD("Load / Save"),
    
    /**
     * Denotes a request to start recording a match.
     */
    MATCH("New Match"),
    
    /**
     * Denotes a request to export currently-saved data.
     */
    EXPORT("Export Data"),
    
    /**
     * Denotes a request to exit the program.
     */
    EXIT("Exit");
    
    private JButton button = null;
    
    private Action(String text) {
      button = new JButton(text);
    }
    
    Action getAction(JButton button) {
      for(Action action : values())
        if(this.button == button)
          return action;
      return null;
    }
  }
  
  /**
   * Null constructor to construct the main menu.
   */
  public MainMenu() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("MunkeeScouter Main Menu");
    setSize(440, 500); //image is 440x440, extra height required for buttons
    setLocation(100, 100);
    setResizable(false);
    setUndecorated(true);
    getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    
    bannerImage = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("banner.png")));
    buttonPanel = new JPanel();
    for(Action action : Action.values()) {
      buttonPanel.add(action.button);
      action.button.addActionListener(new ActionListener() {
        @Override public void actionPerformed(ActionEvent e) {
          nextAction = action;
        }
      });
    }
    
    getContentPane().add(bannerImage, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
  }
  
  /**
   * Display the main menu and returns the requested action.
   * 
   * @return Action denoting the requested action to be performed next
   */
  public Action display() {
    nextAction = null;
    setVisible(true);
    while(nextAction == null) { //wait for a button press
      try {
        Thread.sleep(100L);
      } catch(InterruptedException e) { }
    }
    setVisible(false); //hide the window before continuing
    return nextAction;
  }
  
}
