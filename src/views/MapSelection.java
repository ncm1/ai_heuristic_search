package views;

import views.MainMenu;
import main.RunMVCTest;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MapSelection extends JFrame implements ActionListener{

        JLabel[][] grid; //names the grid of buttons
        JLabel temp;
        JPanel pane;

        JComboBox<String> mapSelection = new JComboBox<String>();
        JComboBox<String> variantSelection = new JComboBox<String>();

        JButton confirm;
        JButton back;
        private String selectedMap;

        public MapSelection(){ //constructor
          super("Predefined Map Selection");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          JPanel pane = new JPanel(new GridLayout(3, 1, 8, 8));

          //Setting the Title and location
          JLabel title = new JLabel("Predefined Map Selection", SwingConstants.CENTER);
          title.setVerticalAlignment(SwingConstants.CENTER);
          Font font = new Font("Cambria", Font.BOLD, 30);
          title.setFont(font);

          JPanel parentPanel1 = new JPanel(new GridLayout(0,4));
          //Setting the default text to display
          mapSelection.setPrototypeDisplayValue("Choose a map to load");
          //Populate the combobox with the possible options
          mapSelection.addItem("Map 1");
          mapSelection.addItem("Map 2");
          mapSelection.addItem("Map 3");
          mapSelection.addItem("Map 4");
          mapSelection.addItem("Map 5");
          ((JLabel)mapSelection.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
          ((JLabel)mapSelection.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

          //Setting the default text to display
          variantSelection.setPrototypeDisplayValue("Choose a map variant");
          //Populate the combobox with the possible options
          variantSelection.addItem("Variation 1");
          variantSelection.addItem("Variation 2");
          variantSelection.addItem("Variation 3");
          variantSelection.addItem("Variation 4");
          variantSelection.addItem("Variation 5");
          variantSelection.addItem("Variation 6");
          variantSelection.addItem("Variation 7");
          variantSelection.addItem("Variation 8");
          variantSelection.addItem("Variation 9");
          variantSelection.addItem("Variation 10");
          ((JLabel)variantSelection.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
          ((JLabel)variantSelection.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

          // Null labels for null in gridlayout
          JLabel nullLabel1 = new JLabel("");
          JLabel nullLabel2 = new JLabel("");

          parentPanel1.add(nullLabel1);
          parentPanel1.add(mapSelection);
          parentPanel1.add(variantSelection);
          parentPanel1.add(nullLabel2);

          //confirm icon as a button on the gui
          ImageIcon confirm_Icon  = new ImageIcon("icons/confirm.png");
          confirm = new JButton(confirm_Icon);

          confirm.setOpaque(false);
          confirm.setContentAreaFilled(false);
          confirm.setBorderPainted(false);
          confirm.addActionListener(this);
          //back icon as a button on gui
          ImageIcon back_Icon = new ImageIcon("icons/back.png");
          back = new JButton(back_Icon);

          back.setOpaque(false);
          back.setContentAreaFilled(false);
          back.setBorderPainted(false);
          back.addActionListener(this);

          JPanel parentPanel2 = new JPanel(new GridLayout(0,2));
          parentPanel2.add(back);
          parentPanel2.add(confirm);
          //Add the title, mapSelection, and confirm icon to the interface
          pane.add(title);
          pane.add(parentPanel1);
          pane.add(parentPanel2);
          //pane.add(mapSelection);
          //pane.add(variantSelection);
          //pane.add(confirm);
          //pane.add(back);

          getContentPane().add(pane);
          setExtendedState(JFrame.MAXIMIZED_BOTH);
          setVisible(true);
        }

        //*******************   Action Listener     **********************
        @Override
        public void actionPerformed(ActionEvent e) {
          Object source = e.getSource();

          if(source == confirm){
              String selectedMap = (String)mapSelection.getSelectedItem();
              String selectedVariant = (String)variantSelection.getSelectedItem();  
              setVisible(false);
              //TODO: RunMVCTest(pass map and variant);
              RunMVCTest runMVC = new RunMVCTest(selectedMap, selectedVariant);
              //onSelected(selected);
          }
          else if(source == back){
              setVisible(false);
              MainMenu menu = new MainMenu();
          }
        }
        public void onSelected(String s){

        }

        public static void main(String[] args) {
                MapSelection ms = new MapSelection();//makes new ButtonGrid with 2 parameters
        }
}
//reference: https://www.wikihow.com/Make-a-GUI-Grid-in-Java
