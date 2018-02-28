package org.okcrobot.scouter.ui.component;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Dynmically modified GridBag constraints (AWT)
 * 
 * @author Caleb L. Power
 */
public class DynamicGridBagConstraints extends GridBagConstraints {
  private static final long serialVersionUID = 32921321733653624L;
  
  /**
   * Null constructor to set default values.
   */
  public DynamicGridBagConstraints() {
    fill = GridBagConstraints.BOTH;
    gridx = 0;
    gridy = 0;
    gridheight = 1;
    gridwidth = 1;
    weightx = 1;
    weighty = 1;
  }
  
  /**
   * Sets the anchor, which is to be used when the component is smaller than
   * its display area.
   * 
   * @param anchor the new anchor value as specified by {@link GridBagConstraints#anchor}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setAnchor(int anchor) {
    this.anchor = anchor;
    return this;
  }
  
  /**
   * Sets the fill, which is to be used when the component's display area is
   * larger than the component's requestted size.
   * 
   * @param fill the new fill value as specified by {@link GridBagConstraints#fill}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setFill(int fill) {
    this.fill = fill;
    return this;
  }
  
  /**
   * Sets the number of cells in a column for the component's display area.
   * 
   * @param gridheight the new gridheight value as specified by {@link GridBagConstraints#gridheight}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setGridHeight(int gridheight) {
    this.gridheight = gridheight;
    return this;
  }
  
  /**
   * Sets the number of cells in a row for the component's display area.
   * 
   * @param gridwidth the new gridwidth value as specified by {@link GridBagConstraints#gridwidth}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setGridWidth(int gridwidth) {
    this.gridwidth = gridwidth;
    return this;
  }
  
  /**
   * Sets the cell containing the leading edge of the component's display area.
   * 
   * @param gridx the new gridx value as specified by {@link GridBagConstraints#gridx}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setGridX(int gridx) {
    this.gridx = gridx;
    return this;
  }
  
  /**
   * Increases the gridx value by one.
   * 
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints increaseGridX() {
    return shiftGridX(1);
  }
  
  /**
   * Decreases the gridx value by one.
   * 
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints decreaseGridX() {
    return shiftGridX(-1);
  }
  
  /**
   * Shifts gridx by the given value.
   * 
   * @param x the amount for which to shift gridx
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints shiftGridX(int x) {
    gridx += x;
    return this;
  }
  
  /**
   * Sets the cell at the top of the component's display area.
   * 
   * @param gridy the new gridy value as specified by {@link GridBagConstraints#gridy}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setGridY(int gridy) {
    this.gridy = gridy;
    return this;
  }
  
  /**
   * Increases the gridy value by one.
   * 
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints increaseGridY() {
    return shiftGridY(1);
  }
  
  /**
   * Decreases the gridy value by one.
   * 
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints decreaseGridY() {
    return shiftGridY(-1);
  }
  
  /**
   * Shifts gridy by the given value.
   * 
   * @param y the amount for which to shift gridy
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints shiftGridY(int y) {
    gridy += y;
    return this;
  }
  
  /**
   * Sets the external padding of the component.
   * 
   * @param insets the new insets as specified by {@link GridBagConstraints#insets}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setInsets(Insets insets) {
    this.insets = insets;
    return this;
  }
  
  /**
   * Sets the internal horizontal padding.
   * 
   * @param ipadx the new ipadx value as specified by {@link GridBagConstraints#ipadx}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setIPadX(int ipadx) {
    this.ipadx = ipadx;
    return this;
  }
  
  /**
   * Sets the internal vertical padding.
   * 
   * @param ipady the new ipady value as specified by {@link GridBagConstraints#ipady}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setIPadY(int ipady) {
    this.ipady = ipady;
    return this;
  }
  
  /**
   * Specifies how to distribute extra horizontal space.
   * 
   * @param weightx the new weightx value as specified by {@link GridBagConstraints#weightx}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setWeightX(double weightx) {
    this.weightx = weightx;
    return this;
  }
  
  /**
   * Specifies how to distribute extra vertical space.
   * @param weighty the new weighty value as specified by {@link GridBagConstraints#weighty}
   * @return this DynamicGridBagConstraints object
   */
  public DynamicGridBagConstraints setWeightY(double weighty) {
    this.weighty = weighty;
    return this;
  }
  
}
