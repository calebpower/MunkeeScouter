package org.okcrobot.scouter;

import org.okcrobot.scouter.ui.MainMenu;
import org.okcrobot.scouter.ui.MatchWindow;
import org.okcrobot.scouter.ui.MainMenu.Action;

/**
 * An FRC match scouting program designed for the NinjaMunkees team.
 * 
 * @author Caleb L. Power
 */
public class MunkeeScouter {
  
  /**
   * Entry point for the program.
   * 
   * @param cheese command-line arguments
   */
  public static void main(String[] cheese) {
    MainMenu mainMenu = new MainMenu();
    MatchWindow matchWindow = new MatchWindow();
    if(mainMenu.display() == Action.MATCH)
      System.out.println(matchWindow.display().name());
  }
  
}
