package util;

import java.util.ArrayList;

public class Node {
  public ArrayList<Edge> AdjacencyList   = new ArrayList<Edge>();
  public int[] coord;
  double g; // start node's g should be 0
  double h;
  private char type;
  boolean start = false;
  boolean goal = false;
  
  public Node(int r, int c){
      this.coord = new int[]{r,c};
  }
  
  public void setType(char t){
      
      // apply all the rules here;
      this.type = t;
  }
  public char getType(){
      return this.type;
  }
}
