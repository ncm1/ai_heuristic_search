package util;

import java.util.Arrays;
import java.util.List;

public class Graph {
    public static int MAX_ROWS = 120;
    public static int MAX_COLS = 160;
    
    public TreeNode list[][];
    
    public Graph(int r, int c){
        MAX_ROWS = r;
        MAX_COLS = c;
        list = new TreeNode[r][c];
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                list[i][j] = new TreeNode(i,j);
            }
        }
    }
    
    public void addEdge(TreeNode src, TreeNode dest, double w){
        list[src.coord[0]][src.coord[1]].AdjacencyList.add(new Edge(src, dest,w));
        dest.parent = src;
    }
  
  public int findDistance(TreeNode root, int[] goalVertex)
  {
   if(root.getChildren() == null)
     return -1;

   int distance = -1;

   int[] rootVert = root.getVertex();
   List<TreeNode> childrenList = root.getChildren();
   int numOfChildren = childrenList.size();

   if(Arrays.equals(rootVert,goalVertex))
   {
     return distance + 1;
   }

   TreeNode currNode;
   for(int i = 0; i < numOfChildren; i++){
     currNode = childrenList.get(i);
     if( (distance = findDistance(currNode, goalVertex)) >= 0)
       return distance + 1;
   }
   return distance;
  }
}
