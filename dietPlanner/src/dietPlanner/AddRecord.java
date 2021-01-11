package dietPlanner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddRecord extends JPanel implements ActionListener{
	private JPanel border;
	
	//upper section of border panel
	private JPanel north=new JPanel();
	//middle section of border panel
	private JPanel center=new JPanel();
	//panel to hold the form
	private JPanel form=new JPanel();
	//stores elemetns for bottom part of GUI
	private JPanel south=new JPanel();
	
	//stores the calorie count for the day
	private int caloriesCount=0;


		//creates container that will be used to pass container from main
	   JPanel container;
		public AddRecord(GuiControl mmc, JPanel container) {
			//passes container values from main
			this.container=container;
			
			//creates calorie label to display how many calories have been consumed for that particular day
			JLabel caloriecountLabel=new JLabel("Calories for day=0");
			//adds the label to the middle panel
			center.add(caloriecountLabel);
			
			//create title
			JLabel title=new JLabel("Add Record for "+mmc.getDate());
			//adds title to gui
			north.add(title);
		
			
		//sets layout for center being a 1xY format
		center.setLayout(new GridLayout(0,1));	
		border = new JPanel(new BorderLayout());
		//add north panel to border in north position of panel
		border.add(north,BorderLayout.NORTH);
		//add center panel to the border in center position of panel
		border.add(center,BorderLayout.CENTER);
		//adds south panel to the bottom of hte panel
		border.add(south, BorderLayout.SOUTH);
		//adds border to gui
		this.add(border);
		

	
		
		   
		   
		   
		   //create table
		   //creates a new tablemodel
		   DefaultTableModel model = new DefaultTableModel(); 
		   //creates a table from that model
		   JTable table = new JTable(model); 

		   
		   // Create columns for that model
		   model.addColumn("Meal"); 
		   model.addColumn("Food");
		   model.addColumn("Calories");
		   model.addColumn("description");

		   
		   
		   	//returns the profile name being used
			  String selectedProfile= mmc.getSelectedProfile();
			  
			//returns the date chosen on home.java from the calandar
			  String date=mmc.getDate();
			  //get a file from file storage using the selected profile and date information
		   File f = new File("users/"+selectedProfile+"/"+date+".txt");
		   //if the file exists
		   if(f.exists() && !f.isDirectory()) { 
			   try {
				   //creates a scanner to read file
					Scanner scanner = new Scanner(f);
					//continues to read file until end of file
					while (scanner.hasNextLine()) {
						//gets next line of text file
						String line=scanner.nextLine();
						//splits lines of text file alongdivider
						String lines[]=line.split("/columnData/");
						//stores split pieces of info in corresponding table columns
						model.addRow(new Object[] {lines[1], lines[2], lines[3], lines[4]});
						//adds number of calories to calorie count
						caloriesCount= Integer.parseInt(lines[3])+caloriesCount;
						//updates calorie count
						caloriecountLabel.setText("Calories for day="+String.valueOf(caloriesCount));
					}
					//closes the scanner
					scanner.close();
				} catch (FileNotFoundException e) {
					//notifies user if file isn't found.  should not happen
					e.printStackTrace();
				}
			}
		   //adds scrollpane to table
		   JScrollPane sp = new JScrollPane(table);

		   //adds table with scrollpane to north section of gui
		   north.add(sp);
		   
		
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   //meal label
		   JLabel mealLabel=new JLabel("Meal");
		   form.add(mealLabel);
		   //combobox for meal
		   JComboBox meal=new JComboBox();
		   form.add(meal);
		   //adds various meals to combobox
		   meal.addItem("Breakfast");
		   meal.addItem("Lunch");
		   meal.addItem("Dinner");
		   meal.addItem("Snack AM");
		   meal.addItem("Snack PM");
		   meal.addItem("Other");
		   
		   //label for food name input
		   JLabel foodNameLabel=new JLabel("Food Name");
		   form.add(foodNameLabel);
		   //textfield to enter food names
		   JTextField foodName=new JTextField();
		   form.add(foodName);
		   
		   //label for calories
		   JLabel caloriesLabel=new JLabel("Calories");
		   form.add(caloriesLabel);
		   //textfield for calorie input
		   JTextField calories=new JTextField();
		   form.add(calories);
		   
		   //creates label for description
		   JLabel descriptionLabel=new JLabel("Description");
		   form.add(descriptionLabel);
		   
		   //creates textbox for description
		   JTextField description=new JTextField();
		   form.add(description);

		   //button to add meal information to table
		   JButton addMeal=new JButton("Add Meal");
		   form.add(addMeal);
		   
		   //adds actionilstener to add meal button
			addMeal.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  //checks if foodname is empty
					  if(foodName.getText().equalsIgnoreCase(""))
					  {
						  System.out.print("Food Name cannot be empty");
						  }
					  //checks if calories is a nondigit
					  else if (!(calories.getText().matches("[0-9]+"))) {
						   
					  System.out.print("CALORIES MUST BE A Whole NUMBER");}
					   //checks if description is empty
					  else if(description.getText().equalsIgnoreCase(""))
					  {
						  System.out.print("description cannot be empty");
						  }
					   
					  //if food isn't empty, calories is a digit and description isn't empty perform
				  
					  else {
						 //add information to corresponding column 
						  model.addRow(new Object[]{meal.getSelectedItem(), foodName.getText(), calories.getText(), description.getText()}); 
						 //add calorie count of new meal/dish to existing calorie count
						  caloriesCount= Integer.parseInt(calories.getText())+caloriesCount;
						  //displays new totalcalorie count
							caloriecountLabel.setText("Calories for day="+String.valueOf(caloriesCount));

						  }
					  	
					  }
					  
				  
				  
				  		});
		   //makes button to remove selected meal from list
		   JButton removeMeal=new JButton("Remove Meal");
		   //adds remove meal to form
		   form.add(removeMeal);
		   //adds actionlistener to remove meal
			removeMeal.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) {
					  //subtracts number of calories equal to amount about to be removed by removing its corresponding row
					  caloriesCount= caloriesCount-Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
						caloriecountLabel.setText("Calories for day="+String.valueOf(caloriesCount));

					  //removes selcted row
					   model.removeRow(table.getSelectedRow());

					  
				  }});
		   //sets gridlayout to form in a 2 wide format
			form.setLayout(new GridLayout(0,2));	
			center.add(form);
			
			//adds goback button to go to home page without saving
			JButton cancel=new JButton("Go Back");
			cancel.addActionListener(mmc);
			south.add(cancel);
			
			//adds save button to save and return to home page
			JButton save=new JButton("Save");
			south.add(save);
			
			//make save button change panel by using guicontrol
			save.addActionListener(mmc);
			//adds actionlistener to save
			save.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  //
					  try {
						  //returns selected profile
						  String selectedProfile= mmc.getSelectedProfile();
					  //returns date selected
						  String date=mmc.getDate();
					
						   //prints out file pathway
						  // System.out.print("users/"+selectedProfile+"/"+date+".txt");
						   //creates a textfile with users name in the users folder
						      File myObj = new File("users/"+selectedProfile+"/"+date+".txt");
						      
						     						    
						      //notifies user profile was made
						    	
						        System.out.println("File created: " + myObj.getName());
						        
						        //makes a file output stream to write file.  overwrites previous file
						    	FileOutputStream fos = new FileOutputStream(myObj, false);
						     
						    	//creates a buffered writer to write to write to fileoutputstream file
						    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
						     //iterates through table for number of rows
						    	for (int i = 0; i < table.getRowCount(); i++) {
						    		//goes through each column
						    		for(int x=0;x<table.getColumnCount();x++)
						    		{
						    		//writes value that will be split on raed
							    	bw.write("/columnData/");
							    	//writes info from corresponding cell.  reads left to write light a book
						    		bw.write(table.getModel().getValueAt(i, x).toString());
						    		
						    		}
						    		//creates newline in text file
						    		bw.newLine();
						    	}
						    	//closes writer
						    	bw.close();
					
						        
						        
						        
						       
						      //notifies user that file already exists
						       
						    
						    } catch (IOException e1) {
						    	//notifies user that an error occurred when trying to create the file
						      System.out.println("An error occurred.");
						      e1.printStackTrace();
						    }
						  }
						   
						   
					  
				  });
			
		
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	
	
}
