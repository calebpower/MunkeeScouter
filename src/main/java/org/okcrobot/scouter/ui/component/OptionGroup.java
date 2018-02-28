package org.okcrobot.scouter.ui.component;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class OptionGroup extends BorderedPanel {
  
  private DynamicGridBagConstraints constraints = null;
  
  public OptionGroup(String title) {
    super(title);
    setLayout(new GridBagLayout());
    constraints = new DynamicGridBagConstraints()
        .setWeightX(0)
        .setWeightY(0)
        .setInsets(new Insets(0, 0, 10, 0))
        .setFill(GridBagConstraints.HORIZONTAL);
  }
  
  @Override public Component add(Component component) {
    add(component, constraints.setGridY(constraints.gridy + 1));
    return component;
  }
  
  public List<Component> add(List<Component> components) {
    for(Component component : components)
      add(component);
    return components;
  }
  
  public void pad() {
    constraints.setWeightY(1);
    add(new JLabel());
  }
  
}
