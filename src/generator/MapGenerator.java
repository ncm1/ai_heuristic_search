package generator;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.lang.*;
import javax.swing.border.Border;
import models.Grid;
import util.Coordinates;
import util.TreeNode;
import views.ButtonGridView;
import views.MapView;

public class MapGenerator extends JFrame{

        JButton[][] grid; //names the grid of buttons
        char[][] char_grid;
        ArrayList<Coordinates> coordinateArray = new ArrayList<Coordinates>();
        ArrayList<ArrayList<Coordinates>> startGoal = new ArrayList<ArrayList<Coordinates>>();
        JFrame self;
        JButton temp;
        JPanel pane;

        Random randy = new Random();

        private int row = -1;
        private int column = -1;
        private int rowSize = 0;
        private int columnSize = 0;

        //TODO: Write the generated puzzle to file
        MapGenerator(int rows, int columns){ //constructor
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
          ArrayList<Coordinates> highways = new ArrayList<Coordinates>();

          for(int i = 0; i < 8; i++){
            x_rand = randy.nextInt(row);
            y_rand = randy.nextInt(column);

            coordinateArray.add(new Coordinates(x_rand,y_rand));
            placeHardTraversalCells(x_rand,y_rand);
          }

          //Boolean isHighwayGenerated = false;
          //while(!isHighwayGenerated)
          //TODO: Implement highway method
          int closedListSize = 0;
          while(closedListSize == 0)
          {
            highways = generateHighways();
            closedListSize = highways.size();
          }
          addHighways(highways);
          placeBlockedCells();
          generateStartGoalCells();
        }

        final String UP    = "Up";
        final String DOWN  = "Down";
        final String LEFT  = "Left";
        final String RIGHT = "Right";

        public ArrayList<Coordinates> generateHighways()
        {
          int x_rand, y_rand;
          double bound;

          String move_direction;
          Boolean building = true;
          Coordinates coord;

          ArrayList<ArrayList<Coordinates>> highways = new ArrayList<ArrayList<Coordinates>>();
          ArrayList<Coordinates> closedList          = new ArrayList<Coordinates>();
          ArrayList<Coordinates> tempHighway         = new ArrayList<Coordinates>();

          Boolean isInside, isSelfColliding;

          long tot_attempts = 0;
          long MAX_ITERATIONS = 100000;
          int TOTAL_HIGHWAYS = 4;
          while(tot_attempts < MAX_ITERATIONS)
          {
            for(int i = 0; i < TOTAL_HIGHWAYS; i++)
            {

              //Select the bound to start the highway
              bound = randy.nextDouble();
              if(bound < 0.5)
              {
                x_rand = getRandomBound(0, row);
                y_rand = randy.nextInt(column);
                //Move DOWN
                if(x_rand == 0)
                  move_direction = DOWN;
                //Move UP
                else
                  move_direction = UP;
              }

              else
              {
                y_rand = getRandomBound(0, column);
                x_rand = randy.nextInt(row);
                //Move RIGHT
                if(y_rand == 0)
                  move_direction = RIGHT;
                //Move LEFT
                else
                  move_direction = LEFT;
              }

              tempHighway = startWalking(move_direction,x_rand,y_rand);
              isInside    = isInsideClosedList(closedList,tempHighway);
              isSelfColliding = isSelfColliding(tempHighway);

              while(isInside || isSelfColliding)
              {
                tempHighway = startWalking(move_direction,x_rand,y_rand);
                //Return valid highway and add it to the array list
                isInside        = isInsideClosedList(closedList,tempHighway);
                isSelfColliding = isSelfColliding(tempHighway);

                tot_attempts++;
                if(tot_attempts > MAX_ITERATIONS)
                  break;
              }//end while loop
              if(tot_attempts > MAX_ITERATIONS)
                break;
              isInside = false;
              highways.add(tempHighway);
              closedList = addHighwayToClosedList(closedList, tempHighway);//closedList.add
            }//end for loop
          }//end while loop

          if(highways.size() < TOTAL_HIGHWAYS)
            return new ArrayList<Coordinates>();
          else if(highways.size() > TOTAL_HIGHWAYS)
            return new ArrayList<Coordinates>();

          return closedList;
        }

