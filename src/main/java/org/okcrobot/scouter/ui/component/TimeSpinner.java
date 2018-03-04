package org.okcrobot.scouter.ui.component;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;

public class TimeSpinner extends JPanel {
  
  private JSpinner minuteSpinner = null;
  private JSpinner secondSpinner = null;
  private JSpinner millisecondSpinner = null;
  
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
  
  public TimeSpinner setTime(long time) {
    long minute = time / 60000L;
    long second = time % 60000L / 1000;
    long millisecond = time % 60000L - (second * 1000);
    
    minuteSpinner.setValue(minute);
    secondSpinner.setValue(second);
    millisecondSpinner.setValue(millisecond);
    
    return this;
  }
  
  private JSpinner generateSpinner(long value, int minimum, int maximum) {
    SpinnerNumberModel model = new SpinnerNumberModel(value, minimum, maximum, 1);
    JSpinner spinner = new JSpinner(model);
    spinner.setMaximumSize(new Dimension(50, 50));
    return spinner;
  }
  
  public int getMinute() {
    return ((Integer)minuteSpinner.getValue());
  }
  
  public int getSecond() {
    return ((Integer)secondSpinner.getValue());
  }
  
  public int getMillisecond() {
    return ((Integer)millisecondSpinner.getValue());
  }
  
}
