package org.okcrobot.scouter.ui.component;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * A collection of JSpinners that are used to change the time.
 * 
 * @author Caleb L. Power
 */
public class TimeSpinner extends JPanel {
  private static final long serialVersionUID = -5843040542833095993L;
  
  private JSpinner minuteSpinner = null;
  private JSpinner secondSpinner = null;
  private JSpinner millisecondSpinner = null;
  
  /**
   * Overloaded constructor to set the time.
   * 
   * @param time match time in milliseconds
   */
  public TimeSpinner(long time) {
    long minute = time / 60000L;
    long second = time % 60000L / 1000;
    long millisecond = time % 60000L - (second * 1000);
    
    minuteSpinner = generateSpinner(minute, 0, 60);
    secondSpinner = generateSpinner(second, 0, 60);
    millisecondSpinner = generateSpinner(millisecond, 0, 999);
    
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    add(minuteSpinner);
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(new JLabel(":"));
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(secondSpinner);
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(new JLabel("."));
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(millisecondSpinner);
  }
  
  /**
   * Sets the time.
   * 
   * @param time the match time in milliseconds
   * @return this TimeSpinner object
   */
  public TimeSpinner setTime(long time) {
    long minute = time / 60000L;
    long second = time % 60000L / 1000;
    long millisecond = time % 60000L - (second * 1000);
    
    minuteSpinner.setValue(minute);
    secondSpinner.setValue(second);
    millisecondSpinner.setValue(millisecond);
    
    return this;
  }
  
  /**
   * Generates an individual spinner.
   * 
   * @param value the initial value of the spinner
   * @param minimum the minimum value that the spinner may show
   * @param maximum the maximum value that the spinner may show
   * @return the JSpinner that is generated
   */
  private JSpinner generateSpinner(long value, int minimum, int maximum) {
    SpinnerNumberModel model = new SpinnerNumberModel(value, minimum, maximum, 1);
    JSpinner spinner = new JSpinner(model);
    spinner.setMaximumSize(new Dimension(50, 50));
    return spinner;
  }
  
  /**
   * Retrieves the minute value shown on the minute spinner.
   * 
   * @return integer representation of the minute spinner
   */
  public int getMinute() {
    return ((Integer)minuteSpinner.getValue());
  }
  
  /**
   * Retrieves the second value shown on the second spinner.
   * 
   * @return integer representation of the second spinner
   */
  public int getSecond() {
    return ((Integer)secondSpinner.getValue());
  }
  
  /**
   * Retrieves the millisecond value shown on the millisecond spinner.
   * 
   * @return integer representation of the millisecond spinner
   */
  public int getMillisecond() {
    return ((Integer)millisecondSpinner.getValue());
  }
  
}