        public ArrayList<Coordinates> startWalking(String move, int x, int y){
          int bound_x;
          int bound_y;
          ArrayList<Coordinates> highway = new ArrayList<Coordinates>();
          highway.add(new Coordinates(x,y));
          Coordinates newC = getNewCoordinates(move,x,y);
          highway.add(newC);

          Boolean bound_hit = false;

          String newDirection;
          String lastDirection = move;
          double q;
          Coordinates newCoordinate = newC;
          int tempX = newC.get_x_coordinate();
          int tempY = newC.get_y_coordinate();
          while(!bound_hit)
          {
            q = randy.nextDouble();

            if(q < 0.6)
              newDirection = lastDirection;
            else
              newDirection = getPerpindicular(lastDirection);

            newCoordinate  = getNewCoordinates(newDirection,tempX,tempY);
            lastDirection = newDirection;
            tempX = newCoordinate.get_x_coordinate();
            tempY = newCoordinate.get_y_coordinate();

            if(!areCoordinatesInBound(newCoordinate)){
              newCoordinate = adjustCoordinate(newCoordinate);
              tempX = newCoordinate.get_x_coordinate();
              tempY = newCoordinate.get_y_coordinate();
              bound_hit = true;
            }
            highway.add(newCoordinate);
          }
            if(highway.size() >= 6)
              return highway;
            else
              return startWalking(move, x, y);
        }

        public ArrayList<Coordinates> addHighwayToClosedList(ArrayList<Coordinates> closedList, ArrayList<Coordinates> highway){

          int tempMax;
          int tempMin;

          int curr_x_coord = -1;
          int curr_y_coord = -1;

          int next_x_coord = -1;
          int next_y_coord = -1;

          Coordinates currCoordinate;
          Coordinates nextCoordinate;

          int highwaySize = highway.size();

          for(int j = 0; j < highwaySize - 1; j++)
          {
            currCoordinate = highway.get(j);
            nextCoordinate = highway.get(j + 1);

            curr_x_coord = currCoordinate.get_x_coordinate();
            curr_y_coord = currCoordinate.get_y_coordinate();

            next_x_coord = nextCoordinate.get_x_coordinate();
            next_y_coord = nextCoordinate.get_y_coordinate();

            //x is constant, y values change, update accordingly
            if(curr_x_coord == next_x_coord)
            {
              tempMax = getMax(curr_y_coord, next_y_coord);
              tempMin = getMin(curr_y_coord, next_y_coord);

              while(tempMin <= tempMax)
              {
                  closedList.add(new Coordinates(curr_x_coord,tempMin));
                  tempMin++;
              }
            }

            //y is constant, x values change, update accordingly
            else if(curr_y_coord == next_y_coord)
            {
              tempMax = getMax(curr_x_coord, next_x_coord);
              tempMin = getMin(curr_x_coord, next_x_coord);

              while(tempMin <= tempMax)
              {
                  closedList.add(new Coordinates(tempMin,curr_y_coord));
                  tempMin++;
              }
            }
          }
          return closedList;
        }

        public void addHighways(ArrayList<Coordinates> closedList){
          int clSize = closedList.size();

          int x_coord;
          int y_coord;
          for(int i = 0; i < clSize; i++)
          {
            x_coord = closedList.get(i).get_x_coordinate();
            y_coord = closedList.get(i).get_y_coordinate();

            if(char_grid[x_coord][y_coord] == '1')
              char_grid[x_coord][y_coord] = 'a';

            else if(char_grid[x_coord][y_coord] == '2')
              char_grid[x_coord][y_coord] = 'b';
          }
        }

        public Boolean areCoordinatesInBound(Coordinates c){
          if(c.get_x_coordinate() > 0 && c.get_x_coordinate() < row -1 && c.get_y_coordinate() > 0 && c.get_y_coordinate() < column - 1)
            return true;
          else
            return false;
        }

