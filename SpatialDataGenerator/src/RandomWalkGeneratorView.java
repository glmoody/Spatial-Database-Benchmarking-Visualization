import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; 
import java.text.NumberFormat;
import javax.swing.text.*;

public class RandomWalkGeneratorView implements PropertyChangeListener, ItemListener {
	
	private static final boolean TRACE = false;
  
	   private static final String TAB_TITLE = "Random Walk";
	   private static final String TAB_TOOLTIP = "Random Walk generator options";
	   private static final String GENERATE_CHECKBOX_TITLE = "Generate data file?";
	   private static final boolean DEFAULT_GENERATE_FLAG = true;
	   private static final int DEFAULT_RW_COUNT = 20;
	   private static final double DEFAULT_STEP_LENGTH = 10;
	   private static final int DEFAULT_NS_COUNT = 10;
	   
	   //***
	   // instance variables
	   //***

	   // parent panel for all elements
	   private JPanel theTabbedPanePanel;

	   // elements for 'generate' flag
	   private JCheckBox theGenerateCheckbox;
	   private JPanel theGeneratePanel;

	   // elements for 'number of squares'
	   private NumberFormat theNumberOfRandomWalksFormat; 		//theNumberOfSquaresFormat
	   private JPanel theNumberOfRandomWalksPanel;				//theNumberOfSquaresPanel
	   private JLabel theNumberOfRandomWalksLabel;				//theNumberOfSquaresLabel
	   private JFormattedTextField theNumberOfRandomWalksField;	//theNumberOfSquaresField

	   // elements for 'max step length'
	   private NumberFormat theMaximumStepLengthFormat;
	   private JPanel theMaximumStepLengthPanel;
	   private JLabel theMaximumStepLengthLabel;
	   private JFormattedTextField theMaximumStepLengthField;
	   
	   //elements for 'range of steps'
	   private NumberFormat theNumberOfStepsFormat;
	   private JPanel theNumberOfStepsPanel;
	   private JLabel theNumberOfStepsLabel;
	   private JFormattedTextField theNumberOfStepsField;

	   // property values
	   private boolean theGenerateFlag;
	   private int theNumberOfRandomWalks;
	   private double theMaximumStepLength;
	   private int theNumberOfSteps;
	   
	   /**
	    * RandomWalkGeneratorView
	    *
	    * This class implements the view for the RandomWAlk class and
	    * handles related user interface events
	    */
	   RandomWalkGeneratorView()
	   {
	      theGenerateFlag = DEFAULT_GENERATE_FLAG;
	      theNumberOfRandomWalks = DEFAULT_RW_COUNT;
	      theMaximumStepLength = DEFAULT_STEP_LENGTH;
	      theNumberOfSteps = DEFAULT_NS_COUNT;
	   }

	   /**
	    * setGenerateFlag
	    *
	    * This method sets the generate datfile property
	    */
	   
	   public void setGenerateFlag(boolean aFlag)
	   {
	      if (theGenerateCheckbox != null)
	      {
	         if (theGenerateFlag != aFlag)
	         {
	            theGenerateCheckbox.doClick();
	            theGenerateCheckbox.updateUI();
	         }
	      }
	   }

	   /**
	    * setNumberOfAlgorithms
	    * 
	    * This method sets the current number of algorithms produced
	    */
	   
	   public void setNumberOfRandomWalks(int aCount)
	   {
		   theNumberOfRandomWalks = aCount;
	      if (theNumberOfRandomWalksField != null)
	      {
	    	  theNumberOfRandomWalksField.setValue(theNumberOfRandomWalks);
	    	  theNumberOfRandomWalksField.updateUI();
	      }
	   }

	   /**
	    * setMaximumStep Length
	    * 
	    * This method sets the current maximum side length
	    */
	   
	   public void setMaximumStepLength(double aLength)
	   {
		   theMaximumStepLength = aLength;
	      if (theMaximumStepLengthField != null)
	      {
	    	  theMaximumStepLengthField.setValue(theMaximumStepLengthField);
	    	  theMaximumStepLengthField.updateUI();
	      }
	   }
	   
	   /**
	    * setNumber of Steps 
	    * 
	    * This method sets the current number of steps
	    */
	   
