package util;

import java.io.*;
import java.util.ArrayList;

public class MapReader{

  private Boolean success;
  char[][] char_map;
  final static int ROWS = 120;
  final static int COLUMNS = 160;
  Coordinates start_goal;
  Coordinates end_goal;
  ArrayList<Coordinates> start_end_goal_pair = new ArrayList<Coordinates>();

  public MapReader(String fileName){
    // This will reference one line at a time
    String line = null;
    try {
        // FileReader reads text files in the default encoding.
        FileReader fileReader =
            new FileReader(fileName);

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader =
            new BufferedReader(fileReader);

        int count = 0;
        ArrayList<Character> arrayMap = new ArrayList<Character>();
        char[] char_value = new char[1];

        char_map = new char[ROWS][COLUMNS];

        int curr_row = 0;
        int curr_column = 0;
        int counter = 0;
        while((line = bufferedReader.readLine()) != null)
        {
            //Split the line of values into seperate strings of ints
            String[] values = line.split(" ");
            if(counter == 0)
              setStartGoal(values);
            else if(counter == 1)
              setEndGoal(values);

            if(values.length >= 5)
            {
              //For loop for adding each char
              for(int i = 0; i < values.length; i++)
              {
                char_map[curr_row][curr_column] = values[i].charAt(0);
                curr_column++;
              }
              curr_column = 0;
              curr_row++;
            }//accounting for both file formats
            counter++;
        }
        bufferedReader.close();

        start_end_goal_pair.add(start_goal);
        start_end_goal_pair.add(end_goal);
        success = true;
    }
    catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" +
            fileName + "'");
        success = false;
    }
    catch(IOException ex) {
        System.out.println(
            "Error reading file '"
            + fileName + "'");
        success = false;
    }
  }

  public Boolean wasSuccessful(){
    return success;
  }

  public void setStartGoal(String s[]){

    int x = Integer.parseInt(String.valueOf(s[0]));
    int y = Integer.parseInt(String.valueOf(s[1]));
    System.out.println("Start goal - x: " + x + " y: " + y);
    start_goal = new Coordinates(x, y);
  }

  public void setEndGoal(String s[]){
    int x = Integer.parseInt(String.valueOf(s[0]));
    int y = Integer.parseInt(String.valueOf(s[1]));
    System.out.println("End goal - x: " + x + " y: " + y);
    end_goal = new Coordinates(x, y);
  }

  public char[][] get_char_map(){
    return char_map;
  }

  public ArrayList<Coordinates> getStartGoalPair(){
    return start_end_goal_pair;
  }

  public void printCharMap(){
    for(int x = 0; x < ROWS; x++){
      for(int y = 0; y < COLUMNS; y++){
        System.out.print(char_map[x][y] + " ");
      }
      System.out.println("");
    }
  }

  public static void main(String[] args){
    new MapReader("./Maps/PredefinedMaps/Map0/map-0-v0.txt");
  }
}
/*Reference: https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
              https://stackoverflow.com/questions/6881458/converting-a-string-array-into-an-int-array-in-java
 */
