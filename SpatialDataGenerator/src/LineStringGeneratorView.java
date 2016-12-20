/**
 * LineStringGeneratorView.java
 *
 * @author :  Tim Faulkner
 * @version : 12/19/2011
 */

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; 
import java.text.NumberFormat;
import javax.swing.text.*;

public class LineStringGeneratorView implements PropertyChangeListener, ItemListener
{
   //***
   // class variables
   //***

   private static final boolean TRACE = false;
   
   private static final String TAB_TITLE = "LineStrings";
   private static final String TAB_TOOLTIP = "LineString generator options";
   private static final String GENERATE_CHECKBOX_TITLE = "Generate data file?";
   private static final boolean DEFAULT_GENERATE_FLAG = true;
   private static final int DEFAULT_LINESTRING_COUNT = 25;
   private static final int DEFAULT_MAX_SEGMENT_COUNT = 10;

   private static final int DEFAULT_MAX_SEGMENT_LENGTH = 100;
   private static final int DEFAULT_MIN_SEGMENT_COUNT = 1;
   private static final int DEFAULT_MIN_SEGMENT_LENGTH = 1;

   //private static final double DEFAULT_MAX_SEGMENT_LENGTH = 100;
//branch 'master' of ssh://git@github.fit.edu/haltammami2013/SpaceBench.git
   
   //***
   // instance variables
   //***

   // parent panel for all elements
   private JPanel theTabbedPanePanel;

   // elements for 'generate' flag
   private JCheckBox theGenerateCheckbox;
   private JPanel theGeneratePanel;

   // elements for 'number of line strings'
   private NumberFormat theNumberOfLineStringsFormat;
   private JPanel theNumberOfLineStringsPanel;
   private JLabel theNumberOfLineStringsLabel;
   private JFormattedTextField theNumberOfLineStringsField;

   // elements for 'max segmeng count'
   private NumberFormat theMaximumSegmentCountFormat;
   private JPanel theMaximumSegmentCountPanel;
   private JLabel theMaximumSegmentCountLabel;
   private JFormattedTextField theMaximumSegmentCountField;

   // elements for 'max segment length'
   private NumberFormat theMinimumSegmentLengthFormat;
   private JPanel theMinimumSegmentLengthPanel;
   private JLabel theMinimumSegmentLengthLabel;
   private JFormattedTextField theMinimumSegmentLengthField;
   
   // elements for 'min segmeng count'
   private NumberFormat theMinimumSegmentCountFormat;
   private JPanel theMinimumSegmentCountPanel;
   private JLabel theMinimumSegmentCountLabel;
   private JFormattedTextField theMinimumSegmentCountField;

   // elements for 'min segment length'
   private NumberFormat theMaximumSegmentLengthFormat;
   private JPanel theMaximumSegmentLengthPanel;
   private JLabel theMaximumSegmentLengthLabel;
   private JFormattedTextField theMaximumSegmentLengthField;

   // property values
   private boolean theGenerateFlag;
   private int theNumberOfLineStrings;
   private int theMaximumSegmentCount;

   //private int theMaximumSegmentLength;
   private int theMinimumSegmentCount;
   private int theMinimumSegmentLength;

   private double theMaximumSegmentLength;
//branch 'master' of ssh://git@github.fit.edu/haltammami2013/SpaceBench.git

