package org.okcrobot.scouter;

import javax.swing.JFileChooser;

import org.okcrobot.scouter.persistent.FileHandler;
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
    FileHandler fileHandler = new FileHandler();
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
          
          switch(confirmationWindow
              .setTeam(matchWindow.getTeamNumber())
              .setMatch(matchWindow.getMatchNumber())
              .setComments(matchWindow.getComments())
              .setAlliancePoints(matchWindow.getTotalAlliancePoints())
              .setRobotActions(matchWindow.getRobotActions())
              .display()) {
          case SAVE:
            
            JFileChooser fileChooser = new JFileChooser();
            if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
              fileHandler.save(fileChooser.getSelectedFile(), confirmationWindow.serialize());
            
            break;
          default:
            break;
          
          }
          
          
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
