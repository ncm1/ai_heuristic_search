/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searches;

import java.util.PriorityQueue;
import util.Edge;
import util.NodePathCostComparator;
import util.TreeNode;
import static util.Graph.MAX_COLS;
import static util.Graph.MAX_ROWS;
import models.Grid;

/**
 *
 * @author seo
 */
abstract public class AbstractSearch {
    long elapsedTime;
    
   public TreeNode abstractSearch(TreeNode[][]list, int[] start, int[] goal){
        PriorityQueue<TreeNode> fringe = new PriorityQueue<>(10,new NodePathCostComparator());
        int[][] explored = new int[MAX_ROWS][MAX_COLS];
        TreeNode tmp, edgeNode; double pathCost;
        
        TreeNode parentNode = list[start[0]][start[1]];
        parentNode.parent = null;
        parentNode.g = 0; 
        setFandH(parentNode, goal);
        fringe.add(parentNode);
        while(!fringe.isEmpty()){
            parentNode = fringe.poll();
            if (parentNode.goal == true) //System.out.printf("current coord %d %d \n",tmp[0], tmp[1]);
                return parentNode;
            explored[parentNode.coord[0]][parentNode.coord[1]] = 1;
            for (Edge edge: parentNode.AdjacencyList){ // each edge at node
                tmp = edge.getDest(); //System.out.printf("potential coord %d %d ||| ",tmp[0], tmp[1]);
                edgeNode = list[tmp.coord[0]][tmp.coord[1]];
                if (edgeNode == parentNode || edgeNode.g > 1000)
                    continue;
                pathCost = parentNode.g + edge.weight;
                //System.out.println("pathcost" + pathCost);
                if (explored[tmp.coord[0]][tmp.coord[1]] == 0){
                    if (!fringe.contains(edgeNode)){ 
                        edgeNode.g = 1001;
                        edgeNode.parent = null;
                    } // by default
                    if (pathCost < edgeNode.g){
                        edgeNode.g = pathCost;
                        setFandH(edgeNode, goal);
                        edgeNode.parent = parentNode;
                        if (fringe.contains(edgeNode))
                            fringe.remove(edgeNode); // this is done to postiion edgenode according to it's new g value . Should not edit node's value directly
                        fringe.add(edgeNode);
                    }
                }
                
                
            }
        }
        return null;
    } 
   abstract public void setFandH(TreeNode node, int[] goal);
   
   public void setTime(TreeNode[][] list){
       for (int i = 0; i < MAX_ROWS; ++i){
           for (int j =0; j < MAX_COLS; ++j){
               list[i][j].elapsedTime = elapsedTime;
           }
       }
   }
}
