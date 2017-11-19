/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searches;

import heuristics.AbstractHeuristic;
import heuristics.H1;
import heuristics.H2;
import heuristics.H3;
import heuristics.H4;
import heuristics.H5;
import util.TreeNode;

/**
 *
 * @author seobo
 */
public class WeightedAStarSearch extends AbstractSearch{
    private double w;
    
    public TreeNode weightedAStarSearch(TreeNode[][] list, int[] start,  int[] goal, double w, String heur){
        chooseHeuristic(heur);
        this.w = w;
        long s = System.nanoTime();
        TreeNode res =  super.abstractSearch(list, start, goal);
        this.elapsedTime = System.nanoTime() - s;
        setTime(list);
        return res;
    }
    @Override
    public void setFandH(TreeNode node, int[] goal){
        node.w = this.w;
        node.h = h.getHeuristic(node,goal);
        node.f = node.g + node.w * node.h;
    }
    
}
