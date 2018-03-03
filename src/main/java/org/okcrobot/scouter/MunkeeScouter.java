package org.okcrobot.scouter;

import org.okcrobot.scouter.ui.ConfirmationWindow;
import org.okcrobot.scouter.ui.MainMenu;
import org.okcrobot.scouter.ui.MatchWindow;

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
    ConfirmationWindow confirmationWindow = new ConfirmationWindow();
    main:for(;;) {
      switch(mainMenu.display()) {
      case EXIT:
        break main;
      case EXPORT:
        //TODO export files
        break;
      case LOAD:
        //TODO save/load files
        break;
      case MATCH:
        
        switch(matchWindow.display()) {
        case SAVING:
          confirmationWindow
              .setTeam("TEST TEAM")
              .setMatch("TEST MATCH")
              .setRobotActions(matchWindow.getRobotActions())
              .display();
          break;
        default:
          break;
        }
        
        break;
      default:
        break;
      }
    }
    
    //TODO perform cleanup
    
    System.exit(0);
  }
  
}
