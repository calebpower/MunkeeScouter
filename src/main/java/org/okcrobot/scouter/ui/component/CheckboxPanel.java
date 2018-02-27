package org.okcrobot.scouter.ui.component;

import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.okcrobot.scouter.ui.Selectable;

public class CheckboxPanel extends JPanel implements Selectable {
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
  
  public int getValue() {
    return checkbox.isSelected() ? 1 : 0;
  }

  @Override public void setSelected(boolean selected) {
    // TODO Auto-generated method stub
  }
}
