package main;

import controllers.MapController;
import generator.MapGenerator;
import java.util.ArrayList;
import models.Grid;
import models.MapModel;
import util.Coordinates;
import util.TreeNode;
import views.ButtonGridView;
import views.MapView;

public class RunMVCTest {

    public RunMVCTest(char[][] char_map, ArrayList<Coordinates> start_end_pair) throws Exception{
      System.out.println("RunMVCTest()");
      //The map and variant will be passed to buttonGridView
      ButtonGridView theBGView  = new ButtonGridView(char_map, start_end_pair);
      int[] start = new int[] {start_end_pair.get(0).get_x_coordinate(), start_end_pair.get(0).get_y_coordinate()};
      int[] goal = new int[] {start_end_pair.get(1).get_x_coordinate(), start_end_pair.get(1).get_y_coordinate()};
      System.out.printf("printing grid with start: %d %d \n", start[0],start[1]);
      System.out.printf("printing grid with goal: %d %d \n", goal[0], goal[1]);      
      Grid grid = new Grid(char_map,start,goal);
      TreeNode res = grid.g.uniformCostSearch(start);
      System.out.printf("result = %d, %d \n",res.coord[0], res.coord[1]);
      theBGView.tracePath(res);
      MapView        theMapView = new MapView(theBGView);
    	MapModel theModel = new MapModel();

      //MapController theMapController = new MapController(theMapView,theModel);
      MapController theMapController = new MapController(theBGView, theMapView, theModel);
      //theMapView.setVisible(true);
        
              
        
    }
}
//reference: http://www.newthinktank.com/2013/02/mvc-java-tutorial/
