package org.okcrobot.scouter.ui.component;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class HelpfulTextbox extends JTextField {
  
  private String value = null;
  
  public HelpfulTextbox(final String hint) {
    value = new String();
    setText(hint);
    
    addFocusListener(new FocusListener() {

      @Override public void focusGained(FocusEvent arg0) {
        setText(value);
      }

      @Override public void focusLost(FocusEvent arg0) {
        value = getText();
        if(getText().equalsIgnoreCase("")) setText(hint);
      }
      
    });
    
  }
  
  public String getValue() {
    return value;
  }
  
}
