/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristics;

import util.TreeNode;

/**
 *
 * @author seobo
 */
public class H2 extends AbstractHeuristic{
    public double getHeuristic(TreeNode node, int[]goal){ // h value differences is too low, so A star approach is same as ucs 
        int ydif = Math.abs(goal[0] - node.coord[0]);
        int xdif = Math.abs(goal[1] - node.coord[1]);
        if (node.type == 'a' || node.type == 'b')
            return 0.38 * (ydif + xdif);
        if (node.type == '2')
            return 1.5 * (ydif + xdif);
        else //(node.type == '1')
            return Math.min(xdif, ydif) + Math.sqrt(2)*Math.max(xdif,ydif);
    }
}
