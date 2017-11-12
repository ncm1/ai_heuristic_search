/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import util.Edge;
import util.Graph;
import util.Node;
/**
 *
 * @author seo
 */
public class Grid {
    
    static int MAX_COLS = 160; // width
    static int MAX_ROWS = 120; // height
    Graph g;
    
    public Grid(char[][] arr) throws Exception{
        /*
        Creates a graph object that represents the grid from a given character array describing
        
        Args:   arr     the character array that represents the type of terrain at each coordinate
        
        Precondition:
            The character array fulfills all the requirements of the terrain mapping.
            All values in the character array have a value. (no coordinate left blank)
        Postcondition:
            All edge weights and types will be filled accordingly.
        */
        Node[][] list = g.list;
        int rmin, rmax, cmin, cmax;
        double w = 0;
        for (int r = 0; r < MAX_ROWS; ++r){
            for (int c = 0; c < MAX_COLS; ++c){
                list[r][c] = new Node();
                list[r][c].setType(arr[r][c]);
                
                rmin = (r-1 < 0) ? 0 : r-1;
                rmax = (r+1 >= MAX_ROWS) ? MAX_ROWS-1 : r+1;
                cmin = (c-1 < 0) ? 0 : c-1;
                cmax = (c+1 >= MAX_COLS) ? MAX_COLS-1 : r+1;
                for (;rmin <= rmax; ++rmin){
                    for(; cmin <= cmax; ++cmin){
                        w = getWeightByTypeAndCoord(r,c,rmin,cmin,arr[r][c],arr[rmin][cmin]);
                        list[r][c].AdjacencyList.add(new Edge(r,c,rmin,cmin,w));
                    }
                }
                
            }
        }
    }
    
    public static double getWeightByTypeAndCoord(int sr, int sc, int dr, int dc, char stype, char dtype) throws Exception{
        if (stype == '0' || dtype == '0') return 0; // blocked type
        double sw;
        double dw;
        boolean sh = false;
        boolean dh = false;
        double hwm = 1; // highway multiplier;
        double dm = 1; // direction multiplier
        switch (stype){
            case '1': sw = 0.5; 
                    break;
            case '2': sw = 1;
                    break;
            case 'a': sw = 0.5; sh = true;
                    break;
            case 'b': sw = 1; sh = true;
                    break;
            default: throw new Exception("you done goofed buddy. Invalid character");
        }
        switch (dtype){
            case '1': dw = 0.5; 
                    break;
            case '2': dw = 1;
                    break;
            case 'a': dw = 0.5; dh = true;
                    break;
            case 'b': dw = 1; dh = true;
                    break;
            default: throw new Exception("you done goofed buddy. Invalid character");
        }
        hwm = (sh && dh) ? 0.25: 1;
        
        dm = (sr == dr || sc == dc) ? 1: Math.sqrt(2); // diagnol
        
        return (sw+dw)*dm*hwm;
    }
    
    public char[][] toCharArray(){
        char[][]res = new char[MAX_ROWS][MAX_COLS];
        for (int r = 0; r < MAX_ROWS; ++r){
            for ( int c = 0; c < MAX_COLS; ++c){
                res[r][c] = g.list[r][c].getType();
            }
        }
        return res;
    }
    
    public void randomizeGrid(){
        
        for (int r = 0; r < MAX_ROWS; ++r){
            for ( int c = 0; c < MAX_COLS; ++c){
                g.list[r][c].setType('0');
            }
        }
        
    }
    
    @Override
    public String toString(){
        String res = "";
        for (int r = 0; r < MAX_ROWS; ++r){
            for (int c = 0; c < MAX_COLS; ++c){
                res = res + g.list[r][c].getType() + " " ;
            }
            res = res + "\n";
        }
        return res;
    }
            
}
