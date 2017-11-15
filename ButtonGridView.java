import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.Border;

public class ButtonGridView extends JFrame{

        JButton[][] grid; //names the grid of buttons
        JFrame self;
        JButton temp;
        JPanel pane;
        private static final int ROWS    = 120;
        private static final int COLUMNS = 160;

        public ButtonGridView(char[][] char_grid, ArrayList<Coordinates> s_e_pair){ //constructor

          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pane = new JPanel();
          pane.setLayout(new GridLayout(ROWS,COLUMNS)); //set layout
          grid = new JButton[ROWS][COLUMNS]; //allocate the size of grid

          //declare the border color to be used in each of the jlabels
          Border border = BorderFactory.createLineBorder(Color.BLACK);

          Color lightBrown = new Color(166, 123, 91);
          Color lightBlue  = new Color(173, 216, 230);

          int start_x = s_e_pair.get(0).get_x_coordinate();
          int start_y = s_e_pair.get(0).get_y_coordinate();

          int goal_x  = s_e_pair.get(1).get_x_coordinate();
          int goal_y  = s_e_pair.get(1).get_y_coordinate();

          //TODO: Add start and end goal to the map
          for(int x = 0; x < ROWS; x++)
          {
            for(int y = 0; y < COLUMNS; y++)
            {
              temp = new JButton(); //creates new JLabel

              if(x == goal_x && y == goal_y)
                temp.setBackground(Color.red);

              else if(x == start_x && y == start_y)
                temp.setBackground(Color.red);

              else
              {

                if(char_grid[x][y] == '2')
                  temp.setBackground(lightBrown);

                if(char_grid[x][y] == '0')
                  temp.setBackground(Color.black);

                if(char_grid[x][y] == 'a' || char_grid[x][y] == 'b' )
                  temp.setBackground(lightBlue);

              }

            temp.setOpaque(true);
            temp.setBorder(border);
            grid[x][y] = temp;
            pane.add(grid[x][y]); //adds button to grid
          }

          pack();
          setExtendedState(JFrame.MAXIMIZED_BOTH);
          setLocationRelativeTo(null);
          getContentPane().add(pane);
        }
      }

        void addCellListener(ActionListener cellListener){
          for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
              grid[i][j].addActionListener(cellListener);
            }
          }
        }

        public JButton[][] getGrid(){
          return grid;
        }
}
//reference: https://www.wikihow.com/Make-a-GUI-Grid-in-Java
