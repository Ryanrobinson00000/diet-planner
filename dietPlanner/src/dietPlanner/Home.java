package dietPlanner;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;



public class Home extends JPanel implements ActionListener{
	private JPanel border;
	
	//upper section of border panel
	private JPanel north=new JPanel();
	//middle section of border panel
	private JPanel center=new JPanel();
	//lower middle section of border panel
	private JPanel south=new JPanel();
	
	
	   JPanel container;
		public Home(GuiControl mmc, JPanel container) {
			
			String selectedProfile=mmc.getSelectedProfile();
			//sets container value from passed value
			this.container=container;
		
			//creates label to be used at top of page
		JLabel titleLabel=new JLabel(" Diet Tracker");
		north.add(titleLabel);

		//will become visible if a date is not chosen and user tries to add a record
		JLabel dateNotChosenAlert=new JLabel("Please choose a date to add record to");
		center.add(dateNotChosenAlert);
		dateNotChosenAlert.setForeground(Color.red);
		//makes warning invisible by default
		dateNotChosenAlert.setVisible(false);
		
		//creates new datemodel to make calandar
		UtilDateModel model = new UtilDateModel();
		
		//stores the today year and month properties to calandar
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		//creates a panelusing model and properties
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		//makes a datepicker from datepanel and by running the datelabelformatter function
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		

		//adds calandar to datepicker 
		center.add(datePicker);
		
		
		
		
		
		

		
		//creates button to add record. will go to addrecord
		JButton addRecordButton=new JButton("Add New Record");
		//creates actionlistener to go to addrecord panel
	
		//adds addrecorbutton to center of panel
		center.add(addRecordButton);
		
		
		//creates button to view past records.  will go to viewrecords
	JButton viewButton=new JButton("View Past Records");
	//viewbutton to center panel
	center.add(viewButton);
		
		//makes button that return to the changeprofile menu
		JButton changeProfile=new JButton("Change Profile");
		changeProfile.addActionListener(mmc);
		center.add(changeProfile);
	
		//sets layout to 1xY format
		center.setLayout(new GridLayout(0,1));	
		//creates new borderlayout for border variable
		border = new JPanel(new BorderLayout());
		//add north panel to border in north position of panel
		border.add(north,BorderLayout.NORTH);
		//add center panel to the border in center position of panel
		border.add(center,BorderLayout.CENTER);

		
		//makes border potentially visible
		this.add(border);
		
		viewButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  
			    	        //creates a new login panel with updated info
					        viewRecords view2=new viewRecords(mmc, container);
					        //adds new panel to the panel with same name to overwrite
							container.add(view2,"2");
							
							//ensures graphics update properly
							container.repaint();
							container.revalidate();
							
							//gets the cardlayout to allow for transition of screen
							CardLayout copy=mmc.getCardLayout();
							//changes screen to new login panel with new profile added
							copy.show(container, "2");

					  }
			   
			} );
		//adds button to go to records panel
		addRecordButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  //do not allow user to go to records panel if a date isnt chosen and makes alert visible
				  if(datePicker.getJFormattedTextField().getText().contentEquals("")) {
					 dateNotChosenAlert.setVisible(true);
				  }
				  //makes alert invisible if record is chosen
				  else {
					  dateNotChosenAlert.setVisible(false);
					  //sets chosen date
				  			mmc.setDate(datePicker.getJFormattedTextField().getText());
			    	        //creates a new login panel with updated info
					        AddRecord view3=new AddRecord(mmc, container);
					        //adds new panel to the panel with same name to overwrite
							container.add(view3,"3");
							
							//ensures graphics update properly
							container.repaint();
							container.revalidate();
							
							//gets the cardlayout to allow for transition of screen
							CardLayout copy=mmc.getCardLayout();
							//changes screen to new login panel with new profile added
							copy.show(container, "3");
				  }
					  }
			   
			} );
		
		
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	
		
		
		public class DateLabelFormatter extends AbstractFormatter {
			//gets date in format year month day
		    private String datePattern = "yyyy-MM-dd";
		    //creates simpledtateformat using datepattern variable year month day
		    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		    //overrides to modify  text string to a value and returns that value
		    @Override
		    public Object stringToValue(String text) throws ParseException {
		        return dateFormatter.parseObject(text);
		    }
		    //override sto modify a value to a text string and returns that value using value from calaendar
		    @Override
		    public String valueToString(Object value) throws ParseException {
		        if (value != null) {
		            Calendar cal = (Calendar) value;
		            return dateFormatter.format(cal.getTime());
		        }
		        //if neither of these occur return an empty string
		        return "";
		    }

		}
}
