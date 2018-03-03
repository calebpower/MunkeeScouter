package org.okcrobot.scouter.ui.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Bordered panel that supports titles and color changes.
 * 
 * @author Caleb L. Power
 */
public class BorderedPanel extends JPanel {
  private static final long serialVersionUID = -8343613423413372595L;
  
  private String title = null;
  
  /**
   * Null constructor.
   */
  public BorderedPanel() {
    this(null);
  }
  
  /**
   * Overloaded constructor that sets the title.
   * 
   * @param title the title to be set
   */
  public BorderedPanel(String title) {
    if(title == null) title = "";
    this.title = title;
    setBorder(generateBorder(Color.BLACK));
  }
  
  /**
   * Toggles highlighting of a particular border title
   * @param highlight <code>true</code> if the title should be colored or
   *                  <code>false</code> if the title should not be colored
   */
  public void toggleHighlight(boolean highlight) {
    setBorder(generateBorder(highlight ? Color.RED : Color.BLACK));
  }
  
  /**
   * Generates a border with the title as a set color.
   * 
   * @param color the color to set the title
   * @return TitledBorder the border (which won't be forced to pay for)
   */
  private TitledBorder generateBorder(Color color) {
    TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
    titledBorder.setTitleJustification(TitledBorder.CENTER);
    titledBorder.setTitleColor(color);
    return titledBorder;
  }
  
}
