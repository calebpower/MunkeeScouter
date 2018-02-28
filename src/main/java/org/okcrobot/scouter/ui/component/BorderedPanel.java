package org.okcrobot.scouter.ui.component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class BorderedPanel extends JPanel {
  
  public BorderedPanel() {
    this(null);
  }
  
  public BorderedPanel(String title) {
    if(title == null) title = "";
    TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
    titledBorder.setTitleJustification(TitledBorder.CENTER);
    setBorder(titledBorder);
  }
  
}
