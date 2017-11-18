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
public class UniformCostSearch extends AbstractSearch{
    public TreeNode uniformCostSearch(TreeNode[][] list, int[] start){
        return super.abstractSearch(list, start);
    }
    
    @Override
    public double calcCost(TreeNode node){
        return node.g;
    }
    
}
