package main;

import controllers.MapController;
import java.util.ArrayList;
import models.Grid;
import models.MapModel;
import searches.*;
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
      MapModel theModel = new MapModel(new Grid(char_map,start,goal));
      //UniformCostSearch ucs = new UniformCostSearch();
      //TreeNode res = ucs.uniformCostSearch(grid.g.list,start,goal);
      AStarSearch ass = new AStarSearch();
      TreeNode res = ass.aStarSearch(theModel.grid.g.list, start, goal);
      System.out.printf("result = %d, %d \n",res.coord[0], res.coord[1]);
      theBGView.tracePath(res);
      MapView        theMapView = new MapView(theBGView);
    	

      //MapController theMapController = new MapController(theMapView,theModel);
      MapController theMapController = new MapController(theBGView, theMapView, theModel);
      //theMapView.setVisible(true);
        
              
        
    }
}
//reference: http://www.newthinktank.com/2013/02/mvc-java-tutorial/
