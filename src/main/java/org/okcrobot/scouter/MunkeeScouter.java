package org.okcrobot.scouter;

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
        System.out.println(matchWindow.display().name());
        break;
      default:
        break;
      }
    }
    
    //TODO perform cleanup
    
    System.exit(0);
  }
  
}
