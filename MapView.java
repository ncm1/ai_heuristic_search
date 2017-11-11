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

  private JLabel columnLabel = new JLabel("column-null", SwingConstants.CENTER);
  private JLabel rowLabel    = new JLabel("row-null", SwingConstants.CENTER);


  public MapView(ButtonGridView bgv)
  {
    //frame in constructor and not an attribute as doesn't need to be visible to whole class
    Frame frame 		= new Frame("simple MVC");

    tabPane  = new JTabbedPane();
    //Adding all the components together to create the JFram
    parentPanel = new JPanel(new BorderLayout());

    JPanel titlePanel = new JPanel(new GridLayout(0,3));
    JLabel title = new JLabel("Input coordinates: ", SwingConstants.CENTER);
    title.setVerticalAlignment(SwingConstants.CENTER);
    Font font = new Font("Cambria", Font.BOLD, 30);
    title.setFont(font);

    JTextField jtf = new JTextField ("");
    JButton jb = new JButton("Retrieve Cell Data");
    titlePanel.add(title);
    titlePanel.add(jtf);
    titlePanel.add(jb);

    map = bgv;
    /*q1 = new ButtonGridView(60,80);
    q2 = new ButtonGridView(60,80);
    q3 = new ButtonGridView(60,80);
    q4 = new ButtonGridView(60,80);
    */

    parentPanel.add(titlePanel, BorderLayout.SOUTH);
    parentPanel.add(map.getContentPane(), BorderLayout.CENTER);

    tabPane.addTab("Map - Full", parentPanel);

    /* Adding the quadrants to the mapview tabbedpane
    tabPane.addTab("Map - Q1", q1.getContentPane());
    tabPane.addTab("Map - Q2", q2.getContentPane());
    tabPane.addTab("Map - Q3", q3.getContentPane());
    tabPane.addTab("Map - Q4", q4.getContentPane());
    */

    JPanel cellpane = new JPanel(new GridLayout(3,0));
    cellpane.add(rowLabel);
    cellpane.add(columnLabel);
    tabPane.addTab("Cell Data", cellpane);

    tabPane.addChangeListener(this);
    frame.add(tabPane);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.pack();
    frame.setVisible(true);
  }

  public void setColumnLabel(int c){
    columnLabel.setText("column - " + c);
  }

  public void setRowLabel(int r){
    rowLabel.setText("row - " + r);
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
