package dietPlanner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	//creates a cardlayout to switch panels
	CardLayout cardLayout = new CardLayout();
	//creates a conatiner to store panels using hte cardlayout
    JPanel container = new JPanel(cardLayout);
    //creates a guicontrol to control the cardlayout using the container
    GuiControl mmc = new GuiControl(container);
	
	//main
	public static void main(String[] args) {
		new Main();

	}

	//performs main, setting up gui frame
	public  Main()
	{
		//makes jframe exit on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1000, 1000));

	    // Add the card layout container to the JFrame.
	    this.add(container, BorderLayout.CENTER);
	    this.setTitle("Diet Tracker");
	    // Show the JFrame.
	    this.pack();
	    this.setVisible(true);
	    
	    //login page to chose profile
	    Login view4=new Login(mmc, container);
		container.add(view4,"4");
		cardLayout.show(container, "4");
		
	    //makes new home object, which serves as the main menu
	    Home view1=new Home(mmc, container);
		container.add(view1,"1");
		
		

		//makes createprofile and adds to container
		CreateProfile view5=new CreateProfile(mmc, container);
				container.add(view5,"5");
				
		//makes container to display table and adds to container
		displayTable view6=new displayTable(mmc, container);
				container.add(view6,"6");	
				
				
				
		
		
		
	}
	
}
