/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searches;

import heuristics.AbstractHeuristic;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import util.Edge;
import static util.Graph.MAX_COLS;
import static util.Graph.MAX_ROWS;
import util.NodePathCostComparator;
import util.TreeNode;

/**
 *
 * @author seobo
 */
public class SequentialAStarSearch extends AbstractSearch{
    public double w1;
    
    
        
    public TreeNode sequentialAStarSearch(TreeNode[][] list, int[] start, int[] goal, double w1, double w2){
        TreeNode res;
        long s = System.nanoTime();
        res = search(list,start,goal,w1,w2);
        this.elapsedTime = System.nanoTime() - s;
        setTime(list);
        return res;
    }
    
    public TreeNode search(TreeNode[][] list, int[] start, int[] goal, double w1, double w2){
        this.w1 = w1;
        chooseHeuristic(String.valueOf(0));
        int numH = h.numH;
        
        
        List<PriorityQueue<TreeNode>> fringes = new ArrayList<>();
        List<int[][]> closed= new ArrayList<>(); //closed = explored
        List<Double[][]> g = new ArrayList<>();
        List<TreeNode[][]> bp = new ArrayList<>();
        for ( int i = 0; i < numH; ++i){
            fringes.add(new PriorityQueue<TreeNode>(1000,new NodePathCostComparator()));
            closed.add(new int[MAX_ROWS][MAX_COLS]);
            for( int j = 0; j < MAX_ROWS; j++){
                for(int k = 0; k < MAX_COLS; k++){
                    closed.get(i)[j][j] = 0;
                }
            }
            g.add(new Double[MAX_ROWS][MAX_COLS]);
            g.get(i)[start[0]][start[1]] = 0.0;
            g.get(i)[goal[0]][goal[1]] = 1000.0;
            bp.add(i,new TreeNode[MAX_ROWS][MAX_COLS]);
            bp.get(i)[start[0]][start[1]] = null;
            bp.get(i)[goal[0]][goal[1]] = null;
            fringes.get(i).add(list[start[0]][start[1]]);
            this.exploredCount++;
        }
        TreeNode s;
        while (fringes.get(0).peek().f < 1000){
            for (int i = 1; i < numH; ++i){
                TreeNode open0 = fringes.get(0).peek();
                TreeNode openi = fringes.get(i).peek();
                if (openi.f <= w2 * open0.f){
                    if (g.get(i)[goal[0]][goal[1]] <= openi.f){
                        if (g.get(i)[goal[0]][goal[1]] < 1000)
                            updateParents(list,bp.get(i));
                            return list[goal[0]][goal[1]]; // return goal
                    }
                    else{
                        s = fringes.get(i).poll();
                        expandState(s,fringes.get(i),closed.get(i),list,goal,g.get(i), bp.get(i), i);
                        closed.get(i)[s.coord[0]][s.coord[1]] = 1;
                        this.exploredCount++;
                    }    
                }
                else{
                    if (g.get(0)[goal[0]][goal[1]] <= open0.f){
                        if (g.get(0)[goal[0]][goal[1]] < 1000){
                            updateParents(list,bp.get(0));
                            return list[goal[0]][goal[1]];
                        }
                    }
                    else{
                        s = fringes.get(0).poll();
                        expandState(s,fringes.get(0),closed.get(0),list,goal,g.get(0), bp.get(0), 0);
                        closed.get(0)[s.coord[0]][s.coord[1]] = 1;
                        this.exploredCount++;
                    }
                }
            }
        }
        
        return null;//no solution
    }
    
    public void expandState(TreeNode parentNode, PriorityQueue<TreeNode> fringe, int[][] explored, TreeNode[][] list, int[] goal, Double[][] g, TreeNode[][] bp, int i){
        TreeNode edgeNode;
        TreeNode tmp;
        double pathCost;
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
                        g[tmp.coord[0]][tmp.coord[1]] = edgeNode.g;
                        edgeNode.parent = null;
                        bp[tmp.coord[0]][tmp.coord[1]] = edgeNode.parent;
                    } // by default
                    if (pathCost < edgeNode.g){
                        edgeNode.g = pathCost;
                        g[tmp.coord[0]][tmp.coord[1]] = edgeNode.g;
                        chooseHeuristic(String.valueOf(i));
                        setFandH(edgeNode, goal);
                        edgeNode.parent = parentNode;
                        bp[tmp.coord[0]][tmp.coord[1]] = edgeNode.parent;
                        if (fringe.contains(edgeNode))
                            fringe.remove(edgeNode); // this is done to postiion edgenode according to it's new g value . Should not edit node's value directly
                        fringe.add(edgeNode);
                    }
                }
                
                
            }
    }
    
    public void updateParents(TreeNode[][] list,TreeNode[][] bp){
        for ( int j = 0; j < MAX_ROWS; ++j){
            for ( int k = 0; k < MAX_COLS; ++k){
                list[j][k].parent = bp[j][k];
            }
        }
        
    }
    
    public void setFandH(TreeNode node, int[] goal){
        node.h = h.getHeuristic(node,goal);
        node.f = node.g + this.w1 * node.h;
    }
}
