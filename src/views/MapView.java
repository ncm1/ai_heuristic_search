package views;

import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.event.*;


public class MapView extends JFrame
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
  private JLabel wLabel      = new JLabel("w: null"   , SwingConstants.CENTER);
  private JLabel timeLabel   = new JLabel("time: null", SwingConstants.CENTER);

  JButton back;
  Frame frame;

  public MapView(ButtonGridView bgv)
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame in constructor and not an attribute as doesn't need to be visible to whole class
    frame 		= new Frame("simple MVC");

    tabPane  = new JTabbedPane();
    //Adding all the components together to create the JFram
    parentPanel     = new JPanel(new BorderLayout());
    JPanel cellData = new JPanel(new GridLayout(1,6));

    Font font = new Font("Cambria", Font.BOLD, 20);
    rowLabel.setFont(font);
    columnLabel.setFont(font);
    fLabel.setFont(font);
    gLabel.setFont(font);
    hLabel.setFont(font);
    wLabel.setFont(font);
    timeLabel.setFont(font);

    ImageIcon back_Icon  = new ImageIcon("icons/backArrow.png");
    back = new JButton(back_Icon);

    back.setOpaque(false);
    back.setContentAreaFilled(false);
    back.setBorderPainted(false);
    back.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        MapSelection ms = new MapSelection();
      }
    });

    JPanel holdingPanel = new JPanel(new GridLayout(0,1));
    holdingPanel.add(back);
    //Adding the labels to the cell data panel
    cellData.add(holdingPanel);
    cellData.add(rowLabel);
    cellData.add(columnLabel);
    cellData.add(fLabel);
    cellData.add(gLabel);
    cellData.add(hLabel);
    cellData.add(wLabel);
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
    //tabPane.addChangeListener(this);
    add(tabPane);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    pack();
    setVisible(true);
  }

  public void setRowLabel(int r){
    rowLabel.setText("x: " + r);
  }

  public void setColumnLabel(int c){
    columnLabel.setText("y: " + c);
  }

  public void set_f_Label(double f){
    if ((int)f == -1){
        fLabel.setText("f: -");
    }
    else
        fLabel.setText("f: " + String.format("%.4f",f));
  }

  public void set_g_label(double g){
    if ((int)g == -1){
        gLabel.setText("g: -");
    }
    else
        gLabel.setText("g: " + String.format("%.4f",g));
  }

  public void set_h_label(double h){
    if ((int)h == -1){
        hLabel.setText("h: -");
    }
    else
      hLabel.setText("h: " + String.format("%.4f",h));
  }

  public void set_w_label(double w){
    if ((int)w == -1){
        wLabel.setText("w: -");
    }
    else
      wLabel.setText("w: " + String.format("%.4f",w));
  }

  public void set_time_label(long t){
      String str = String.format("time:  %5.4f ms", (double)t/Math.pow(10, 6));
      timeLabel.setText(str);
  }

  public void stateChanged(ChangeEvent e) {
    JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
    int selectedIndex = tabbedPane.getSelectedIndex();
  }

  public static class CloseListener extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      e.getWindow().setVisible(false);
      System.out.println("System exit");
      System.exit(0);
    } //windowClosing()
  } //CloseListener*/

}//end of MapView.java
