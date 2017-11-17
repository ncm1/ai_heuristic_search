package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.Border;
import util.Coordinates;
import util.TreeNode;

public class ButtonGridView extends JFrame{

        JButton[][] grid; //names the grid of buttons
        JFrame self;
        JButton temp;
        JPanel pane;
        private static final int ROWS    = 120;
        private static final int COLUMNS = 160;
        private int row = -1;
        private int column = -1;
        private int rowSize = 0;
        private int columnSize = 0;

        public ButtonGridView(char[][] char_grid, ArrayList<Coordinates> s_e_pair){ //constructor

          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pane = new JPanel();
          pane.setLayout(new GridLayout(ROWS,COLUMNS)); //set layout
          grid = new JButton[ROWS][COLUMNS]; //allocate the size of grid

          //declare the border color to be used in each of the jlabels
          Border border = BorderFactory.createLineBorder(Color.BLACK);

          Color lightBrown = new Color(166, 123, 91);
          Color lightBlue  = new Color(173, 216, 230);


          //TODO: Add start and end goal to the map
          for(int x = 0; x < ROWS; x++)
          {
            for(int y = 0; y < COLUMNS; y++)
            {
              temp = new JButton(); //creates new JLabel


              if(char_grid[x][y] == '2')
                temp.setBackground(lightBrown);

              if(char_grid[x][y] == '0')
                temp.setBackground(Color.black);

              if(char_grid[x][y] == 'a' || char_grid[x][y] == 'b' )
                temp.setBackground(lightBlue);

              temp.setOpaque(true);
              temp.setBorder(border);
              grid[x][y] = temp;
              pane.add(grid[x][y]); //adds button to grid

            }

          }

          int start_x = s_e_pair.get(0).get_x_coordinate();
          int start_y = s_e_pair.get(0).get_y_coordinate();

          int goal_x  = s_e_pair.get(1).get_x_coordinate();
          int goal_y  = s_e_pair.get(1).get_y_coordinate();

          grid[goal_x][goal_y].setBackground(Color.red);

          grid[start_x][start_y].setBackground(Color.red);


          pack();
          setExtendedState(JFrame.MAXIMIZED_BOTH);
          setLocationRelativeTo(null);
          getContentPane().add(pane);
      }


	public void showMap(char[][] char_grid, int[]start, int[]goal){
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pane = new JPanel();
          pane.setLayout(new GridLayout(row,column)); //set layout
          //declare the border color to be used in each of the jlabels
          Border border = BorderFactory.createLineBorder(Color.BLACK);
          Color brown = new Color(153,76,0);

          int center_x;
          int center_y;
          for(int x = 0; x < row; x++)
          {
            for(int y = 0; y < column; y++)
            {
              temp = new JButton(); //creates new JLabel

              /*if(char_grid[x][y] == '1'){
                  temp.setBackground(Color.r);
                  temp.setOpaque(true);
              }*/
              //char_grid[coordinateArray.get(x).get_x_coordinate()][coordinateArray.get(x).get_y_coordinate()] = ;
              /*grid[][coordinateArray.get(x).get_y_coordinate()] = temp;
              pane.add(grid[coordinateArray.get(x).get_x_coordinate()][coordinateArray.get(x).get_y_coordinate()]);

              if(x == coordinateArray.get(x).get_x_coordinate() && )
              {
                temp.setBackground(Color.magenta);
                temp.setOpaque(true);
              }*/
              if(char_grid[x][y] == '2'){
                temp.setBackground(brown);
                temp.setOpaque(true);
              }
              if(char_grid[x][y] == '0'){
                temp.setBackground(Color.black);
                temp.setOpaque(true);
              }

              if(char_grid[x][y] == 'a' ){
                temp.setBackground(Color.cyan);
                temp.setOpaque(true);
              }
              if(char_grid[x][y] == 'b' ){
                temp.setBackground(Color.blue);
                temp.setOpaque(true);
              }
              if (x == start[0] && y == start[1]){
                temp.setText("S");
                temp.setOpaque(false);
              }
              if (x == goal[0] && y == goal[1]){
                temp.setText("G");
                temp.setOpaque(false);
              }
              temp.setBorder(border);
              grid[x][y] = temp;
              pane.add(grid[x][y]); //adds button to grid
            }
          }

          pack();
          setExtendedState(JFrame.MAXIMIZED_BOTH);
          setLocationRelativeTo(null);
          getContentPane().add(pane);
          setVisible(true);
        }

	public void tracePath(TreeNode treeNode){
            ArrayList<int[]> path = new ArrayList<>();
            TreeNode tmp = treeNode;
            while (tmp != null){
                path.add(tmp.coord);
                tmp = tmp.parent;
            }
            Border border = BorderFactory.createLineBorder(Color.BLACK);
            path.forEach( coord -> {
                temp = new JButton();
                temp.setBorder(border); 
                temp.setBackground(Color.orange);
                temp.setOpaque(true);
                grid[coord[0]][coord[1]] = temp;
            });
            // for each coordinate, color the box red
        }

        public void addCellListener(ActionListener cellListener){
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
