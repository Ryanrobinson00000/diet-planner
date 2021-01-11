package dietPlanner;




	import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
	import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

	public class CreateProfile extends JPanel implements ActionListener{
		private JPanel border;
		
		//upper section of border panel
		private JPanel north=new JPanel();
		//middle section of border panel
		private JPanel center=new JPanel();
		private JPanel south=new JPanel();
		
	
		
			//creates container that will be used to pass container from main
		   JPanel container;
			public CreateProfile(GuiControl mmc, JPanel container) {
				
				
				//passes container values from main
				this.container=container;
				
				
				
				//create title
				JLabel title=new JLabel("Create Profile");
				//adds title to gui
				north.add(title);
				
				//panel to store username info
				JPanel username=new JPanel();
				username.setLayout(new GridLayout(0,2));
				
				//sets profile name label
				JLabel profileName=new JLabel("Profile Name");
				//sets profile name input box
				JTextField profileNameInput=new JTextField();
				//adds jlabel for profilename
				username.add(profileName);
				//adds textfield for input box for username
				username.add(profileNameInput);
				//adds components to the center panel
				center.add(username);
				
				//profile already exists label to notify user if invalid profile creation is attempted
				JLabel alreadyExists=new JLabel("Profile name already exists");
				//makes the notification invisible
				alreadyExists.setVisible(false);
				//adds notification to the center panel
				center.add(alreadyExists);
				
				//button to create profile
				JButton createProfile=new JButton("Create Profile");
				//leaves without creating profile
				JButton cancel=new JButton("Cancel");
				//adds listener to change panel back to login if cancel is pressed
				cancel.addActionListener(mmc);
				//adds cancel to the south panel
				south.add(cancel);
				//adds createprofile to the south panel
				south.add(createProfile);
				
				//performs if createprofilebutton is pressed
				createProfile.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) { 
						   try {
							  
							   //gets the users entered profile name
							   String profileName=profileNameInput.getText();
							   //makes a file for the user profile
							   (new File("users/"+profileName)).mkdir();
							   //creates a textfile with users name in the users folder
							      File myObj = new File("users/"+profileName+"/"+profileName+".txt");
							      
							     
							    //notifies uer if file is created and refreshes login page with new profile  
							      if (myObj.createNewFile()) {
							    	  //notifies user profile was made
							    	  alreadyExists.setVisible(false);
							        System.out.println("File created: " + myObj.getName());
							        
							        //creates a new login panel with updated info
							        Login view4=new Login(mmc, container);
							        //adds new panel to the panel with same name to overwrite
									container.add(view4,"4");
									
									//ensures graphics update properly
									container.repaint();
									container.revalidate();
									
									//gets the cardlayout to allow for transition of screen
									CardLayout copy=mmc.getCardLayout();
									//changes screen to new login panel with new profile added
									copy.show(container, "4");
							        }   
							        
							        
							       
							      //notifies user that file already exists
							       
							       else {
							    	   //displays that profile already exists if file exists
							    	   alreadyExists.setVisible(true);
							        System.out.println("File already exists.");
							        container.repaint();
									container.revalidate();
							      }
							    } catch (IOException e1) {
							    	//notifies uesr that an error occured when trying to create the file
							      System.out.println("An error occurred.");
							      e1.printStackTrace();
							    }
							  }
					   
					} );;
				 
				
				
			//sets layout for center being a 1xY format
			center.setLayout(new GridLayout(0,1));	
			border = new JPanel(new BorderLayout());
			//add north panel to border in north position of panel
			border.add(north,BorderLayout.NORTH);
			//add center panel to the border in center position of panel
			border.add(center,BorderLayout.CENTER);
			border.add(south,BorderLayout.SOUTH);

			south.setLayout(new GridLayout(0, 2));
			
			//adds border to gui
			this.add(border);
			

			
			
			
			
		}
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
			    String command = ae.getActionCommand();
				
			
			}
				
			

		
		
	

}
