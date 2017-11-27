package main;

import controllers.MapController;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static javax.script.ScriptEngine.FILENAME;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import models.Grid;
import models.MapModel;
import searches.*;
import util.Coordinates;
import util.TreeNode;
import views.ButtonGridView;
import static views.MapSelection.a;
import static views.MapSelection.sa;
import static views.MapSelection.ucs;
import static views.MapSelection.wa;
import views.MapView;

public class RunMVCTestExperimental {

    public RunMVCTestExperimental(char[][] char_map, ArrayList<Coordinates> start_end_pair, String selectedSearch, String selectedH, double weight, double w2) throws Exception{
      //The map and variant will be passed to buttonGridView
      //ButtonGridView theBGView  = new ButtonGridView(char_map, start_end_pair);
      int[] start = new int[] {start_end_pair.get(0).get_x_coordinate(), start_end_pair.get(0).get_y_coordinate()};
      int[] goal = new int[] {start_end_pair.get(1).get_x_coordinate(), start_end_pair.get(1).get_y_coordinate()};

      MapModel theModel = new MapModel(new Grid(char_map,start,goal));
      TreeNode res;


      String FILENAME = "./experiments/" + selectedSearch;

      if(!selectedSearch.equals(ucs))
          FILENAME += selectedH;

      PrintStream output = new PrintStream(new FileOutputStream(FILENAME + ".txt", true));

      //for(int i = 0; i < 5; i++)
      if (selectedSearch.equals(ucs)){
        UniformCostSearch ucs = new UniformCostSearch();
        res = ucs.uniformCostSearch(theModel.grid.g.list,start,goal);
        theModel.updateCoordinates(goal[0],goal[1]);

        output.println(ucs.getExploredCount());
        output.println(getPathSize(res) + "");
        output.println(String.format("%.4f",theModel.get_f_value()));
        output.println(String.format("%.3f", (double)theModel.get_time_value() / Math.pow(10, 6)));
      }

      else if (selectedSearch.equals(a)){
        AStarSearch ass = new AStarSearch();
        res = ass.aStarSearch(theModel.grid.g.list, start, goal,selectedH);

        theModel.updateCoordinates(goal[0],goal[1]);
        output.println(ass.getExploredCount());
        output.println(getPathSize(res) + "");
        output.println(String.format("%.4f",theModel.get_f_value()));
        output.println(String.format("%.3f", (double)theModel.get_time_value() / Math.pow(10, 6)));
      }
      
      else if (selectedSearch.equals(wa))
      {
        WeightedAStarSearch wass = new WeightedAStarSearch();
        res = wass.weightedAStarSearch(theModel.grid.g.list, start, goal, weight,selectedH);
        
        theModel.updateCoordinates(goal[0],goal[1]);
        output.println(wass.getExploredCount());
        output.println(getPathSize(res) + "");
        output.println(String.format("%.4f",theModel.get_f_value()));
        output.println(String.format("%.3f", (double)theModel.get_time_value() / Math.pow(10, 6)));
      }
      
      /*
      else if (selectedSearch.equals(sa)){
          SequentialAStarSearch sass = new SequentialAStarSearch();
          res =  sass.sequentialAStarSearch(theModel.grid.g.list, start, goal, weight, w2);
      }
      else res = null;
      if (res == null) throw new Exception("goal not found");

      output.printf("result = %d, %d \n",res.coord[0], res.coord[1]);
      theBGView.tracePath(res);

      MapView        theMapView = new MapView(theBGView);


      //MapController theMapController = new MapController(theMapView,theModel);
      MapController theMapController = new MapController(theBGView, theMapView, theModel);
      //theMapView.setVisible(true);
      */
      output.flush();
      output.close();
      
}
 
    public int getPathSize(TreeNode treeNode){
        ArrayList<int[]> path = new ArrayList<>();
        TreeNode tmp = treeNode.parent; // okay because start and goal at least 10 apart
        while (tmp.parent != null){
            path.add(tmp.coord);
             tmp = tmp.parent;
            //System.out.println(tmp.f);
        }
           // for each coordinate, color the box red
            return path.size();
    }
}
    
//reference: http://www.newthinktank.com/2013/02/mvc-java-tutorial/
