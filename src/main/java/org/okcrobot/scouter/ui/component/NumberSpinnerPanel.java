package org.okcrobot.scouter.ui.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;

public class NumberSpinnerPanel extends JPanel {
  private JLabel label = null;
  private JSpinner spinner = null;
  private SpinnerNumberModel spinnerModel = null;
  
  public NumberSpinnerPanel(String text, Comparable min, Comparable max) {
    label = new JLabel(text);
    spinnerModel = new SpinnerNumberModel(0, min, max, 1);
    spinner = new JSpinner(spinnerModel);
    ((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);
    
    setLayout(new GridBagLayout());
    DynamicGridBagConstraints constraints = new DynamicGridBagConstraints().setGridWidth(4);
    add(label, constraints);
    add(spinner, constraints.setGridX(4).setGridWidth(1));
  }
  
  public int getValue() {
    try {
      return ((Integer)spinnerModel.getValue()).intValue();
    } catch(ClassCastException e) { }
    return 0;
  }
}
