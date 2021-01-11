package dietPlanner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class displayTable extends JPanel implements ActionListener{
	private JPanel border;
	
	//upper section of border panel
	private JPanel north=new JPanel();
	//middle section of border panel
	private JPanel center=new JPanel();
	private JPanel form=new JPanel();
	private JPanel south=new JPanel();
	private int calorieCount=0;
	   JPanel container;
		public displayTable(GuiControl mmc, JPanel container) {
			
			
			this.container=container;
			//label to count calories
			JLabel calorieCounterLabel=new JLabel("Calories for day=0");
			
			center.setLayout(new GridLayout(0,1));	
			//creates new borderlayout for border variable
			border = new JPanel(new BorderLayout());
			//add north panel to border in north position of panel
			border.add(north,BorderLayout.NORTH);
			//add center panel to the border in center position of panel
			border.add(center,BorderLayout.CENTER);

			
			
			
			
			
			
			//get chosen filename
			String file=mmc.getSelectedFile();
			//gets chosen profile
			String profile=mmc.getSelectedProfile();
			
			//makes defaulttablemodel
			   DefaultTableModel model = new DefaultTableModel(); 
			 //makes table using this model
			   JTable table = new JTable(model); 

			   // Create columns for meal chart
			   model.addColumn("Meal"); 
			   model.addColumn("Food");
			   model.addColumn("Calories");
			   model.addColumn("description");
			   
			   //tries to open file using profiel and file name
			   File f = new File("users/"+profile+"/"+file);
			   //if the file is in the right place perform
			   if(f.exists() && !f.isDirectory()) { 
				   try {
					   //creates scanner to read file
						Scanner scanner = new Scanner(f);
						//continue reading until file ends
						while (scanner.hasNextLine()) {
							//gets next line in string
							String line=scanner.nextLine();
							//splits line into appropriate pieces to add to chart
							String lines[]=line.split("/columnData/");
							//add split strings to chart columns
							model.addRow(new Object[] {lines[1], lines[2], lines[3], lines[4]});
							
							//adds calories for row to calorie count total
							calorieCount= Integer.parseInt(lines[3])+calorieCount;
							//sets updated calorie count 
							calorieCounterLabel.setText("Calories for day="+String.valueOf(calorieCount));

						}
						//closes file
						scanner.close();
					} catch (FileNotFoundException e) {
						//notify user if file error occured
						e.printStackTrace();
					}
				}
			   //make scrollpane using table
			   JScrollPane sp = new JScrollPane(table);
			   //add table to panel
			   north.add(sp);
			   
			   //make back button to go back to viewrecords
			   JButton Back=new JButton("Back");
			   Back.addActionListener(mmc);
			   south.add(Back);
			   
			   //adds calorie counter label to panel
			   center.add(calorieCounterLabel);
				center.setLayout(new GridLayout(0,1));	
				border = new JPanel(new BorderLayout());
				//add north panel to border in north position of panel
				border.add(north,BorderLayout.NORTH);
				//add center panel to the border in center position of panel
				border.add(center,BorderLayout.CENTER);
				border.add(south, BorderLayout.SOUTH);
				//adds border to gui
				this.add(border);
		
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}
