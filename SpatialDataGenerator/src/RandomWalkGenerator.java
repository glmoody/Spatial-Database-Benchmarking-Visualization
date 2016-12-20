import javax.swing.*;
import java.lang.Math;
import java.io.*;
import java.awt.geom.Point2D;
import java.util.*;

public class RandomWalkGenerator {

	RandomWalkGenerator()
	{
	}
	
	public void generate(DataGenModel aModel) throws IOException
	{
	
		//Set up output 
		String outFilename;
	    FileWriter f = null;
	    PrintWriter out = null;
	    Random rnd = new Random();
	    
	    //Points
	    double x,y;
	    
	    //For random walk direction choice 
	    String[] direction = {"North","South","East","West"};
	    int decision;
	    
	    // do we wish squares generated?
	    if (aModel.theGenerateRandomWalksFlag == false)
	    	return;
	    
	    // Input from User (grabbign from data model)
	    int desiredNumberOfAlgorithms = aModel.theNumberOfRandomWalks;		//number of paths produced 	 
	    int desiredNumberOfSteps = aModel.theNumberOfSteps;					//Range of steps per algorithm (from 3 to n)
	    double stepLength = aModel.theMaximumStepLength;						//Range of step length(from 0 to n)
	    
	    //generate output file
	    outFilename = aModel.theFilenamePrefix + "randomWalk.txt";
		f = new FileWriter(outFilename);
		out = new PrintWriter(f);        
		System.out.println("  creating randomWalk datafile [" + outFilename + "]");
	   
	   
	   //prepare algorithm counters
	   int currentStep;
	   int amountProduced = 0;
	   
	   //Generate desired number of algorithms
	   while (amountProduced < desiredNumberOfAlgorithms){
		   
		   //***
		   //	create next random Walk
		   //***
		   
		   //prepare
		   List<Point2D> xyCoords = new ArrayList<Point2D>();
		   	xyCoords.clear();
		   	currentStep = 0;
		   	x = 0;
		   	y = 0;
		   	
		   	//Random the steps per algorithm 
		   	desiredNumberOfSteps = rnd.nextInt(desiredNumberOfSteps) + 3;		
			
			//generate the starting point
			x = (Math.random()*aModel.theSceneLength)+1;
            y = (Math.random()*aModel.theSceneLength)+1;
            
            //add point to the list
            Point2D startPt = new Point2D.Double(x,y);
            Point2D checkPt = new Point2D.Double(x,y);
            xyCoords.add(startPt);
		
			//generate path from point
		   	for(int i = 1; i<desiredNumberOfSteps; i++){
		   	   
		   		//chose direction to move
		   	    decision = rnd.nextInt(4);
					   
					   if(direction[decision].equals("North")){

			               y = y + stepLength;
			               
					   }else if(direction[decision].equals("South")){
						   
						   y = y - stepLength;
						   
					   }else if(direction[decision].equals("East")){
						   
						   x = x + stepLength;
						   
					   }else if(direction[decision].equals("West")){
						   
						   x = x - stepLength;
					   }
					   
					   //Create Candidate Point
		               Point2D candidatePt = new Point2D.Double(x,y);
		               
		               //Check if: 
		               //			1) the point will not come back to the previous location(redundancy)
		               //			2) the point will not be outside the scene
		               if ( ( ((checkPt.getX() != x) && (checkPt.getY() == y)) 
		            		   || ((checkPt.getX() == x) && (checkPt.getY() != y)) ) 
		            		   && ((x != 0)&&(x > 0) && (y != 0) && (y>0)) ) 
		               {
		            	   
		            	  //if conditions satisfied, add point to the path and up the counter
		                  xyCoords.add(candidatePt);
		                  checkPt = candidatePt;
		                  currentStep++;
		                  
		               }   
		   	   
		   }
		   	
		   	//finish algorithmic path 
		   	amountProduced++;
		   	
		   	//output algorithm to text file 
		   	out.print("LINESTRING (");
			 
		            for (int i = 0; i < xyCoords.size()-1; i++)
		            {
		               x = xyCoords.get(i).getX();
		               y = xyCoords.get(i).getY();
		               
		               out.print(x+" "+y+", ");
		            }
		            
		            x = xyCoords.get(xyCoords.size()-1).getX();
		            y = xyCoords.get(xyCoords.size()-1).getY();
		            out.println(x+" "+y+")");   
		            
        	}
        	out.close();
        	
    	System.out.println("    " + desiredNumberOfAlgorithms + " line strings were generated.");
	}
}