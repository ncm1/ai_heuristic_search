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
        JTextField fileInputField = new JTextField("Input File Name");

        private String selectedMap;
        private static final String PredefinedMaps   =  "./Maps/PredefinedMaps/Map";
        private static final String UserGeneratedMap =  "./Maps/UserGeneratedMaps/";
        private static final String GENERATEDMAP     =  "User Generated Map";

        private static final String Map1 = "Map 1";
        private static final String Map2 = "Map 2";
        private static final String Map3 = "Map 3";
        private static final String Map4 = "Map 4";
        private static final String Map5 = "Map 5";

        private static final String Var1  = "Variation 1";
        private static final String Var2  = "Variation 2";
        private static final String Var3  = "Variation 3";
        private static final String Var4  = "Variation 4";
        private static final String Var5  = "Variation 5";
        private static final String Var6  = "Variation 6";
        private static final String Var7  = "Variation 7";
        private static final String Var8  = "Variation 8";
        private static final String Var9  = "Variation 9";
        private static final String Var10 = "Variation 10";

        public MapSelection(){ //constructor
          super("Main Menu - Map Selection");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          JPanel pane = new JPanel(new GridLayout(4, 1, 8, 8));

          //Setting the Title and location
          JLabel title = new JLabel("Main Menu - Map Selection", SwingConstants.CENTER);
          title.setVerticalAlignment(SwingConstants.CENTER);
          Font font = new Font("Cambria", Font.BOLD, 30);
          title.setFont(font);

          JPanel parentPanel1 = new JPanel(new GridLayout(0,4));
          //Setting the default text to display
          mapSelection.setPrototypeDisplayValue("Choose a map to load");
          //Populate the combobox with the possible options
          mapSelection.addItem(Map1);
          mapSelection.addItem(Map2);
          mapSelection.addItem(Map3);
          mapSelection.addItem(Map4);
          mapSelection.addItem(Map5);
          mapSelection.addItem(GENERATEDMAP);
          ((JLabel)mapSelection.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
          ((JLabel)mapSelection.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

          //Setting the default text to display
          variantSelection.setPrototypeDisplayValue("Choose a map variant");
          //Populate the combobox with the possible options
          variantSelection.addItem(Var1);
          variantSelection.addItem(Var2);
          variantSelection.addItem(Var3);
          variantSelection.addItem(Var4);
          variantSelection.addItem(Var5);
          variantSelection.addItem(Var6);
          variantSelection.addItem(Var7);
          variantSelection.addItem(Var8);
          variantSelection.addItem(Var9);
          variantSelection.addItem(Var10);
          ((JLabel)variantSelection.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
          ((JLabel)variantSelection.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

          // Null labels for null in gridlayout
          JLabel nullLabel1 = new JLabel("");
          JLabel nullLabel2 = new JLabel("");

          parentPanel1.add(nullLabel1);
          parentPanel1.add(mapSelection);
          parentPanel1.add(variantSelection);
          parentPanel1.add(nullLabel2);

          // Null labels for null in gridlayout
          JLabel nullLabel3 = new JLabel("");
          JLabel nullLabel4 = new JLabel("");
          JPanel parentPanel2 = new JPanel(new GridLayout(0,3));
          parentPanel2.add(nullLabel3);
          parentPanel2.add(fileInputField);
          parentPanel2.add(nullLabel4);

          //confirm icon as a button on the gui
          ImageIcon confirm_Icon  = new ImageIcon("icons/confirm.png");
          confirm = new JButton(confirm_Icon);

          confirm.setOpaque(false);
          confirm.setContentAreaFilled(false);
          confirm.setBorderPainted(false);
          confirm.addActionListener(this);

          JPanel parentPanel3 = new JPanel(new GridLayout(0,1));
          parentPanel3.add(confirm);
          //Add the title, mapSelection, and confirm icon to the interface
          pane.add(title);
          pane.add(parentPanel1);
          pane.add(parentPanel2);
          pane.add(parentPanel3);

          getContentPane().add(pane);
          setExtendedState(JFrame.MAXIMIZED_BOTH);
          setVisible(true);
        }

        //*******************   Action Listener     **********************
        @Override
        public void actionPerformed(ActionEvent e) {
          Object source = e.getSource();

          if(source == confirm){
              String selectedMap     = (String)mapSelection.getSelectedItem();
              String selectedVariant = (String)variantSelection.getSelectedItem();

              String fileName = "";
              String path = (String)fileInputField.getText();

              if(selectedMap == GENERATEDMAP)
                  fileName += UserGeneratedMap + path;
              else
                fileName  += PredefinedMaps + getConcatenation(selectedMap, selectedVariant);

              MapReader mapreader = new MapReader(fileName);
              setVisible(false);
              RunMVCTest runMVC = new RunMVCTest(mapreader.get_char_map(), mapreader.getStartGoalPair());
              //onSelected(selected);
          }
        }
        public String getConcatenation(String m, String v)
        {
          String result = "";

          switch(m)
          {
            case Map1: result = "0/map-0-v";
                       break;
            case Map2: result = "1/map-1-v";
                       break;
            case Map3: result = "2/map-2-v";
                       break;
            case Map4: result = "3/map-3-v";
                       break;
            case Map5: result = "4/map-4-v";
                       break;
            default:   result = "INVALID";
                       break;
          }

          switch(v)
          {
            case Var1:  result += "0.txt";
                        break;
            case Var2:  result += "1.txt";
                        break;
            case Var3:  result += "2.txt";
                        break;
            case Var4:  result += "3.txt";
                        break;
            case Var5:  result += "4.txt";
                        break;
            case Var6:  result += "5.txt";
                        break;
            case Var7:  result += "6.txt";
                        break;
            case Var8:  result += "7.txt";
                        break;
            case Var9:  result += "8.txt";
                        break;
            case Var10: result += "9.txt";
                        break;
            default:    result = "INVALID";
                        break;
          }

          return result;
        }

        public static void main(String[] args) {
                MapSelection ms = new MapSelection();//makes new ButtonGrid with 2 parameters
        }
}
//reference: https://www.wikihow.com/Make-a-GUI-Grid-in-Java
