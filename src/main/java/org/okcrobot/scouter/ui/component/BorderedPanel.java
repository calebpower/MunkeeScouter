package org.okcrobot.scouter.ui.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class BorderedPanel extends JPanel {
  
  private String title = null;
  
  public BorderedPanel() {
    this(null);
  }
  
  public BorderedPanel(String title) {
    if(title == null) title = "";
    this.title = title;
    setBorder(generateBorder(Color.BLACK));
  }
  
  public void toggleHighlight(boolean highlight) {
    setBorder(generateBorder(highlight ? Color.RED : Color.BLACK));
  }
  
  private TitledBorder generateBorder(Color color) {
    TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
    titledBorder.setTitleJustification(TitledBorder.CENTER);
    titledBorder.setTitleColor(color);
    return titledBorder;
  }
  
}
