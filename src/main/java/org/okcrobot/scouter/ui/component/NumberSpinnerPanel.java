package org.okcrobot.scouter.ui.component;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.okcrobot.scouter.ui.OptionListener;
import org.okcrobot.scouter.ui.Selectable;

public class NumberSpinnerPanel extends JPanel implements Selectable {
  private JLabel label = null;
  private JSpinner spinner = null;
  private OptionListener listener = null;
  private SpinnerNumberModel spinnerModel = null;
  
  public NumberSpinnerPanel(String text, Comparable min, Comparable max) {
    label = new JLabel(text);
    spinnerModel = new SpinnerNumberModel(0, min, max, 1);
    spinner = new JSpinner(spinnerModel);
    spinner.setMaximumSize(new Dimension(20, 20));
    spinner.setMinimumSize(new Dimension(10, 20));
    ((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
    
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    add(label);
    add(Box.createHorizontalGlue());
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(spinner);
    
    spinner.addChangeListener(new ChangeListener() {
      @Override public void stateChanged(ChangeEvent event) {
        System.out.println("Spinner changed: " + spinner.getValue());
        if(listener != null) listener.onOptionUpdate((Component)event.getSource());
      }
    });
  }
  
  public NumberSpinnerPanel(String text, Comparable min, Comparable max, OptionListener listener) {
    this(text, min, max);
    this.listener = listener;
  }
  
  @Override public boolean equals(Component component) {
    return spinner == component;
  }
  
  @Override public int getValue() {
    try {
      return ((Integer)spinnerModel.getValue()).intValue();
    } catch(ClassCastException e) { }
    return 0;
  }

  @Override public void setSelected(boolean selected) {
    // TODO Auto-generated method stub
    
  }

  @Override public void setListener(OptionListener listener) {
    this.listener = listener;
  }
  
}
