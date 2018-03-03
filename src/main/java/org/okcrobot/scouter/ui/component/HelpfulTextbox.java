package org.okcrobot.scouter.ui.component;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * A textbox that gives a default value when empty.
 * 
 * @author Caleb L. Power
 */
public class HelpfulTextbox extends JTextField {
  private static final long serialVersionUID = 2473111271481610690L;
  
  private String hint = null;
  private String value = null;
  
  /**
   * Overloaded constructor to set the hint.
   * 
   * @param hint the hint to be shown on empty textboxes
   */
  public HelpfulTextbox(final String hint) {
    this.hint = hint;
    value = new String();
    setText(hint);
    setHorizontalAlignment(JTextField.CENTER);
    
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
  
  /**
   * Get the true value of the textbox (not necessarily what is shown).
   * 
   * @return String representation of the textbox value
   */
  public String getValue() {
    return value;
  }
  
  /**
   * Sets the true value of the textbox.
   * 
   * @param value the textbox value
   * @return this HelpfulTextbox object
   */
  public HelpfulTextbox setValue(String value) {
    if(value == null) value = "";
    setText(value == null ? hint : value);
    return this;
  }
}
