package org.okcrobot.scouter.ui.component;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class OptionGroup extends JPanel {
  
  private DynamicGridBagConstraints constraints = null;
  
  public OptionGroup(String title) {
    TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
    titledBorder.setTitleJustification(TitledBorder.CENTER);
    setBorder(titledBorder);
    setLayout(new GridBagLayout());
    constraints = new DynamicGridBagConstraints()
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
  
}
