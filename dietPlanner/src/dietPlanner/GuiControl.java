package dietPlanner;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GuiControl implements ActionListener{
	private JPanel container;
	CardLayout cardLayout;
	 String profile;
	 String date;
	 String fileName;
	
	//sets guicontrol when originally called in main
	GuiControl(JPanel container)
	{
		this.container=container;
		cardLayout = (CardLayout)container.getLayout();

	}
	//returns cardlayout
	CardLayout getCardLayout()
	{
		return this.cardLayout;
	}
	//returns container
	JPanel getContainer()
	{
		return container;
	}
	//sets the profile the user selects in login
	void setSelectedPorfile(String profile)
	{
		this.profile=profile;
	}
	//returns the selected profile selected in login
	String getSelectedProfile()
	{
		return profile;
	}
	//sets the date to modify or look at.
	void setDate(String date)
	{
		this.date=date;
	}
	//returns the date chosen to look at
	String getDate()
	{
		return date;
	}
	//sets the selected file
	void setSelectedFile(String fileName)
	{
		this.fileName=fileName;
	}
	//returns selected file
	String getSelectedFile()
	{
		return fileName;
	}
	//used to change visible panel
	public void actionPerformed(ActionEvent ae) {
	    String command = ae.getActionCommand();
	    
	    //goes to home menu panel
	    if (command.contentEquals("Go Back"))
		{
			cardLayout.show(container, "1");
		}
		else if (command.contentEquals("Save"))
		{
			cardLayout.show(container, "1");
		}
		else if (command.contentEquals("Go Back"))
		{
			cardLayout.show(container, "1");
		}
		else if (command.contentEquals("cancel"))
		{
			cardLayout.show(container, "1");

		}
	    //goes to view past records panel
		else if (command.contentEquals("Submit"))
		{
			cardLayout.show(container, "1");
		}
	    else if(command.contentEquals("View Past Records")) {
			   
			   cardLayout.show(container, "2");	  
		 }
		else if (command.contentEquals("Back"))
		{
			cardLayout.show(container, "2");
		}
    //goes to add record panel
	else if(command.contentEquals("Add New Record")) {
		   
		   cardLayout.show(container, "3");	   }
	    
	 //goes to login panel  
	else if(command.contentEquals("Cancel")) {
		   
		   cardLayout.show(container, "4");	   }
	   

	else if (command.contentEquals("Change Profile"))
	{
		cardLayout.show(container, "4");
	}
		
    //goes to create profile
	else if(command.contentEquals("Create Profile")) {
	//goes to login if cancel button is pressed on createprofile page
		   cardLayout.show(container, "5");	   }




 }
		
 

	
}
