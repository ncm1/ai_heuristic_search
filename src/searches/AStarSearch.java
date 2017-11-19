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
public class AStarSearch extends AbstractSearch{
    
    public TreeNode aStarSearch(TreeNode[][] list, int[] start, int[] goal){
        long s = System.nanoTime();
        TreeNode res = super.abstractSearch(list, start, goal);
        this.elapsedTime = System.nanoTime() - s;;
        setTime(list);
        return res;
    }
    @Override
    public void setFandH(TreeNode node, int[] goal){
        node.h = getHeuristic(node,goal);
        node.f = node.g + node.h;
    }
    public double getHeuristic(TreeNode node, int[]goal){ // h value differences is too low, so A star approach is same as ucs 
        int ydif = Math.abs(goal[0] - node.coord[0]);
        int xdif = Math.abs(goal[1] - node.coord[1]);
        return (xdif + ydif) * 0.25;
    }
}

