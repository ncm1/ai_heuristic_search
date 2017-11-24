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
public class H3 extends AbstractHeuristic{
    public double getHeuristic(TreeNode node, int[]goal){ // h value differences is too low, so A star approach is same as ucs
        int ydif = Math.abs(goal[0] - node.coord[0]);
        int xdif = Math.abs(goal[1] - node.coord[1]);

        double ydif_sqr = (double) ydif * ydif;
        double xdif_sqr = (double) xdif * xdif;

        //Diagonal Distance
        /*
            return D * (dx + dy) + (D2 - 2 * D) * min(dx, dy)
            Same as the  Chebyshev distance
        */
        return 0.25 * (xdif + ydif) + (0.25 - 2 * 0.25) * Math.min(xdif, ydif);
    }
}

/*
If h(n) is sometimes greater than the cost of moving from n to the goal,
then A* is not guaranteed to find a shortest path, but it can run faster.

The Manhattan shows a good example of this since the solution is suboptimal, although
found faster relative to the other heuristics
*/
