package controllers;

import models.MapModel;
import views.ButtonGridView;
import views.MapView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
// The Controller coordinates interactions
// between the View and Model

public class MapController {

	private MapView theMapView;
	private ButtonGridView theBGView;
	private MapModel theModel;

	 public MapController(ButtonGridView theBGView, MapView theMapView, MapModel theModel) {

			this.theMapView = theMapView;
			this.theBGView  = theBGView;
			this.theModel   = theModel;

			// Tell the View that when ever the calculate button
			// is clicked to execute the actionPerformed method
			// in the CalculateListener inner class
			this.theBGView.addCellListener(new CellListener());

	}

  public void updateModel(int row, int column){
  		theModel.updateCoordinates(row, column);
      theMapView.setColumnLabel(column);
      theMapView.setRowLabel(row);
      theMapView.set_f_Label(theModel.get_f_value());
      theMapView.set_g_label(theModel.get_g_value());
      theMapView.set_h_label(theModel.get_h_value());
      theMapView.set_w_label(theModel.get_w_value());
      theMapView.set_time_label(theModel.get_time_value());
  }

	class CellListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			JButton[][] grid = theBGView.getGrid();
			final int rowSize = 120;
			final int columnSize = 160;
			int row = -1;
			int column = -1;

			Object source = e.getSource();

			for (int i = 0; i < rowSize; i++) {
				for (int j = 0; j < columnSize; j++) {
					if (source == (grid[i][j])){
						row = i; column = j;

						if(i == rowSize -1)
							theBGView.closeView();
					}
				}
			}



			// Surround interactions with the view with
			// a try block in case numbers weren't
			// properly entered

			try{
				//TODO: Implement MapModel methods to find values of f,h,g
				updateModel(row,column);
				//theMapView.set_time_label();
			}
			catch(NumberFormatException ex){
				System.out.println(ex);
			}
		}
	}
}

//Reference: http://www.newthinktank.com/2013/02/mvc-java-tutorial/
