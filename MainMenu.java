import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MainMenu extends JFrame implements ActionListener {

  JComboBox<String> mapSelection = new JComboBox<String>();
  JButton confirm;
  MapSelection ms;

  public MainMenu() {

    super("Main Menu - Map Selection");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel pane = new JPanel(new GridLayout(3, 1, 8, 8));

    //Setting the Title and location
    JLabel title = new JLabel("Main Menu - Map Selection", SwingConstants.CENTER);
    title.setVerticalAlignment(SwingConstants.CENTER);
    Font font = new Font("Cambria", Font.BOLD, 30);
    title.setFont(font);

    JPanel parentPanel1 = new JPanel(new GridLayout(0,3));
    //Setting the default text to display
    mapSelection.setPrototypeDisplayValue("Choose a type of map");
    //Populate the combobox with the possible options
    mapSelection.addItem("Load predefined maps");
    mapSelection.addItem("Load a user generated map");
    ((JLabel)mapSelection.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    ((JLabel)mapSelection.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

    // Null labels for null in gridlayout
    JLabel nullLabel1 = new JLabel("");
    JLabel nullLabel2 = new JLabel("");
    //nullLayoutPanel.setLayout(null);
    parentPanel1.add(nullLabel1);
    parentPanel1.add(mapSelection);
    parentPanel1.add(nullLabel2);
    //Add confirm icon as a button on the gui
    ImageIcon confirm_Icon  = new ImageIcon("icons/confirm.png");
    confirm = new JButton(confirm_Icon);

    confirm.setOpaque(false);
    confirm.setContentAreaFilled(false);
    confirm.setBorderPainted(false);
    confirm.addActionListener(this);

    JPanel parentPanel2 = new JPanel(new GridLayout(0,3));
    nullLabel1 = new JLabel("");
    nullLabel2 = new JLabel("");
    parentPanel2.add(nullLabel1);
    parentPanel2.add(nullLabel2);
    parentPanel2.add(confirm);
    //Add the title, mapSelection, and confirm icon to the interface
    pane.add(title);
    pane.add(parentPanel1);
    pane.add(parentPanel2);

    getContentPane().add(pane);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(true);
}

  //*******************   Action Listener     **********************
  @Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

    if(source == confirm){
      	String selected = (String)mapSelection.getSelectedItem();
        System.out.println(selected);
        setVisible(false);
        //TabbedPane tp = new TabbedPane();
        onSelected(selected);
    }
  }

  public void onSelected(String s){
    if(s == "Load predefined maps" )
      ms = new MapSelection();
    //else if(s == "Load a user generated map")
      //UserGeneratedMapSelection ugms = new UserGeneratedMapSelection();
  }

public static void main(String[] args){
  //Call the MainMenu to initialize program
  MainMenu menu = new MainMenu();
}

} //end class MainMenu
