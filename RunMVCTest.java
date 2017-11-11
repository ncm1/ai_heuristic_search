public class RunMVCTest {

    RunMVCTest(String map, String variant){
      System.out.println("RunMVCTest()");
      System.out.println(map + "-" + variant);
      //The map and variant will be passed to buttonGridView
      ButtonGridView theBGView  = new ButtonGridView(120,160);
      MapView        theMapView = new MapView(theBGView);

    	MapModel theModel = new MapModel();

      //MapController theMapController = new MapController(theMapView,theModel);
      MapController theMapController = new MapController(theBGView, theMapView, theModel);
      //theMapView.setVisible(true);

    }
    public static void main(String[] args) {
      RunMVCTest runmvc = new RunMVCTest("blank", "blank");
    }
}
//reference: http://www.newthinktank.com/2013/02/mvc-java-tutorial/
