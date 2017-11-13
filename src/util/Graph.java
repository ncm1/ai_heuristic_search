package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
    public static int MAX_ROWS = 120;
    public static int MAX_COLS = 160;
    
    public Node list[][];
    int[][] traceNodes;
    int rows;
    int cols;
/*
    public Graph(int v){
        vertices = v;
        list = new Node[v];
        for(int i = 0; i < v; i++)
            list[i] = new Node();
    }
*/
    
    public Graph(int r, int c){
        this.rows = r;
        this.cols = c;
        list = new Node[r][c];
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                list[i][j] = new Node(i,j);
            }
        }
    }
    
    public void addEdge(int sr,int sc,int dr,int dc,double w){
        list[sr][sc].AdjacencyList.add(new Edge(sr,sc,dr,dc,w));
    }

  public static int[] get2dIndex(int index){
      int[] arr = new int[]{index/MAX_COLS,index%MAX_COLS};
      return arr;
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

public int[][] bfs(int r,int c)
  {
    //2D Auxilary matrix visited for keeping track of whether a vertex has been
    //visited or not
		int visited[][] = new int[this.rows][this.cols];
    //Set the source node as visited
		visited[r][c] = 1;

    //Creating 2 queues for keeping
		ArrayList<Integer[]>  queue      = new ArrayList<Integer[]>();
    ArrayList<TreeNode> nodeQueue = new ArrayList<TreeNode>();

    TreeNode root = new TreeNode(r,c);
    TreeNode currNode = null;
    TreeNode newNode  = null;
    Integer[] s= {r,c};
    queue.add(s);
    nodeQueue.add(root);

		while(!queue.isEmpty())
		{
			Integer[] next = queue.remove(0);
      currNode = nodeQueue.remove(0);
      //System.out.println("visited node: " + next);
			for(int i = 0; i < list[next[0]][next[1]].AdjacencyList.size(); i++)
			{
				Edge e1 = list[next[0]][next[1]].AdjacencyList.get(i);
				if(visited[e1.dest_r][e1.dest_c] != 1)
				{
          //Create a new TreeNode that has not been visited
          newNode = new TreeNode(e1.dest_r,e1.dest_c);
          //Set the new node as being visited and place it in the queue
					visited[e1.dest_r][e1.dest_c] = 1;
          //Adding new node to the queue as well as the TreeNode
                                        Integer[] t = {e1.dest_r,e1.dest_c};
					queue.add(t);
          nodeQueue.add(newNode);
          //Add the new node as the dequed nodes child
          currNode.addChild(newNode);

          //System.out.println("Added node " + e1.destination + " to node " + currNode.getVertex());
				}
			}
		}

    int vertexHasBeenVisited;
    int numberOfMoves;

    List<TreeNode> children = root.getChildren();

    //For loop goes through the visited array and replaces the vertices that
    //have been visited with the number of moves it took to get there
    for(int j = 0; j < r; j++) // changed from 1 to 0 because we're searching all nodes
    {
      for ( int i = 0; i < c; i++){
        vertexHasBeenVisited = visited[j][i];
        if(vertexHasBeenVisited == 1)
        {
          //Find the number of moves to get to that vertex
          int[] t= {i,j};
          numberOfMoves = findDistance(root, t);
          //Set the value in the visited array as the number of moves
          visited[j][i] = numberOfMoves;
        }
      }
    }//end for
    return visited;
  }

public int[] uniformCostSearch(int[] start){
        int r,c ; // end indices
        PriorityQueue<Node> pq = new PriorityQueue<Node>(10,new NodePathCostComparator());
        int[][] explored = new int[MAX_ROWS][MAX_COLS];
        int[] tmp; double pathCost;
        Node parentNode = list[start[0]][start[1]];
        parentNode.g = 0;
        Node edgeNode;
        pq.add(parentNode);
        for (long l = 0; l < 1000000000; ++l){
            System.out.println("-");
            if (pq.isEmpty()) 
                return new int[]{-1,-1};
            parentNode = pq.poll();
            tmp = parentNode.coord;
            explored[tmp[0]][tmp[1]] = 1;
            if (parentNode.goal == true)
                break;
            //System.out.printf("current coord %d %d \n",tmp[0], tmp[1]);
            for (Edge edge: parentNode.AdjacencyList){ // each edge at node
                tmp = edge.getDestination();
                //System.out.printf("potential coord %d %d ||| ",tmp[0], tmp[1]);
                edgeNode = list[tmp[0]][tmp[1]];
                pathCost = edge.weight + parentNode.g;
                //System.out.println("pathcost" + pathCost);
                if (!pq.contains(edgeNode) && explored[tmp[0]][tmp[1]] == 0 && pathCost < 1000){
                    edgeNode.g = pathCost;
                    pq.add(edgeNode);
                }
                if (pq.contains(edgeNode) && edgeNode.g > pathCost)
                    edgeNode.g = pathCost;
            }
        }
        traceNodes = explored;
        return parentNode.coord;
    }

public int traceSearch(int[] start){
    //do a simple search along the nodes in the trace
    Node tempNode = list[start[0]][start[1]];
    int[] tmp;
    
    for (Edge edge: tempNode.AdjacencyList){
        tmp = edge.getDestination();
        if (traceNodes[tmp[0]][tmp[1]] == 1){
            tempNode = list[tmp[0]][tmp[1]];
        }
    }
    return 0;
}

}
