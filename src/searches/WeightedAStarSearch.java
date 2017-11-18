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
    public TreeNode weightedAStarSearch(TreeNode[][] list, int[] start,  int[] goal){
        return super.abstractSearch(list, start, goal);
    }
    
    @Override
    public void setF(TreeNode node){
        node.f = node.g + node.h;
    }
    
    @Override
    public void calcH(TreeNode node, int[]goal){
        //node.h = getHeuristic(node);
    }
}
