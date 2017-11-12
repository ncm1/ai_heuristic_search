package generator;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.Border;
import util.Coordinates;

public class PuzzleGenerator extends JFrame{

        JButton[][] grid; //names the grid of buttons
        char[][] char_grid;
        ArrayList<Coordinates> coordinateArray = new ArrayList<Coordinates>();
        JFrame self;
        JButton temp;
        JPanel pane;

        Random randy = new Random();

        private int row = -1;
        private int column = -1;
        private int rowSize = 0;
        private int columnSize = 0;

        //TODO: Write the generated puzzle to file
        PuzzleGenerator(int rows, int columns){ //constructor
          this.row = rows;
          this.column = columns;
          //Initialize the map to correspond to unblocked cells
          char_grid = new char[rows][columns];
          grid      = new JButton[rows][columns];
          for(int x = 0; x < rows; x++)
          {
            for(int y = 0; y < columns; y++)
            {
              char_grid[x][y] = '1';
            }
          }
        }

        public void generateMap(){
          int x_rand = -1;
          int y_rand = -1;
          int min = 0;

          for(int i = 0; i < 8; i++){
            x_rand = randy.nextInt(row - min + 1) + min;
            y_rand = randy.nextInt(column - min + 1) + min;
            System.out.println("x_rand: " + x_rand + " y_rand: " + y_rand);
            coordinateArray.add(new Coordinates(x_rand,y_rand));
            placeHardTraversalCells(x_rand,y_rand);
          }


        }

        public void placeHardTraversalCells(int x, int y){
          int current_x = x;
          int current_y = y;

          int min_x = min_x(x);
          int max_x = max_x(x);

          //System.out.println("Max x: " + max_x + " Min x: " + min_x);

          int min_y = min_y(y);
          int max_y = max_y(y);

          //System.out.println("Max y: " + max_y + " Min y: " + min_y);

          current_x = min_x;
          current_y = min_y;
          Random rand = new Random();
          double prob_q = 0.0;
          int counter = 1;
          while(current_x < max_x)
          {
            while(current_y < max_y){
              prob_q = rand.nextDouble();
              //System.out.println("prob_q: " + prob_q);
              if(prob_q <= 0.5)
                char_grid[current_x][current_y] = '2';
              current_y += 1;
              //System.out.println("current_x " + current_x + " current_y " + current_y);
            }
            //System.out.println("current_x " + current_x + " current_y " + current_y);
            current_x += 1;
            current_y = min_y;
            //current_y += counter;
            counter   +=1;
          }
      }

        public int max_x(int x){
          int temp = x + 30;
          if(temp > (row - 1))
            return row;
          else
            return temp;
        }

        public int min_x(int x){
          int temp = x - 30;
          if(temp < 0)
            return 0;
          else
            return temp;
        }

        public int max_y(int y){
          int temp = y + 30;
          if(temp > (column - 1))
            return column;
          else
            return temp;
        }
        public int min_y(int y){
          int temp = y - 30;
          if(temp < 0)
            return 0;
          else
            return temp;
        }

        public void showMap(){
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pane = new JPanel();
          pane.setLayout(new GridLayout(row,column)); //set layout
          //declare the border color to be used in each of the jlabels
          Border border = BorderFactory.createLineBorder(Color.BLACK);

          for(int x = 0; x < row; x++)
          {
            for(int y = 0; y < column; y++)
            {
              temp = new JButton(); //creates new JLabel

              /*if(char_grid[x][y] == '1'){
                  temp.setBackground(Color.r);
                  temp.setOpaque(true);
              }*/
              if(char_grid[x][y] == '2'){
                temp.setBackground(Color.gray);
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
          setVisible(true);
        }

        public static void main(String[] args) {
                PuzzleGenerator puzzleGen = new PuzzleGenerator(120,160);//makes new ButtonGrid with 2 parameters
                puzzleGen.generateMap();
                puzzleGen.showMap();
        }
}
//reference: https://www.wikihow.com/Make-a-GUI-Grid-in-Java
