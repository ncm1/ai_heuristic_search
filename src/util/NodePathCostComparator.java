/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Comparator;

/**
 *
 * @author seobo
 */
public class NodePathCostComparator implements Comparator<TreeNode>{
    double ep = 0.00001;
    public int compare(TreeNode a, TreeNode b){
        if (equals(a,b))
            return 0;
        else if ((a.getCost() - b.getCost()) > ep){
            return 1;
        }
        else if ((a.getCost() - b.getCost()) < -ep){
            return -1;
        }
        else return 0;
    }
    public boolean equals(TreeNode a, TreeNode b){
        if (a.getCost()*(1 + ep)>  b.getCost() || a.getCost()*(1-ep) < b.getCost())
            return false;
        else return true;
    }
}