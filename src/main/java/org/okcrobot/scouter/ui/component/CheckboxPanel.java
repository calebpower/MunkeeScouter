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

/**
 * Checkbox panel with label.
 * 
 * @author Caleb L. Power
 */
public class CheckboxPanel extends JPanel implements Selectable {
  private static final long serialVersionUID = -84467251141630392L;
  
  private boolean initiallySelected = false;
  private Component me = null;
  private JLabel label = null;
  private JCheckBox checkbox = null;
  private OptionListener listener = null;
  
  /**
   * Overloaded constructor to set the label text and the state of the checkbox.
   * 
   * @param text the label text
   * @param selected <code>true</code> if the checkbox should be selected initially or
   *        <code>false</code> if the checkbox should be deselected initially 
   */
  public CheckboxPanel(String text, boolean selected) {
    initiallySelected = selected;
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
    
    checkbox.addActionListener(new ActionListener() { //notify listeners on update
      @Override public void actionPerformed(ActionEvent event) {
        if(((checkbox.isSelected() && !initiallySelected)
                || (!checkbox.isSelected() && initiallySelected))
            && listener != null
            && me != null) listener.onOptionUpdate(me);
      }
    });
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public boolean equals(Component component) {
    return this == component;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public int getValue() {
    return checkbox.isSelected() ? 1 : 0;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void setSelected(boolean selected) {
    label.setForeground(selected ? Color.GREEN : Color.BLACK);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void setListener(OptionListener listener) {
    if(me == null) me = this;
    this.listener = listener;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onKeyUp() {
    if(checkbox.isSelected()) return;
    checkbox.setSelected(true);
    if(!initiallySelected && listener != null) listener.onOptionUpdate(this);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onKeyDown() {
    if(!checkbox.isSelected()) return;
    checkbox.setSelected(false);
    if(initiallySelected && listener != null) listener.onOptionUpdate(this); 
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void reset() {
    checkbox.setSelected(initiallySelected);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void setEnabled(boolean enabled) {
    checkbox.setEnabled(enabled);
  }
}