	   public void setNumberOfSteps(int aLength)
	   {
		   theNumberOfSteps = aLength;
	      if (theNumberOfStepsField != null)
	      {
	    	  theNumberOfStepsField.setValue(theNumberOfStepsField);
	    	  theNumberOfStepsField.updateUI();
	      }
	   }
	   

	  /**
	   * getGenerateFlag
	   *
	   * This method returns the generate datfile property
	   */
	   public boolean getGenerateFlag()
	   {
	      return theGenerateFlag;
	   }

	   /**
	   * getNumberOfAlgorithms
	   *
	   * This method returns the current number of paths generated
	   */
	   public int getNumberOfRandomWalks()
	   {
	      return theNumberOfRandomWalks;
	   }

	   /**
	    * getMaximumStepLength
	    * 
	    * This method returns the current step length
	    */
	   public double getMaximumStepLength()
	   {
	      return theMaximumStepLength;
	   }
	   
	   /**
	    * getNumberOfSteps
	    * 
	    * This method returns the current number of steps
	    */
	   
	   public int getNumberOfSteps()
	   {
		   return theNumberOfSteps;
	   }

	   /**
	    * build
	    * 
	    * This method builds the user interface and ties in any
	    * event listeners
	    */
	   
	   public void build(JTabbedPane aTabbedPane)
	   {
	      //***
	      // generate flag
	      //***

	      // create generate flag [checkbox]
	      theGenerateCheckbox = new JCheckBox(GENERATE_CHECKBOX_TITLE);
	      theGenerateCheckbox.setSelected(theGenerateFlag);
	      theGenerateCheckbox.addItemListener(this);

	      // add to containing panel
	      theGeneratePanel = new JPanel();
	      theGeneratePanel.add(theGenerateCheckbox);

	      //***
	      // number of steps 
	      //***
	   
	      //build format argument
	      theNumberOfStepsFormat = NumberFormat.getIntegerInstance();
	      
	      //create number of point elements [label, field]
	      theNumberOfStepsLabel = new JLabel("Maximum bound of steps (3<n) :");
	      theNumberOfStepsLabel.setHorizontalAlignment(JLabel.LEFT);
	      theNumberOfStepsField = new JFormattedTextField(theNumberOfStepsFormat);
	      
	      theNumberOfStepsField.setValue(new Double(theNumberOfSteps));
	      theNumberOfStepsField.setColumns(10);
	      theNumberOfStepsField.addPropertyChangeListener("value", this);

	      theNumberOfStepsPanel = new JPanel();
	      theNumberOfStepsPanel.add(theNumberOfStepsLabel);
	      theNumberOfStepsPanel.add(theNumberOfStepsField);
	      
	      //***
	      // number of random walks
	      //***

	      // build format arguments
	      theNumberOfRandomWalksFormat = NumberFormat.getIntegerInstance();

	      // create number of point elements [label, field]
	      theNumberOfRandomWalksLabel = new JLabel("Number of Random Walks:");
	      theNumberOfRandomWalksLabel.setHorizontalAlignment(JLabel.LEFT);
	      theNumberOfRandomWalksField = new JFormattedTextField(theNumberOfRandomWalksFormat);
	      
	      theNumberOfRandomWalksField.setValue(new Double(theNumberOfRandomWalks));
	      theNumberOfRandomWalksField.setColumns(10);
	      theNumberOfRandomWalksField.addPropertyChangeListener("value", this);
	      

	      // add to containing panel
	      theNumberOfRandomWalksPanel = new JPanel();
	      theNumberOfRandomWalksPanel.add(theNumberOfRandomWalksLabel);
	      theNumberOfRandomWalksPanel.add(theNumberOfRandomWalksField);

	      
	 
	      //***
	      // maximum step length
	      //***

	      // build format arguments
	      theMaximumStepLengthFormat = NumberFormat.getNumberInstance();

	      // create number of point elements [label, field]
	      theMaximumStepLengthLabel = new JLabel("Maximum bound of each step length (0<n) :");
	      theMaximumStepLengthLabel.setHorizontalAlignment(JLabel.LEFT);
	      theMaximumStepLengthField = new JFormattedTextField(theMaximumStepLengthFormat);
	      theMaximumStepLengthField.setValue(new Double(theMaximumStepLength));
	      theMaximumStepLengthField.setColumns(10);
	      theMaximumStepLengthField.addPropertyChangeListener("value", this);

	      // add to containing panel
	      theMaximumStepLengthPanel = new JPanel();
	      theMaximumStepLengthPanel.add(theMaximumStepLengthLabel);
	      theMaximumStepLengthPanel.add(theMaximumStepLengthField);
	      
	      //***
	      // maximum number of steps
	      //***
	      /*	      
	      //build format argument
	      theNumberOfStepsFormat = NumberFormat.getIntegerInstance();
	      
	      //create number of point elements [label, field]
	      theNumberOfStepsLabel = new JLabel("Number of Steps:");
	      theNumberOfStepsLabel.setHorizontalAlignment(JLabel.LEFT);
	      theNumberOfStepsField = new JFormattedTextField(theNumberOfStepsFormat);
	      theNumberOfStepsField.setValue(new Double(theNumberOfSteps));
	      theNumberOfStepsField.setColumns(10);
	      theNumberOfStepsField.addPropertyChangeListener("value", this);

	      //***
	      // update tabbed pane
	      //***

	      theNumberOfStepsPanel = new JPanel();
	      theNumberOfStepsPanel.add(theNumberOfStepsLabel);
	      theNumberOfStepsPanel.add(theNumberOfStepsField);
	      
	      // build tab
	      theTabbedPanePanel = new JPanel();
	      theTabbedPanePanel.setLayout(new BoxLayout(theTabbedPanePanel, BoxLayout.PAGE_AXIS));
	      theTabbedPanePanel.add(theGeneratePanel);
	      theTabbedPanePanel.add(theNumberOfRandomWalksPanel);
	      theTabbedPanePanel.add(theMaximumSideLengthPanel);
	      theTabbedPanePanel.add(theNumberOfStepsPanel);
	       */
	      
	      // build tab
	      theTabbedPanePanel = new JPanel();
	      theTabbedPanePanel.setLayout(new BoxLayout(theTabbedPanePanel, BoxLayout.PAGE_AXIS));
	      theTabbedPanePanel.add(theGeneratePanel);
	      theTabbedPanePanel.add(theNumberOfRandomWalksPanel);
	      theTabbedPanePanel.add(theNumberOfStepsPanel);
	      theTabbedPanePanel.add(theMaximumStepLengthPanel);
	      
	      
	      // add new tab to tabbed pane
	      aTabbedPane.addTab(TAB_TITLE, null, theTabbedPanePanel, TAB_TOOLTIP);
	   }

