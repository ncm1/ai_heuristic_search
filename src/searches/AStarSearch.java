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
    
    public TreeNode aStarSearch(TreeNode[][] list, int[] start){
        return super.abstractSearch(list, start);
    }
    
    @Override
    public double calcCost(TreeNode node){
        node.h = getHeuristic(node);
        return node.g + node.h;
    }
    public double getHeuristic(TreeNode node){
        return node.h;
    }
}

