package dietPlanner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class viewRecords extends JPanel implements ActionListener{
	private JPanel border;
	
	//upper section of border panel
	private JPanel north=new JPanel();
	//middle section of border panel
	private JPanel center=new JPanel();
	private JPanel south=new JPanel();
	
		//creates container that will be used to pass container from main
	   JPanel container;
		public viewRecords(GuiControl mmc, JPanel container) {
			//passes container values from main
			this.container=container;
			
			//create title
			String profile=mmc.getSelectedProfile();
			JLabel title=new JLabel("View Records for "+profile);
			//adds title to gui
			north.add(title);
	
		//sets layout for center being a 1xY format
		center.setLayout(new GridLayout(0,1));	
		
	
		
		
		//creates borderlayout for outer panel
		border = new JPanel(new BorderLayout());
		//add north panel to border in north position of panel
		border.add(north,BorderLayout.NORTH);
		//add center panel to the border in center position of panel
		border.add(center,BorderLayout.CENTER);
		border.add(south, BorderLayout.SOUTH);
		//adds border to gui
		this.add(border);
		
		//stores selected profilename
		String profileName=mmc.getSelectedProfile();
	
		//makes a combobox to store the records and adds it to centr panel
		JComboBox records=new JComboBox();
		center.add(records);
		//gets all file associated with a profile and uses listfilesfolder function to add them to combobox
		final File folder = new File("users/"+profileName+"/");
		listFilesForFolder(folder, records);
		
		//creates submit button to display table
		JButton submit=new JButton("Display Table");
		south.add(submit);
		
		//creates cancel button to return to home panel
		JButton cancel=new JButton("cancel");
		cancel.addActionListener(mmc);
		south.add(cancel);
		
		//adds actionlistener to submit button to change panel to displaytable panel
		submit.addActionListener(mmc);
		//adds actionlistener to submit button
		submit.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  //sets the file selected in guicontrol
				  mmc.setSelectedFile(records.getSelectedItem().toString());
				  //updates displaytable to dipslay new table for selected information
					displayTable view6=new displayTable(mmc, container);
					//adds the view to the container
					container.add(view6,"6");	
					
					//ensures graphics update properly
					container.repaint();
					container.revalidate();
					
					//gets the cardlayout to allow for transition of screen
					CardLayout copy=mmc.getCardLayout();
					//changes screen to new login panel with new profile added
					copy.show(container, "6");
			  }});
	}	
		
		
		
		
		
		
		public void listFilesForFolder(final File folder, JComboBox records) {
			//iterates through files in selected folder
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		        	//goes to next if file is not the directory itself
		            listFilesForFolder(fileEntry, records);
		        } else {
		        	//if file isn't in directory add to combobox
		            records.addItem(fileEntry.getName().toString());
		        }
		    }
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	
}
