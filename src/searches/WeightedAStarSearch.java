/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searches;

import util.TreeNode;

/**
 *
 * @author seobo
 */
public class WeightedAStarSearch extends AbstractSearch{
    private double w;
    
    public TreeNode weightedAStarSearch(TreeNode[][] list, int[] start,  int[] goal, double w){
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
        node.h = getHeuristic(node,goal);
        node.f = node.g + node.w * node.h;
    }
    
    public double getHeuristic(TreeNode node, int[]goal){ // h value differences is too low, so A star approach is same as ucs 
        int ydif = Math.abs(goal[0] - node.coord[0]);
        int xdif = Math.abs(goal[1] - node.coord[1]);
        return ((Math.max(xdif, ydif) - Math.min(xdif, ydif))+ Math.sqrt(2)*(Math.min(xdif, ydif))) * 0.25;
    }
    
}
