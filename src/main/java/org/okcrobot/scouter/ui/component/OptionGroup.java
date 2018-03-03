package org.okcrobot.scouter.ui.component;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

import org.okcrobot.scouter.model.timer.GamePhase;

/**
 * Groups various options together (presumably by FRC {@link GamePhase}
 * 
 * @author Caleb L. Power
 */
public class OptionGroup extends BorderedPanel {
  private static final long serialVersionUID = -2267282713258890953L;
  
  private DynamicGridBagConstraints constraints = null;
  
  /**
   * Overloaded constructor to set the title of the option group.
   * 
   * @param title the title of the option group
   */
  public OptionGroup(String title) {
    super(title);
    setLayout(new GridBagLayout());
    constraints = new DynamicGridBagConstraints()
        .setWeightX(0)
        .setWeightY(0)
        .setInsets(new Insets(0, 0, 10, 0))
        .setFill(GridBagConstraints.HORIZONTAL);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public Component add(Component component) {
    add(component, constraints.setGridY(constraints.gridy + 1));
    return component;
  }
  
  /**
   * Pads the option group on the bottom so that vertical spacing doesn't become
   * an issue. <strong>To be used only after all elements have been added.</strong>
   */
  public void pad() {
    constraints.setWeightY(1);
    add(new JLabel());
  }
  
}
