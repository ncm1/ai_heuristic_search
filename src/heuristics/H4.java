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
public class H4 extends AbstractHeuristic{
    public double getHeuristic(TreeNode node, int[]goal){ // h value differences is too low, so A star approach is same as ucs
      int ydif = Math.abs(goal[0] - node.coord[0]);
      int xdif = Math.abs(goal[1] - node.coord[1]);

      double xdif_sqr = xdif * xdif;
      double ydif_sqr = ydif * ydif;
      //Manhattan distance - admissible since set to the lowest distance
      return (xdif + ydif) / 4;
    }
}
//http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
