package org.okcrobot.scouter.ui;

import java.awt.Component;

/**
 * A selectable object (preferably related to the GUI)
 * 
 * @author Caleb L. Power
 */
public interface Selectable {
  
  /**
   * Determines if this component is equal to the provided component.
   * 
   * @param component a component to be used for comparison
   * @return <code>true</code> if the component was equivalent or
   *         <code>false</code> if the component was not equivalent
   */
  public boolean equals(Component component);
  
  /**
   * Retrieves the value of this selectable component.
   * 
   * @return integer representation of the value of this component
   */
  public int getValue();
  
  /**
   * Flags the object as being "selected" or "deselected"
   * 
   * @param selected <code>true</code> if the object is being selected or
   *                 <code>false</code> if the object is being deselected
   */
  public void setSelected(boolean selected);
  
  /**
   * Performs an action when the up arrow is pressed.
   */
  public void onKeyUp();
  
  /**
   * Performs an action when the down arrow is pressed.
   */
  public void onKeyDown();
  
  /**
   * Sets the option listener.
   * 
   * @param listener the option listener
   */
  public void setListener(OptionListener listener);
  
}