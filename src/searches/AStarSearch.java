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
        return super.abstractSearch(list, start, goal);
    }
    
    @Override
    public void calcH(TreeNode node, int[]goal){
        node.h = getHeuristic(node,goal);
        System.out.println(node.h);
    }
    @Override
    public void setF(TreeNode node){
        node.f = node.g + node.h;
    }
    public double getHeuristic(TreeNode node, int[]goal){ // h value differences is too low, so A star approach is same as ucs 
        return Math.sqrt(2)*(Math.abs(goal[0] - node.coord[0]) + Math.abs(goal[1] - node.coord[1])) * 0.25;
    }
}

