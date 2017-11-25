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
    public TreeNode uniformCostSearch(TreeNode[][] list, int[] start,  int[] goal){
        long s = System.nanoTime();
        TreeNode res = super.abstractSearch(list, start, goal);
        this.elapsedTime = System.nanoTime() - s;
        setTime(list);
        System.out.println("Explored ");
        return res;
    }

    @Override
    public void setFandH(TreeNode node, int[] goal){
        node.f = node.g;
    }

}
