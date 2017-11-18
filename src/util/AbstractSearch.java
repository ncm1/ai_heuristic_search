/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.PriorityQueue;
import static util.Graph.MAX_COLS;
import static util.Graph.MAX_ROWS;

/**
 *
 * @author seo
 */
abstract public class AbstractSearch {
   public TreeNode abstractSearch(TreeNode[][]list, int[] start){
        int r,c ; // end indices
        PriorityQueue<TreeNode> pq = new PriorityQueue<>(10,new NodePathCostComparator());
        int[][] explored = new int[MAX_ROWS][MAX_COLS];
        TreeNode tmp, edgeNode; double pathCost;
        TreeNode parentNode = list[start[0]][start[1]];
        parentNode.parent = null;
        parentNode.setCost(0);
        pq.add(parentNode);
        for (long l = 0; l < 1000000000; ++l){
            //System.out.println("-");
            if (pq.isEmpty()) 
                return null;
            parentNode = pq.poll();
            explored[parentNode.coord[0]][parentNode.coord[1]] = 1;
            if (parentNode.goal == true) //System.out.printf("current coord %d %d \n",tmp[0], tmp[1]);
                break;
            for (Edge edge: parentNode.AdjacencyList){ // each edge at node
                tmp = edge.getDest(); //System.out.printf("potential coord %d %d ||| ",tmp[0], tmp[1]);
                edgeNode = list[tmp.coord[0]][tmp.coord[1]];
                pathCost = edge.weight + parentNode.getCost();
                if (edgeNode == parentNode)
                    continue;
                //System.out.println("pathcost" + pathCost);
                if (!pq.contains(edgeNode) && explored[tmp.coord[0]][tmp.coord[1]] == 0 && pathCost < 1000){
                    edgeNode.setCost(pathCost);
                    edgeNode.parent = parentNode;
                    pq.add(edgeNode);
                }
                if (pq.contains(edgeNode) && edgeNode.g > pathCost)
                    edgeNode.parent = parentNode;
                    edgeNode.setCost(pathCost);
            }
        }
        return parentNode;
    } 
   abstract public double getCost(TreeNode node);
   public void setCost(TreeNode node, double cost){
       //node.setCost(cost);
   }
}
