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
        else if ((a.f - b.f) > ep){
            return 1;
        }
        else if ((a.f - b.f) < -ep){
            return -1;
        }
        else return 0;
    }
    public boolean equals(TreeNode a, TreeNode b){
        if (a.f*(1 + ep)>  b.f || a.f*(1-ep) < b.f)
            return false;
        else return true;
    }
}