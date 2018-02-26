package org.okcrobot.scouter.ui.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckboxPanel extends JPanel {
  private JLabel label = null;
  private JCheckBox checkbox = null;
  
  public CheckboxPanel(String text, boolean selected) {
    label = new JLabel(text);
    checkbox = new JCheckBox();
    checkbox.setSelected(selected);
    
    setLayout(new GridBagLayout());
    DynamicGridBagConstraints constraints = new DynamicGridBagConstraints().setGridWidth(4);
    add(label, constraints);
    add(checkbox, constraints.setGridX(4).setGridWidth(1));
  }
  
  public boolean getValue() {
    return checkbox.isSelected();
  }
}
