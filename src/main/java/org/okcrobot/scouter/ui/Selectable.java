package org.okcrobot.scouter.ui;

import java.awt.Component;

public interface Selectable {
  
  public boolean equals(Component component);
  public int getValue();
  public void setSelected(boolean selected);
  public void onKeyUp();
  public void onKeyDown();
  public void setListener(OptionListener listener);
  
}