import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; 
import java.text.NumberFormat;
import javax.swing.text.*;

/*
 * 	- number of algos
	- Radi const 
	- number of vertices
 */
public class ConicSpiralGeneratorView implements PropertyChangeListener, ItemListener{
	
		private static final boolean TRACE = false;
	  
	   private static final String TAB_TITLE = "Conic Spiral";
	   private static final String TAB_TOOLTIP = "Conic Spiral generator options";
	   private static final String GENERATE_CHECKBOX_TITLE = "Generate data file?";
	   private static final boolean DEFAULT_GENERATE_FLAG = true;
	   private static final int DEFAULT_CS_COUNT = 20;
	   private static final int DEFAULT_RD_LENGTH = 10;
	   private static final int DEFAULT_NV_COUNT = 15;
	   
	   //***
	   // instance variables
	   //***

	   // parent panel for all elements
	   private JPanel theTabbedPanePanel;

	   // elements for 'generate' flag
	   private JCheckBox theGenerateCheckbox;
	   private JPanel theGeneratePanel;

	   // elements for 'number of Conic Spiral algorithms'
	   private NumberFormat theNumberOfConicSpiralFormat; 		//theNumberOfConicSpiralFormat
	   private JPanel theNumberOfConicSpiralPanel;				//theNumberOfConicSpiralPanel
	   private JLabel theNumberOfConicSpiralLabel;				//theNumberOfConicSpiralLabel
	   private JFormattedTextField theNumberOfConicSpiralField;	//theNumberOfConicSpiralField

	   // elements for 'radius length'
	   private NumberFormat theMaximumRadiusFormat;
	   private JPanel theMaximumRadiusPanel;
	   private JLabel theMaximumRadiusLabel;
	   private JFormattedTextField theMaximumRadiusField;
	   
	   //elements for 'number of vertices'
	   private NumberFormat theNumberOfVerticesFormat;
	   private JPanel theNumberOfVerticesPanel;
	   private JLabel theNumberOfVerticesLabel;
	   private JFormattedTextField theNumberOfVerticesField;

	   // property values
	   private boolean theGenerateFlag;
	   private int theNumberOfConicSpirals;
	   private double theMaximumRadius;
	   private int theNumberOfVertices;
	   
	   /**
	    * ConicSpiralGeneratorView
	    *
	    * This class implements the view for the Conic Spiral class and
	    * handles related user interface events
	    */
	   
