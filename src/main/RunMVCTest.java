
import java.util.ArrayList;

public class RunMVCTest {

    RunMVCTest(char[][] char_map, ArrayList<Coordinates> start_end_pair){
      System.out.println("RunMVCTest()");
      //The map and variant will be passed to buttonGridView
      ButtonGridView theBGView  = new ButtonGridView(char_map, start_end_pair);
      MapView        theMapView = new MapView(theBGView);

    	MapModel theModel = new MapModel();

      //MapController theMapController = new MapController(theMapView,theModel);
      MapController theMapController = new MapController(theBGView, theMapView, theModel);
      //theMapView.setVisible(true);

    }
}
//reference: http://www.newthinktank.com/2013/02/mvc-java-tutorial/
