package org.okcrobot.scouter.ui.component;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
    checkbox.setMaximumSize(new Dimension(20, 20));
    checkbox.setMinimumSize(new Dimension(10, 20));
    
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    add(label);
    add(Box.createHorizontalGlue());
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(checkbox);
  }
  
  public int getValue() {
    return checkbox.isSelected() ? 1 : 0;
  }

  @Override public void setSelected(boolean selected) {
    // TODO Auto-generated method stub
  }
}