        public Boolean isInsideClosedList(ArrayList<Coordinates> cl, ArrayList<Coordinates> temp)
        {
          int clSize   = cl.size();
          int tempSize = temp.size();

          int cl_x_coord;
          int cl_y_coord;

          int curr_temp_x_coord;
          int curr_temp_y_coord;

          int next_temp_x_coord;
          int next_temp_y_coord;

          int tempMin;
          int tempMax;

          //Closed list
          for(int i = 0; i < tempSize - 1; i++)
          {
            curr_temp_x_coord = temp.get(i).get_x_coordinate();
            curr_temp_y_coord = temp.get(i).get_y_coordinate();

            next_temp_x_coord = temp.get(i + 1).get_x_coordinate();
            next_temp_y_coord = temp.get(i + 1).get_y_coordinate();

            for(int j = 0; j < clSize; j++)
            {
              cl_x_coord = cl.get(j).get_x_coordinate();
              cl_y_coord = cl.get(j).get_y_coordinate();

              //x is constant, y values change, update accordingly
              if(curr_temp_x_coord == next_temp_x_coord)
              {
                tempMax = getMax(curr_temp_y_coord, next_temp_y_coord);
                tempMin = getMin(curr_temp_y_coord, next_temp_y_coord);

                while(tempMin <= tempMax)
                {
                    if(tempMin == cl_y_coord && curr_temp_x_coord == cl_x_coord)
                      return true;
                    tempMin++;
                }
              }

              //y is constant, x values change, update accordingly
              else if(curr_temp_y_coord == next_temp_y_coord)
              {
                tempMax = getMax(curr_temp_x_coord, next_temp_x_coord);
                tempMin = getMin(curr_temp_x_coord, next_temp_x_coord);

                while(tempMin <= tempMax)
                {
                    if(tempMin == cl_x_coord && curr_temp_y_coord == cl_y_coord)
                      return true;
                    tempMin++;
                }
              }

              if(cl_x_coord == curr_temp_x_coord && cl_y_coord == curr_temp_y_coord)
                return true;
            }
          }
          return false;
        }

        //TODO: Implement method to prevent the highway from colliding with itself
        public Boolean isSelfColliding(ArrayList<Coordinates> coordinateList){
          int coordinateListSize = coordinateList.size();

          int curr_temp_x_coord;
          int curr_temp_y_coord;

          int cl_x_coord;
          int cl_y_coord;

          int tempMin;
          int tempMax;

          //Closed list
          for(int i = 0; i < coordinateListSize - 1; i++)
          {
            curr_temp_x_coord = coordinateList.get(i).get_x_coordinate();
            curr_temp_y_coord = coordinateList.get(i).get_y_coordinate();

            for(int j = coordinateListSize - 1; j > i; j--)
            {
              cl_x_coord = coordinateList.get(j).get_x_coordinate();
              cl_y_coord = coordinateList.get(j).get_y_coordinate();

              if(cl_x_coord == curr_temp_x_coord && cl_y_coord == curr_temp_y_coord)
                return true;
            }
          }
          return false;
        }

        public String getPerpindicular(String move){
          String perp = "";
          Double rand;
          rand = randy.nextDouble();
          if(move == UP || move == DOWN){
            if(rand < 0.5)
              perp = RIGHT;
            else
              perp = LEFT;
          }
          else if(move == RIGHT || move == LEFT){
            if(rand < 0.5)
              perp = UP;
            else
              perp = DOWN;
          }
          return perp;
        }

        public Coordinates getNewCoordinates(String move, int x, int y){
          int bound_x = 0;
          int bound_y = 0;
          if(move == UP){
            bound_x = x - 20;
            bound_y = y;
          }
          else if(move == DOWN){
            bound_x = x + 20;
            bound_y = y;
          }
          else if(move == RIGHT){
            bound_x = x;
            bound_y = y + 20;
          }
          else if(move == LEFT){
            bound_x = x;
            bound_y = y - 20;
          }
          Coordinates newCoord = new Coordinates(bound_x,bound_y);
          return newCoord;
        }

        public Coordinates adjustCoordinate(Coordinates c){
                  Coordinates newC = new Coordinates(c.get_x_coordinate(),c.get_y_coordinate());

                  if(c.get_x_coordinate() > row - 1)
                    newC.set_x_coordinate(row - 1);

                  else if(c.get_x_coordinate() < 0)
                    newC.set_x_coordinate(0);

                  if(c.get_y_coordinate() > column - 1)
                    newC.set_y_coordinate(column - 1);

                  else if(c.get_y_coordinate() < 0)
                    newC.set_y_coordinate(0);

                  return newC;
                }

        public int getRandomBound(int min, int max){
          double bound;
          bound = randy.nextDouble();
          if(bound < 0.5)
            return min;
          return max - 1;
        }

