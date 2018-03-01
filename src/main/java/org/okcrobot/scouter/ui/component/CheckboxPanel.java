package org.okcrobot.scouter.ui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.okcrobot.scouter.ui.OptionListener;
import org.okcrobot.scouter.ui.Selectable;

public class CheckboxPanel extends JPanel implements Selectable {
  private JLabel label = null;
  private JCheckBox checkbox = null;
  private OptionListener listener = null;
  
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
    
    checkbox.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent event) {
        System.out.println("Checkbox changed: " + checkbox.isSelected());
        if(listener != null) listener.onOptionUpdate((Component)event.getSource());
      }
    });
  }
  
  @Override public boolean equals(Component component) {
    return checkbox == component;
  }
  
  @Override public int getValue() {
    return checkbox.isSelected() ? 1 : 0;
  }

  @Override public void setSelected(boolean selected) {
    label.setForeground(selected ? Color.GREEN : Color.BLACK);
  }

  @Override public void setListener(OptionListener listener) {
    this.listener = listener;
  }

  @Override public void onKeyUp() {
    checkbox.setSelected(true);
    System.out.println("Checkbox changed: " + checkbox.isSelected());
    if(listener != null) listener.onOptionUpdate(this);
  }

  @Override public void onKeyDown() {
    checkbox.setSelected(false);
    System.out.println("Checkbox changed: " + checkbox.isSelected());
    if(listener != null) listener.onOptionUpdate(this); 
  }
}