	   /** 
	    * propertyChange
	    * 
	    * Called when a field's "value" property changes
	    */
	   
	   public void propertyChange(PropertyChangeEvent e)
	   {
	      Object source = e.getSource();
	      if (source == theNumberOfRandomWalksField)
	      {
	    	  theNumberOfRandomWalks = ((Number)theNumberOfRandomWalksField.getValue()).intValue();
	         if (TRACE)
	            System.out.println("Random Walk: number of algorithms = " + theNumberOfRandomWalks);
	      }
	      else if (source == theMaximumStepLengthField)
	      {
	    	  theMaximumStepLength = ((Number)theMaximumStepLengthField.getValue()).doubleValue();
	         if (TRACE)
	            System.out.println("Random Walk: maximum step length = " + theMaximumStepLength);
	      }
	      else if (source == theNumberOfStepsField)
	      {
	    	  theNumberOfSteps = ((Number)theNumberOfStepsField.getValue()).intValue();
	    	  if (TRACE)
	    		  System.out.println("Random Walk: number of steps = " + theNumberOfSteps);
	      }
	   }
	   
	   /**
	    * itemStateChanged
	    *
	    * Called when a checkbox's state changes
	    */
	   public void itemStateChanged(ItemEvent e)
	   {
	      Object source = e.getItemSelectable();
	      if (source == theGenerateCheckbox)
	      {
	         theGenerateFlag = !theGenerateFlag;
	         if (theGenerateFlag)
	         {
	        	 theNumberOfRandomWalksField.setEnabled(true);
	        	 theMaximumStepLengthField.setEnabled(true);
	        	 theNumberOfStepsField.setEnabled(true);
	         }
	         else
	         {
	        	 theNumberOfRandomWalksField.setEnabled(false);
	        	 theMaximumStepLengthField.setEnabled(false);
	        	 theNumberOfStepsField.setEnabled(false);
	         }
	         if (TRACE)
	            System.out.println("Random Walk: generate = " + theGenerateFlag);
	      }
	   }
}