        public void placeBlockedCells(){
          double blocked = row * column * 0.2;
          int tot_blocked = (int) blocked;

          int cellsPlaced = 0;
          int x_rand = -1;
          int y_rand = -1;
          int min = 0;
          Boolean placing = true;
          while(cellsPlaced <= tot_blocked)
          {
            placing = true;
            while(placing){
              x_rand = randy.nextInt(row);
              y_rand = randy.nextInt(column);
              if(char_grid[x_rand][y_rand] != '0' && char_grid[x_rand][y_rand] != 'a'
                && char_grid[x_rand][y_rand] != 'b'){
                char_grid[x_rand][y_rand] = '0';
                placing = false;
              }
            }
            cellsPlaced += 1;
          }
        }

        public void placeHardTraversalCells(int x, int y){
          int current_x = x;
          int current_y = y;

          int min_x = min_x(x);
          int max_x = max_x(x);

          int min_y = min_y(y);
          int max_y = max_y(y);

          current_x = min_x;
          current_y = min_y;
          Random rand = new Random();
          double prob_q = 0.0;
          int counter = 1;
          while(current_x < max_x)
          {
            while(current_y < max_y){
              prob_q = rand.nextDouble();

              if(prob_q <= 0.5)
                char_grid[current_x][current_y] = '2';
              current_y += 1;
            }
            current_x += 1;
            current_y = min_y;
            counter   +=1;
          }
        }

        public void generateStartGoalCells()
        {
          int generated = 0;

          Boolean isValid;

          Coordinates temp_start;
          Coordinates temp_goal;
          ArrayList<Coordinates> tempStartGoalPair = new ArrayList<Coordinates>();
          while(generated < 10)
          {

            temp_start = getRandomStartGoalCell();
            temp_goal  = getRandomStartGoalCell();
            isValid = false;
            while(!isValid)
            {
              if(getEuclideanDistance(temp_start, temp_goal) >= 100)
              {
                //Cells should not be on blocked, or highways
                if(char_grid[temp_start.get_x_coordinate()][temp_start.get_y_coordinate()] == 'a' ||
                   char_grid[temp_start.get_x_coordinate()][temp_start.get_y_coordinate()] == 'b' ||
                   char_grid[temp_start.get_x_coordinate()][temp_start.get_y_coordinate()] == '0' ||
                   char_grid[temp_goal.get_x_coordinate()][temp_goal.get_y_coordinate()]   == 'a' ||
                   char_grid[temp_goal.get_x_coordinate()][temp_goal.get_y_coordinate()]   == 'b' ||
                   char_grid[temp_goal.get_x_coordinate()][temp_goal.get_y_coordinate()]   == '0')
                   {
                            isValid = false;
                   }
                else
                {
                  tempStartGoalPair = new ArrayList<Coordinates>();
                  tempStartGoalPair.add(temp_start);
                  tempStartGoalPair.add(temp_goal);
                  startGoal.add(tempStartGoalPair);
                  isValid = true;
                  generated++;
                }
              }

              else
                isValid = false;
              //Try again!
              temp_start = getRandomStartGoalCell();
              temp_goal  = getRandomStartGoalCell();
            }
          }
        }

        public int getEuclideanDistance(Coordinates p, Coordinates q)
        {
          double d = 0.0;
          double sub_x = (double)(q.get_x_coordinate() - p.get_x_coordinate());
          double sub_y = (double)(q.get_y_coordinate() - p.get_y_coordinate());

          double sqr_x = sub_x * sub_x;
          double sqr_y = sub_y * sub_y;
          d = Math.sqrt(sqr_x + sqr_y);

          return (int) d;
        }

        public Coordinates getRandomStartGoalCell(){
          Random rand = new Random();
          double q = randy.nextDouble();

          Coordinates newCoordinate;
          if(q < 0.25)
            newCoordinate = randomTopCell();
          else if(q >= 0.25 && q < 0.5)
            newCoordinate = randomBottomCell();
          else if(q >= 0.5 && q < 0.75)
            newCoordinate = randomLeftCell();
          else
            newCoordinate = randomRightCell();

          return newCoordinate;

        }

        public Coordinates randomTopCell(){
          Random rand = new Random();
          int rand_x = randy.nextInt(20);
          int rand_y = randy.nextInt(column);

          return new Coordinates(rand_x, rand_y);
        }

