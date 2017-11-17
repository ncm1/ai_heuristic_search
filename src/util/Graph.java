package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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

public TreeNode uniformCostSearch(int[] start){
        int r,c ; // end indices
        PriorityQueue<TreeNode> pq = new PriorityQueue<>(10,new NodePathCostComparator());
        int[][] explored = new int[MAX_ROWS][MAX_COLS];
        TreeNode tmp, edgeNode; double pathCost;
        TreeNode parentNode = list[start[0]][start[1]];
        parentNode.g = 0;
        pq.add(parentNode);
        for (long l = 0; l < 1000000000; ++l){
            System.out.println("-");
            if (pq.isEmpty()) 
                return null;
            parentNode = pq.poll();
            explored[parentNode.coord[0]][parentNode.coord[1]] = 1;
            if (parentNode.goal == true) //System.out.printf("current coord %d %d \n",tmp[0], tmp[1]);
                break;
            for (Edge edge: parentNode.AdjacencyList){ // each edge at node
                tmp = edge.getDest();
                //System.out.printf("potential coord %d %d ||| ",tmp[0], tmp[1]);
                edgeNode = list[tmp.coord[0]][tmp.coord[1]];
                pathCost = edge.weight + parentNode.g;
                //System.out.println("pathcost" + pathCost);
                if (!pq.contains(edgeNode) && explored[tmp.coord[0]][tmp.coord[1]] == 0 && pathCost < 1000){
                    edgeNode.g = pathCost;
                    pq.add(edgeNode);
                }
                if (pq.contains(edgeNode) && edgeNode.g > pathCost)
                    edgeNode.g = pathCost;
            }
        }
        return parentNode;
    }

}
