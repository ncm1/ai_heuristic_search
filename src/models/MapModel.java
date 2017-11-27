package models;
import models.Grid;

// The Model performs all the calculations needed
// and that is it. It doesn't know the View
// exists

public class MapModel {

	// Holds the value of the sum of the numbers
	// entered in the view

    private double fValue;
    private double gValue;
    private double hValue;
    private double wValue;
    private long time;
    public Grid grid;


    public MapModel(Grid grid){
        this.grid = grid;
    }
    public void updateCoordinates(int r,int c){
        gValue = grid.g.list[r][c].g;
        fValue = grid.g.list[r][c].f;
        hValue = grid.g.list[r][c].h;
        wValue = grid.g.list[r][c].w;
        time   = grid.g.list[r][c].elapsedTime;
    }
    public double get_f_value(){
        return fValue;
    }
    public double get_h_value(){
        return hValue;
    }
    public double get_g_value(){
        return gValue;
    }
    public double get_w_value(){
        return wValue;
    }
    public long get_time_value(){
        return time;
    }

}