	   ConicSpiralGeneratorView()
	   {
	      theGenerateFlag = DEFAULT_GENERATE_FLAG;
	      theNumberOfConicSpirals = DEFAULT_CS_COUNT;
	      theMaximumRadius = DEFAULT_RD_LENGTH;
	      theNumberOfVertices = DEFAULT_NV_COUNT;
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
	   
	   public void setNumberOfConicSpirals(int aCount)
	   {
		   theNumberOfConicSpirals = aCount;
	      if (theNumberOfConicSpiralField != null)
	      {
	    	  theNumberOfConicSpiralField.setValue(theNumberOfConicSpirals);
	    	  theNumberOfConicSpiralField.updateUI();
	      }
	   }
	   /**
	    * setRadius Length
	    * 
	    * This method sets the current maximum radius length
	    */
	   
	   public void setMaximumRadiusLength(double aLength)
	   {
		   theMaximumRadius = aLength;
	      if (theMaximumRadiusField != null)
	      {
	    	  theMaximumRadiusField.setValue(theMaximumRadiusField);
	    	  theMaximumRadiusField.updateUI();
	      }
	   }
	   
	   /**
	    * setNumber of vertices 
	    * 
	    * This method sets the current number of vertices
	    */
	   
	   public void setNumberOfVertices(int aLength)
	   {
		   theNumberOfVertices = aLength;
	      if (theNumberOfVerticesField != null)
	      {
	    	  theNumberOfVerticesField.setValue(theNumberOfVerticesField);
	    	  theNumberOfVerticesField.updateUI();
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
		   * This method returns the current number of algorithms generated
		   */
		   public int getNumberOfConicSpirals()
		   {
		      return theNumberOfConicSpirals;
		   }
		   /**
		    * getMaximumRadiusLength
		    * 
		    * This method returns the current Radius length
		    */
		   public double getMaximumRadiusLength()
		   {
		      return theMaximumRadius;
		   }
		   /**
		    * getNumberOfVertices
		    * 
		    * This method returns the current number of steps
		    */
		   
		   public int getNumberOfVertices()
		   {
			   return theNumberOfVertices;
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
		      // number of conic spirals
		      //***

		      // build format arguments
			  theNumberOfConicSpiralFormat = NumberFormat.getIntegerInstance();

		      // create number of point elements [label, field]
			  theNumberOfConicSpiralLabel = new JLabel("Number of Conic Spirals:");
			  theNumberOfConicSpiralLabel.setHorizontalAlignment(JLabel.LEFT);
		      theNumberOfConicSpiralField = new JFormattedTextField(theNumberOfConicSpiralFormat);
		      
		      theNumberOfConicSpiralField.setValue(new Double(theNumberOfConicSpirals));
		      theNumberOfConicSpiralField.setColumns(10);
		      theNumberOfConicSpiralField.addPropertyChangeListener("value", this);
		      

		      // add to containing panel
		      theNumberOfConicSpiralPanel = new JPanel();
		      theNumberOfConicSpiralPanel.add(theNumberOfConicSpiralLabel);
		      theNumberOfConicSpiralPanel.add(theNumberOfConicSpiralField);
			  
		      
		      //***
		      // radius length
		      //***
		      
		      // build format arguments
		      theMaximumRadiusFormat = NumberFormat.getNumberInstance();

		      // create number of point elements [label, field]
		      theMaximumRadiusLabel = new JLabel("Radius Length:");
		      theMaximumRadiusLabel.setHorizontalAlignment(JLabel.LEFT);
		      theMaximumRadiusField = new JFormattedTextField(theMaximumRadiusFormat);
		      
		      theMaximumRadiusField.setValue(new Double(theMaximumRadius));
		      theMaximumRadiusField.setColumns(10);
		      theMaximumRadiusField.addPropertyChangeListener("value", this);
		      

		      // add to containing panel
		      theMaximumRadiusPanel = new JPanel();
		      theMaximumRadiusPanel.add(theMaximumRadiusLabel);
		      theMaximumRadiusPanel.add(theMaximumRadiusField);
		      
		      //***
		      // number of vertices
		      //***
		      
		      // build format arguments
		      theNumberOfVerticesFormat = NumberFormat.getIntegerInstance();

		      // create number of point elements [label, field]
		      theNumberOfVerticesLabel = new JLabel("Number of Vertices:");
		      theNumberOfVerticesLabel.setHorizontalAlignment(JLabel.LEFT);
		      theNumberOfVerticesField = new JFormattedTextField(theNumberOfVerticesFormat);
		      
		      theNumberOfVerticesField.setValue(new Double(theNumberOfVertices));
		      theNumberOfVerticesField.setColumns(10);
		      theNumberOfVerticesField.addPropertyChangeListener("value", this);
		      
		      // add to containing panel
		      theNumberOfVerticesPanel = new JPanel();
		      theNumberOfVerticesPanel.add(theNumberOfVerticesLabel);
		      theNumberOfVerticesPanel.add(theNumberOfVerticesField);
		      
		      // build tab
		      theTabbedPanePanel = new JPanel();
		      theTabbedPanePanel.setLayout(new BoxLayout(theTabbedPanePanel, BoxLayout.PAGE_AXIS));
		      theTabbedPanePanel.add(theGeneratePanel);
		      theTabbedPanePanel.add(theNumberOfConicSpiralPanel);
		      theTabbedPanePanel.add(theMaximumRadiusPanel);
		      theTabbedPanePanel.add(theNumberOfVerticesPanel);
		      
		      
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
		      if (source == theNumberOfConicSpiralField)
		      {
		    	  theNumberOfConicSpirals = ((Number)theNumberOfConicSpiralField.getValue()).intValue();
		         if (TRACE)
		            System.out.println("Conic Spiral: number of algorithms = " + theNumberOfConicSpirals);
		      }
		      else if (source == theMaximumRadiusField)
		      {
		    	  theMaximumRadius = ((Number)theMaximumRadiusField.getValue()).doubleValue();
		         if (TRACE)
		            System.out.println("Conic Spiral: maximum step length = " + theMaximumRadius);
		      }
		      else if (source == theNumberOfVerticesField)
		      {
		    	  theNumberOfVertices = ((Number)theNumberOfVerticesField.getValue()).intValue();
		    	  if (TRACE)
		    		  System.out.println("Conic Spiral: number of steps = " + theNumberOfVertices);
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
		        	 theNumberOfConicSpiralField.setEnabled(true);
		        	 theMaximumRadiusField.setEnabled(true);
		        	 theNumberOfVerticesField.setEnabled(true);
		         }
		         else
		         {
		        	 theNumberOfConicSpiralField.setEnabled(false);
		        	 theMaximumRadiusField.setEnabled(false);
		        	 theNumberOfVerticesField.setEnabled(false);
		         }
		         if (TRACE)
		            System.out.println("Random Walk: generate = " + theGenerateFlag);
		      }
		   }
	   
}