        public Coordinates randomBottomCell(){
          Random rand = new Random();

          int max = row - 1;
          int min = row - 21;

          int rand_x = randy.nextInt(max - min + 1) + min;
          int rand_y = randy.nextInt(column);

          return new Coordinates(rand_x, rand_y);
        }

        public Coordinates randomLeftCell(){
          Random rand = new Random();
          int rand_x = randy.nextInt(row);
          int rand_y = randy.nextInt(20);

          return new Coordinates(rand_x, rand_y);
        }

        public Coordinates randomRightCell(){
          Random rand = new Random();
          int max = column - 1;
          int min = column - 21;
          int rand_x = randy.nextInt(row);
          int rand_y = randy.nextInt(max - min + 1) + min;

          return new Coordinates(rand_x, rand_y);
        }

        public int max_x(int x){
          int temp = x + 16;
          if(temp > (row - 1))
            return row;
          else
            return temp;
        }

        public int min_x(int x){
          int temp = x - 15;
          if(temp < 0)
            return 0;
          else
            return temp;
        }

        public int max_y(int y){
          int temp = y + 16;
          if(temp > (column - 1))
            return column;
          else
            return temp;
        }

        public int min_y(int y){
          int temp = y - 15;
          if(temp < 0)
            return 0;
          else
            return temp;
        }

        public int getMax(int a, int b){
          if(a < b)
            return b;
          else
            return a;
        }

        public int getMin(int a, int b){
          if(a < b)
            return a;
          else
            return b;
        }

        public void writeGridToFile(String filename){
            PrintWriter mapFile = null;

            for(int k = 0; k < 10; k++)
            {
              try{
                    mapFile = new PrintWriter(new FileWriter(filename + "-v" + k + ".txt"));
                  }catch (IOException i) {
                    // TODO Auto-generated catch block
                    i.printStackTrace();
                  }
              //First two lines provide start, and end goal
              mapFile.write(startGoal.get(k).get(0).get_x_coordinate() + " " + startGoal.get(k).get(0).get_y_coordinate());
              mapFile.write("\n");
              mapFile.write(startGoal.get(k).get(1).get_x_coordinate() + " " + startGoal.get(k).get(1).get_y_coordinate());
              mapFile.write("\n");
              //Next eight lines provide the centers of the hard to traverse regions
              for(int x = 0; x < 8; x++)
                mapFile.write(coordinateArray.get(x).get_x_coordinate() + " " + coordinateArray.get(x).get_y_coordinate() + "\n");

              //Map as a char_grid printed to file
              for(int i = 0; i < row; i++)
              {
                for(int j = 0; j < column; j++)
                {
                  mapFile.write(char_grid[i][j] + " ");
                }
                mapFile.write("\n");
              }
              mapFile.write("\n");
              mapFile.flush();
              mapFile.close();
            }
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
        
        public static void main(String[] args) throws Exception {
              
              MapGenerator puzzleGen = new MapGenerator(120,160);//makes new ButtonGrid with 2 parameters
              puzzleGen.generateMap();
              int[] start = new int[]{puzzleGen.startGoal.get(0).get(0).get_x_coordinate(), puzzleGen.startGoal.get(0).get(0).get_y_coordinate()};
              int[] goal = new int[]{puzzleGen.startGoal.get(0).get(1).get_x_coordinate(), puzzleGen.startGoal.get(0).get(1).get_y_coordinate()};
              
              ButtonGridView bgt = new ButtonGridView(puzzleGen.char_grid,puzzleGen.startGoal.get(0));
              //puzzleGen.showMap(puzzleGen.char_grid,start,goal);
              
              System.out.printf("printing grid with start: %d %d \n", start[0], start[1]);
              System.out.printf("printing grid with goal: %d %d \n", goal[0], goal[1]);
              
              Grid grid = new Grid(puzzleGen.char_grid,start,goal);
              TreeNode res = grid.g.uniformCostSearch(start);
              System.out.printf("result = %d, %d \n",res.coord[0], res.coord[1]);
              
              bgt.tracePath(res);
              MapView mv = new MapView(bgt);
              //puzzleGen.writeGridToFile("./Maps/PredefinedMaps/Map" + 0+"/" + "map-" + 0);
        }
}
//reference: https://www.wikihow.com/Make-a-GUI-Grid-in-Java