   /**
    * LineStringGeneratorView
    *
    * This class implements the view for the PolygonGenerator class and
    * handles related user interface events
    */
   LineStringGeneratorView()
   {
      theGenerateFlag = DEFAULT_GENERATE_FLAG;
      theNumberOfLineStrings = DEFAULT_LINESTRING_COUNT;
      theMaximumSegmentCount = DEFAULT_MAX_SEGMENT_COUNT;
      theMaximumSegmentLength = DEFAULT_MAX_SEGMENT_LENGTH;
      theMinimumSegmentCount = DEFAULT_MIN_SEGMENT_COUNT;
      theMinimumSegmentLength = DEFAULT_MIN_SEGMENT_LENGTH;

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
    * setNumberOfLineStrings
    * 
    * This method sets the current number of squares
    */
   public void setNumberOfLineStrings(int aCount)
   {
      theNumberOfLineStrings = aCount;
      if (theNumberOfLineStringsField != null)
      {
         theNumberOfLineStringsField.setValue(theNumberOfLineStrings);
         theNumberOfLineStringsField.updateUI();
      }
   }

   /**
    * setMaximumSegmentCount
    *
    * This method sets the current maximum segment count
    */
   public void setMaximumSegmentCount(int aCount)
   {
      theMaximumSegmentCount = aCount;
      if (theMaximumSegmentCountField != null)
      {
         theMaximumSegmentCountField.setValue(theMaximumSegmentCount);
         theMaximumSegmentCountField.updateUI();
      }
   }

   /**
    * setMaximumSegmentLength
    * 
    * This method sets the current maximum bbox length
    */
   public void setMaximumSegmentLength(double aLength)
   {
      theMaximumSegmentLength = aLength;
      if (theMaximumSegmentLengthField != null)
      {
         theMaximumSegmentLengthField.setValue(theMaximumSegmentLength);
         theMaximumSegmentLengthField.updateUI();
      }
   }
   /**
    * setMinimumSegmentCount
    *
    * This method sets the current Minimum segment count
    */
   public void setMinimumSegmentCount(int aCount)
   {
      theMinimumSegmentCount = aCount;
      if (theMinimumSegmentCountField != null)
      {
         theMinimumSegmentCountField.setValue(theMinimumSegmentCount);
         theMinimumSegmentCountField.updateUI();
      }
   }

   /**
    * setMinimumSegmentLength
    * 
    * This method sets the current Minimum bbox length
    */
   public void setMinimumSegmentLength(int aLength)
   {
      theMinimumSegmentLength = aLength;
      if (theMinimumSegmentLengthField != null)
      {
         theMinimumSegmentLengthField.setValue(theMinimumSegmentLength);
         theMinimumSegmentLengthField.updateUI();
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
   * getNumberOfLineStrings
   *
   * This method returns the current number of line strings
   */
   public int getNumberOfLineStrings()
   {
      return theNumberOfLineStrings;
   }

   /**
    * getMaximumSegmentCount
    *
    * This method returns the maximum segment count
    */
   public int getMaximumSegmentCount()
   {
      return theMaximumSegmentCount;
   }

   /**
    * getMaximumSegmentLength
    * 
    * This method returns the maximum segment length
    */
   public double getMaximumSegmentLength()
   {
      return theMaximumSegmentLength;
   }
   /**
    * getMinimumSegmentCount
    *
    * This method returns the maximum segment count
    */
   public int getMinimumSegmentCount()
   {
      return theMinimumSegmentCount;
   }

   /**
    * getMinimumSegmentLength
    * 
    * This method returns the maximum segment length
    */
   public int getMinimumSegmentLength()
   {
      return theMinimumSegmentLength;
   }

   /**
    * build
    * 
    * This method builds the user interface and ties in any
    * evenet listeners
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
      // number of squares
      //***

      // build format arguments
      theNumberOfLineStringsFormat = NumberFormat.getIntegerInstance();

      // create number of elements [label, field]
      theNumberOfLineStringsLabel = new JLabel("Number of line strings:");
      theNumberOfLineStringsLabel.setHorizontalAlignment(JLabel.LEFT);
      theNumberOfLineStringsField = new JFormattedTextField(theNumberOfLineStringsFormat);
      theNumberOfLineStringsField.setValue(new Double(theNumberOfLineStrings));
      theNumberOfLineStringsField.setColumns(10);
      theNumberOfLineStringsField.addPropertyChangeListener("value", this);

      // add to containing panel
      theNumberOfLineStringsPanel = new JPanel();
      theNumberOfLineStringsPanel.add(theNumberOfLineStringsLabel);
      theNumberOfLineStringsPanel.add(theNumberOfLineStringsField);

      //***
      // maximum vertex count
      //***

      // build format arguments
      theMaximumSegmentCountFormat = NumberFormat.getIntegerInstance();

      // create number of point elements [label, field]
      theMaximumSegmentCountLabel = new JLabel("Maximum segment count:");
      theMaximumSegmentCountLabel.setHorizontalAlignment(JLabel.LEFT);
      theMaximumSegmentCountField = new JFormattedTextField(theMaximumSegmentCountFormat);
      theMaximumSegmentCountField.setValue(new Double(theMaximumSegmentCount));
      theMaximumSegmentCountField.setColumns(10);
      theMaximumSegmentCountField.addPropertyChangeListener("value", this);

      // add to containing panel
      theMaximumSegmentCountPanel = new JPanel();
      theMaximumSegmentCountPanel.add(theMaximumSegmentCountLabel);
      theMaximumSegmentCountPanel.add(theMaximumSegmentCountField);

      //***
      // bbox length
      //***

      // build format arguments
      theMaximumSegmentLengthFormat = NumberFormat.getIntegerInstance();

      // create number of point elements [label, field]
      theMaximumSegmentLengthLabel = new JLabel("Maximum segment length:");
      theMaximumSegmentLengthLabel.setHorizontalAlignment(JLabel.LEFT);
      theMaximumSegmentLengthField = new JFormattedTextField(theMaximumSegmentLengthFormat);
      theMaximumSegmentLengthField.setValue(new Double(theMaximumSegmentLength));
      theMaximumSegmentLengthField.setColumns(10);
      theMaximumSegmentLengthField.addPropertyChangeListener("value", this);

      // add to containing panel
      theMaximumSegmentLengthPanel = new JPanel();
      theMaximumSegmentLengthPanel.add(theMaximumSegmentLengthLabel);
      theMaximumSegmentLengthPanel.add(theMaximumSegmentLengthField);
      
      //***
      // minimum segment count
      //***

      // build format arguments
      theMinimumSegmentCountFormat = NumberFormat.getIntegerInstance();

      // create number of point elements [label, field]
      theMinimumSegmentCountLabel = new JLabel("Minimum segment count:");
      theMinimumSegmentCountLabel.setHorizontalAlignment(JLabel.LEFT);
      theMinimumSegmentCountField = new JFormattedTextField(theMinimumSegmentCountFormat);
      theMinimumSegmentCountField.setValue(new Double(theMinimumSegmentCount));
      theMinimumSegmentCountField.setColumns(10);
      theMinimumSegmentCountField.addPropertyChangeListener("value", this);

      // add to containing panel
      theMinimumSegmentCountPanel = new JPanel();
      theMinimumSegmentCountPanel.add(theMinimumSegmentCountLabel);
      theMinimumSegmentCountPanel.add(theMinimumSegmentCountField);

      //***
      // minimum segment length
      //***

      // build format arguments
      theMinimumSegmentLengthFormat = NumberFormat.getIntegerInstance();

      // create number of point elements [label, field]
      theMinimumSegmentLengthLabel = new JLabel("Minimum segment length:");
      theMinimumSegmentLengthLabel.setHorizontalAlignment(JLabel.LEFT);
      theMinimumSegmentLengthField = new JFormattedTextField(theMinimumSegmentLengthFormat);
      theMinimumSegmentLengthField.setValue(new Double(theMinimumSegmentLength));
      theMinimumSegmentLengthField.setColumns(10);
      theMinimumSegmentLengthField.addPropertyChangeListener("value", this);

      // add to containing panel
      theMinimumSegmentLengthPanel = new JPanel();
      theMinimumSegmentLengthPanel.add(theMinimumSegmentLengthLabel);
      theMinimumSegmentLengthPanel.add(theMinimumSegmentLengthField);

      //***
      // update tabbed pane
      //***

      // build tab
      theTabbedPanePanel = new JPanel();
      theTabbedPanePanel.setLayout(new BoxLayout(theTabbedPanePanel, BoxLayout.PAGE_AXIS));
      theTabbedPanePanel.add(theGeneratePanel);
      theTabbedPanePanel.add(theNumberOfLineStringsPanel);
      theTabbedPanePanel.add(theMaximumSegmentCountPanel);
      theTabbedPanePanel.add(theMaximumSegmentLengthPanel);
      theTabbedPanePanel.add(theMinimumSegmentCountPanel);
      theTabbedPanePanel.add(theMinimumSegmentLengthPanel);
      
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
      if (source == theNumberOfLineStringsField)
      {
         theNumberOfLineStrings = ((Number)theNumberOfLineStringsField.getValue()).intValue();
         if (TRACE)
            System.out.println("LineStrings: number of squares = " + theNumberOfLineStrings);
      }
      else if (source == theMaximumSegmentCountField)
      {
         theMaximumSegmentCount = ((Number)theMaximumSegmentCountField.getValue()).intValue();
         if (TRACE)
            System.out.println("LineStrings: maximum segment count = " + theMaximumSegmentCount);
      }
      else if (source == theMaximumSegmentLengthField)
      {
         theMaximumSegmentLength = ((Number)theMaximumSegmentLengthField.getValue()).doubleValue();
         if (TRACE)
            System.out.println("LineStrings: maximum segment length = " + theMaximumSegmentLength);
      }
      else if (source == theMinimumSegmentCountField)
      {
         theMinimumSegmentCount = ((Number)theMinimumSegmentCountField.getValue()).intValue();
         if (TRACE)
            System.out.println("LineStrings: Minimum segment count = " + theMinimumSegmentCount);
      }
      else if (source == theMinimumSegmentLengthField)
      {
         theMinimumSegmentLength = ((Number)theMinimumSegmentLengthField.getValue()).intValue();
         if (TRACE)
            System.out.println("LineStrings: Minimum segment length = " + theMinimumSegmentLength);
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
           theNumberOfLineStringsField.setEnabled(true);
           theMaximumSegmentCountField.setEnabled(true);
           theMaximumSegmentLengthField.setEnabled(true);
           theMinimumSegmentCountField.setEnabled(true);
           theMinimumSegmentLengthField.setEnabled(true);
         }
         else
         {
           theNumberOfLineStringsField.setEnabled(false);
           theMaximumSegmentCountField.setEnabled(false);
           theMaximumSegmentLengthField.setEnabled(false);
           theMinimumSegmentCountField.setEnabled(false);
           theMinimumSegmentLengthField.setEnabled(false);
         }
         if (TRACE)
            System.out.println("LineStrings: generate = " + theGenerateFlag);
      }
   }
}