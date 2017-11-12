package util;

import java.util.ArrayList;

public class Node {
  public ArrayList<Edge> AdjacencyList   = new ArrayList<Edge>();
  private char type;
  public void setType(char t){
      
      // apply all the rules here;
      this.type = t;
  }
  public char getType(){
      return this.type;
  }
}
