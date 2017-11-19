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
abstract public class AbstractHeuristic {
    public int numH = 5;
    
    abstract public double getHeuristic(TreeNode node, int[]goal);
}
