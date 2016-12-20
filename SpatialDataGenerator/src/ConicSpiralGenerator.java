import javax.swing.*;
import java.lang.Math;
import java.io.*;
import java.awt.geom.Point2D;
import java.util.*;

public class ConicSpiralGenerator {

	ConicSpiralGenerator()
	{
	}
	
	
	public void generate(DataGenModel aModel) throws IOException
	{
	
		// set up file output 
		String outFilename;
	    FileWriter f = null;
	    PrintWriter out = null;
	    
	    
	    // do we wish spirals generated?
	    if (aModel.theGenerateConicSpiralsFlag == false)
	    	return;
	    
	    // generate output file
	    outFilename = aModel.theFilenamePrefix + "conicSpiral.txt";
		f = new FileWriter(outFilename);
		out = new PrintWriter(f);        
		System.out.println("  creating conicSprial datafile [" + outFilename + "]");

		// input from user
		int numOfConicSpirals = aModel.theNumberOfConicSpirals;
		double radius = aModel.theMaximumRadiusLength;
		//int vertices = aModel.theNumberOfVertices;
		
		
		int cnt = 0;	// loop counter
		
		
		Random r = new Random();
		// variables needed for generating conic spirals
		double x;
		double y;
		double centerX;
		double centerY;
		double nextX;
		double nextY;
		double dist;
		double distLast2Pts;
		double t;
	
		while (cnt < numOfConicSpirals)
        {
			out.print("LINESTRING (");
			
			// generating random center within scene bounds
			centerX = r.nextDouble() * aModel.theSceneLength + 1;
			centerY = r.nextDouble() * aModel.theSceneLength + 1;
			
			t = 0;
			
			do {
				// generating new vertex
				x = centerX + t*Math.cos(2*Math.PI*t);
				y = centerY + t*Math.sin(2*Math.PI*t);
				
				out.printf("%f %f, ", x, y);
				
				// projected vertex after the last one generated
				nextX = x + 0.1*Math.cos(2*Math.PI*0.1);
				nextY = y + 0.1*Math.sin(2*Math.PI*0.1);
				
				// distance formula between center and last vertex generated
				dist = Math.sqrt(Math.pow(centerX - x, 2) + Math.pow(centerY - y, 2));
				
				// distance between last vertex and the projected one
				distLast2Pts = Math.sqrt(Math.pow(nextX - x, 2) + Math.pow(nextY - y, 2));
				
				t += 0.1;	// time increment
			} while (radius > dist + distLast2Pts);		// if next vertex will make spiral's radius > given radius, stop
			out.println(")");
			cnt++;
        }
		out.close();
		System.out.println("    " + numOfConicSpirals + " conic spirals were generated.");
	}
}