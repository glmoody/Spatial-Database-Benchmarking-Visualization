/**
 * TriangleGenerator.java
 *
 * @author :  Weijun Huang, Tim Faulkner
 * @version : 12/19/2011
 */


import javax.swing.*;
import java.lang.Math;
import java.io.*;
// test 2.0
public class TriangleGenerator {
        
   /**
    * TriangleGenerator
    *
    * This class generates triangles randomly within a (N by N) space.  The
    * triangles points are writen to a ASCII text file.
    */
   TriangleGenerator()
   {
   }

   /**
    * generate
    */
   public void generate(DataGenModel aModel) throws IOException
   {
      String parameter;
      
        int cnt;
        double leftX, lowerY;
        double i, x, y;
        boolean collinear = false, inlist = false;
        String outFilename;
        FileWriter f = null;
        PrintWriter out = null;

        double[] pointsX = new double[4];
        double[] pointsY = new double[4];

      // do we wish data generated?
      if (aModel.theGenerateTrianglesFlag == false)
         return;

      // setup file output
      outFilename = aModel.theFilenamePrefix + "triangles.txt";
      f = new FileWriter(outFilename);
      out = new PrintWriter(f);

      // generate data
      System.out.println("  creating triangles datafile [" + outFilename + "]");

        cnt = 0;
        while (cnt < aModel.theNumberOfTriangles)
        {
           // randomly position the bounding rectangle in the grid
           leftX  = (Math.random() * (aModel.theSceneLength - aModel.theTriangleBBoxWidth) );
           lowerY = (Math.random() * (aModel.theSceneLength - aModel.theTriangleBBoxHeight) );

           // the initial vertices of the Triangle
           x = leftX  + (Math.random() * aModel.theTriangleBBoxWidth);
           y = lowerY + (Math.random() * aModel.theTriangleBBoxHeight);
           pointsX[0] = x;
           pointsY[0] = y;

           // the 2nd vertices of the Triangle
           x = leftX  + (Math.random() * aModel.theTriangleBBoxWidth);
           y = lowerY + (Math.random() * aModel.theTriangleBBoxHeight);
           while (inList(pointsX, pointsY, 1, x, y))
           {
              x = leftX  + (Math.random() * aModel.theTriangleBBoxWidth);
              y = lowerY + (Math.random() * aModel.theTriangleBBoxHeight);
           }
           pointsX[1] = x;
           pointsY[1] = y;

           // the 3rd vertices of the Triangle
           x = leftX  + (Math.random() * aModel.theTriangleBBoxWidth);
           y = lowerY + (Math.random() * aModel.theTriangleBBoxHeight);
           collinear = isCollinear(pointsX[0], pointsY[0], pointsX[1], pointsY[1], x, y);
           inlist    = inList(pointsX, pointsY, 2, x, y);
           while (collinear || inlist)
           {
              x = leftX  + (Math.random() * aModel.theTriangleBBoxWidth);
              y = lowerY + (Math.random() * aModel.theTriangleBBoxHeight);
              collinear = isCollinear(pointsX[0], pointsY[0], pointsX[1], pointsY[1], x, y);
              inlist    = inList(pointsX, pointsY, 2, x, y);
           }
           pointsX[2] = x;
           pointsY[2] = y;

           //Old TRIANGLE ((926 918) (941 903) (913 954))
           //New TRIANGLE ((926 918, 941 903, 913 954))
           out.print("TRIANGLE ((");
           out.print(pointsX[0] +" " + pointsY[0] + ", ");
           out.print(pointsX[1] +" " + pointsY[1] + ", ");
           out.print(pointsX[2] +" " + pointsY[2]);
           out.println("))");

           cnt++;
           if (cnt%10000 == 0)
             System.out.println("    generated " + cnt + " of " + aModel.theNumberOfTriangles);
        }
        out.close();
        System.out.println("    " + aModel.theNumberOfTriangles + " triangles were generated.");
    }
    
    private boolean inList(double[] pointsX, double[] pointsY, int length,
                                  double x, double y)  {
      for (int i = 0; i < length; i ++)
         if ((pointsX[i] == x) && (pointsY[i] == y))
                return true;
        return false;
    }

    private boolean isCollinear(double x1, double y1, double x2, double y2, double x3, double y3)  {
         // check collinearity of three points (x1, y1), (x2, y2), & (x3, y3)
    	 double d1, d2;
         d1 = x2 - x1;
         d2 = x3 - x1;
         if (d1 == 0 && d2 == 0)
            return true;
         else
         if (d1 != 0 && d2 != 0)
            if ( (y2 - y1)/d1 == (y3 - y1)/d2 )
               return true;
            return false;
    }
}
