package org.okcrobot.scouter.ui.component;

import java.awt.Color;
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

/**
 * Number spinner with label.
 * 
 * @author Caleb L. Power
 */
public class NumberSpinnerPanel extends JPanel implements Selectable {
  private static final long serialVersionUID = 950645593888496942L;
  
  private JLabel label = null;
  private JSpinner spinner = null;
  private OptionListener listener = null;
  private SpinnerNumberModel spinnerModel = null;
  
  /**
   * Overloaded constructor to set the label text and spinner bounds.
   * 
   * @param text the text of the label
   * @param min the minimum value that the spinner can be
   * @param max the maximum value that the spinner can be
   */
  public NumberSpinnerPanel(String text, Comparable<Integer> min, Comparable<Integer> max) {
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
        if(listener != null) listener.onOptionUpdate((Component)event.getSource());
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
    try {
      return ((Integer)spinnerModel.getValue()).intValue();
    } catch(ClassCastException e) { }
    return 0;
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
    this.listener = listener;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onKeyUp() {
    Integer newValue = (Integer)spinner.getValue() + 1;
    @SuppressWarnings("unchecked") Comparable<Integer> upperBound = spinnerModel.getMaximum();
    if(upperBound != null && upperBound.compareTo(newValue) < 0) return;
    spinner.setValue(newValue);
    if(listener != null) listener.onOptionUpdate(this);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onKeyDown() {
    Integer newValue = (Integer)spinner.getValue() - 1;
    @SuppressWarnings("unchecked") Comparable<Integer> lowerBound = spinnerModel.getMinimum();
    if(lowerBound != null && lowerBound.compareTo(newValue) > 0) return;
    spinner.setValue(newValue);
    //note: do not notify listeners about mistakes--sweep them under the carpet instead ;)
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void reset() {
    spinner.setValue(0);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void setEnabled(boolean enabled) {
    spinner.setEnabled(enabled);
  }
  
}
