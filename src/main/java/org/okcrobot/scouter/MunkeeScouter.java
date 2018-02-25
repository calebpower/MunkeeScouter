package org.okcrobot.scouter;

import org.okcrobot.scouter.ui.MainMenu;

public class MunkeeScouter {
  
  public static void main(String[] args) {
    System.out.println("Hello, world!"); //exists to initialize repo
    MainMenu mainMenu = new MainMenu();
    System.out.println(mainMenu.display().name());
  }
  
  
  
}
