package dietPlanner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login extends JPanel implements ActionListener{
	private JPanel border;
	
	//upper section of border panel
	private JPanel north=new JPanel();
	//middle section of border panel
	private JPanel center=new JPanel();
	private JPanel south=new JPanel();
	
		//creates container that will be used to pass container from main
	   JPanel container;
		public Login(GuiControl mmc, JPanel container) {
			//passes container values from main
			this.container=container;
			
			//create title
			JLabel title=new JLabel("Choose Profile");
			//adds title to gui
			north.add(title);
	
			//creates a jcbombobox to store login profiles
			JComboBox profiles=new JComboBox();
			center.add(profiles);
			
		
			//creates a submission button
			JButton Submit=new JButton("Submit");
			south.add(Submit);
			//changes panel when submit is pressed
			Submit.addActionListener(mmc);
			
			//sets selected profile when submit is pressed
			Submit.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  mmc.setSelectedPorfile(profiles.getSelectedItem().toString());
				  }});
			
			//button to go to add profile panel
			JButton addProfile=new JButton("Create Profile");
			center.add(addProfile);
			//goes to createprofile panel
			addProfile.addActionListener(mmc);
			
		//sets layout for center being a 1xY format
		center.setLayout(new GridLayout(0,1));	
		border = new JPanel(new BorderLayout());
		//add north panel to border in north position of panel
		border.add(north,BorderLayout.NORTH);
		//add center panel to the border in center position of panel
		border.add(center,BorderLayout.CENTER);
		border.add(south, BorderLayout.SOUTH);
		//adds border to gui
		this.add(border);
		
		//sets profile information
		setTextField(profiles);
		
		//disables submit if there are not profiles choosable
		if(profiles.getItemCount()==0)
		{
			Submit.setEnabled(false);
		}

		
		
	}
		//sets profile information
		public void setTextField(JComboBox profiles)
		{
			//reads files in user folder
			File folder = new File("users/");
			//stores files in listoffiiles
			File[] listOfFiles = folder.listFiles();
			//iterates through all files in user folder
			for (int i = 0; i < listOfFiles.length; i++) {
				//if the file  is in the directory add that name to the profile combobox
			  if (listOfFiles[i].isDirectory()) {
			    profiles.addItem(listOfFiles[i].getName());
			  }
			}
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String command = arg0.getActionCommand();
			
			 
		
		}


		
}
