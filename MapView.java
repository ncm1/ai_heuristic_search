import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.math.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.util.Observable;
import javax.swing.event.*;


class MapView implements ChangeListener
{
  //Create main tabePane object where each of the tabs will be place
  JTabbedPane tabPane;
  private JPanel parentPanel;
  private ButtonGridView map;
  private ButtonGridView q1;
  private ButtonGridView q2;
  private ButtonGridView q3;
  private ButtonGridView q4;

  private JLabel rowLabel    = new JLabel("x: null"   , SwingConstants.CENTER);
  private JLabel columnLabel = new JLabel("y: null"   , SwingConstants.CENTER);
  private JLabel fLabel      = new JLabel("f: null"   , SwingConstants.CENTER);
  private JLabel gLabel      = new JLabel("g: null"   , SwingConstants.CENTER);
  private JLabel hLabel      = new JLabel("h: null"   , SwingConstants.CENTER);
  private JLabel timeLabel   = new JLabel("time: null", SwingConstants.CENTER);

  public MapView(ButtonGridView bgv)
  {
    //frame in constructor and not an attribute as doesn't need to be visible to whole class
    Frame frame 		= new Frame("simple MVC");

    tabPane  = new JTabbedPane();
    //Adding all the components together to create the JFram
    parentPanel     = new JPanel(new BorderLayout());
    JPanel cellData = new JPanel(new GridLayout(0,6));

    Font font = new Font("Cambria", Font.BOLD, 28);
    rowLabel.setFont(font);
    columnLabel.setFont(font);
    fLabel.setFont(font);
    gLabel.setFont(font);
    hLabel.setFont(font);
    timeLabel.setFont(font);

    //Adding the labels to the cell data panel
    cellData.add(rowLabel);
    cellData.add(columnLabel);
    cellData.add(fLabel);
    cellData.add(gLabel);
    cellData.add(hLabel);
    cellData.add(timeLabel);

    map = bgv;
    //TODO: Implement partial MapViews to allow splitting the map into quadrants
    /*
    q1 = new ButtonGridView(60,80);
    q2 = new ButtonGridView(60,80);
    q3 = new ButtonGridView(60,80);
    q4 = new ButtonGridView(60,80);
    */

    parentPanel.add(cellData, BorderLayout.SOUTH);
    parentPanel.add(map.getContentPane(), BorderLayout.CENTER);

    tabPane.addTab("Map - Full", parentPanel);

    /*
    Adding the quadrants to the mapview tabbedpane
    tabPane.addTab("Map - Q1", q1.getContentPane());
    tabPane.addTab("Map - Q2", q2.getContentPane());
    tabPane.addTab("Map - Q3", q3.getContentPane());
    tabPane.addTab("Map - Q4", q4.getContentPane());
    */

    tabPane.addChangeListener(this);
    frame.add(tabPane);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.pack();
    frame.setVisible(true);
  }

  public void setColumnLabel(int c){
    columnLabel.setText("x: " + c);
  }

  public void setRowLabel(int r){
    rowLabel.setText("y: " + r);
  }

  public void set_f_Label(int f){
    fLabel.setText("f: " + f);
  }

  public void set_g_label(int g){
    gLabel.setText("g: " + g);
  }

  public void set_h_label(int h){
    hLabel.setText("h: " + h);
  }

  public void set_time_label(int t){
    hLabel.setText("time: " + t);
  }

  public void stateChanged(ChangeEvent e) {
    JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
    int selectedIndex = tabbedPane.getSelectedIndex();
  }

  public static class CloseListener extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      e.getWindow().setVisible(false);
      System.exit(0);
    } //windowClosing()
  } //CloseListener

}//end of MapView.java
