import javax.swing.*;
import java.awt.*;

public class UserGeneratedMapSelection extends JFrame{

        JLabel[][] grid; //names the grid of buttons
        JLabel temp;
        JPanel pane;
        private String selectedMap;

        public UserGeneratedMapSelection(){ //constructor
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pane = new JPanel();

        }

        public static void main(String[] args) {
                //MapSelection ms = new MapSelection();//makes new ButtonGrid with 2 parameters
        }
}
//reference: https://www.wikihow.com/Make-a-GUI-Grid-in-Java
