package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.border.Border;
import util.TreeNode;

public class ButtonGridView extends JFrame{

        JButton[][] grid; //names the grid of buttons
        JFrame self;
        JButton temp;
        JPanel pane;
        private int row = -1;
        private int column = -1;
        private int rowSize = 0;
        private int columnSize = 0;

        //TODO: Make constructor read the map directly from the file
        public ButtonGridView(int rows, int columns){ //constructor
          this.row = rows; this.column = columns;
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pane = new JPanel();
          pane.setLayout(new GridLayout(rows,columns)); //set layout
          grid = new JButton[rows][columns]; //allocate the size of grid

          //declare the border color to be used in each of the jlabels
          Border border = BorderFactory.createLineBorder(Color.BLACK);

          final int size = rows * columns;
          rowSize = rows;
          columnSize = columns;
          for(int x = 0; x < rowSize; x++)
          {
            for(int y = 0; y < columnSize; y++)
            {
              temp = new JButton(); //creates new JLabel

              if(x == 0 && y == 0){
                  temp.setBackground(Color.red);
                  temp.setOpaque(true);
              }
              if(x == rows - 1 && y == columns - 1){
                temp.setBackground(Color.red);
                temp.setOpaque(true);
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
        
        public void addCellListener(ActionListener cellListener){
          for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
              grid[i][j].addActionListener(cellListener);
            }
          }
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

        public JButton[][] getGrid(){
          return grid;
        }

        public static void main(String[] args) {
                ButtonGridView bgt = new ButtonGridView(120,160);//makes new ButtonGrid with 2 parameters
        }
}
//reference: https://www.wikihow.com/Make-a-GUI-Grid-in-Java
