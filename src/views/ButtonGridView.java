package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

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

        public void addCellListener(ActionListener cellListener){
          for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
              grid[i][j].addActionListener(cellListener);
            }
          }
        }

        public JButton[][] getGrid(){
          return grid;
        }

        public static void main(String[] args) {
                ButtonGridView bgt = new ButtonGridView(120,160);//makes new ButtonGrid with 2 parameters
        }
}
//reference: https://www.wikihow.com/Make-a-GUI-Grid-in-Java
