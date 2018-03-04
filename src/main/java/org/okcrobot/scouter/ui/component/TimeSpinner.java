package org.okcrobot.scouter.ui.component;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TimeSpinner extends JPanel {
  
  private JSpinner minuteSpinner = null;
  private JSpinner secondSpinner = null;
  private JSpinner millisecondSpinner = null;
  private SpinnerNumberModel minuteSpinnerModel = null;
  private SpinnerNumberModel secondSpinnerModel = null;
  private SpinnerNumberModel millisecondSpinnerModel = null;
  
  public TimeSpinner(long time) {
    long minute = time / 60000L;
    long second = time % 60000L / 1000;
    long millisecond = time % 60000L - (second * 1000);
    
    minuteSpinnerModel = new SpinnerNumberModel(minute, 0, 60, 1);
    minuteSpinner = new JSpinner(minuteSpinnerModel);
    secondSpinnerModel = new SpinnerNumberModel(second, 0, 60, 1);
    secondSpinner = new JSpinner(secondSpinnerModel);
    millisecondSpinnerModel = new SpinnerNumberModel(millisecond, 0, 999, 1);
    millisecondSpinner = new JSpinner(millisecondSpinnerModel);
    
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    add(minuteSpinner);
    add(Box.createHorizontalGlue());
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(new JLabel(":"));
    add(Box.createHorizontalGlue());
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(secondSpinner);
    add(Box.createHorizontalGlue());
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(new JLabel("."));
    add(Box.createHorizontalGlue());
    add(Box.createRigidArea(new Dimension(10, 0)));
    add(millisecondSpinner);
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
