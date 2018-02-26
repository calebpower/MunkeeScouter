package org.okcrobot.scouter;

import org.okcrobot.scouter.ui.MainMenu;
import org.okcrobot.scouter.ui.MatchWindow;
import org.okcrobot.scouter.ui.MainMenu.Action;

public class MunkeeScouter {
  
  public static void main(String[] args) {
    System.out.println("Hello, world!"); //exists to initialize repo
    MainMenu mainMenu = new MainMenu();
    MatchWindow matchWindow = new MatchWindow();
    if(mainMenu.display() == Action.MATCH)
      matchWindow.display();
  }
  
  
  
}
