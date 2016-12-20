/**
 * PolygonGenerator.java
 *
 * @author :  Weijun Huang, Tim Faulkner
 * @version : 12/19/2011
 */


/*  The primary data structure used:

    int[] pointsX, pointsY: coordinates of vertices of a polygon

    char[][] Grid:  Grid[x][y] denotes point (x, y) in the grid
                    Grid[x][y] = '.' means (x, y) is NOT a vertice of the polygon being generated
                    Grid[x][y] = '*' means (x, y) is an existing vertice of the polygon being generated
*/

import javax.swing.*;
import java.lang.Math;
import java.io.*;

public class PolygonGenerator {

   /**
    * PolygonGenerator
    *
    * This class generates polygons randomly within a (N by N) space.  The
    * polygons are writen to a ASCII text file.
    */
   PolygonGenerator()
   {
   }

   /**
    * generate
    *
    * This method places random polygons, on integer bounds, within the grid,
    * writting their locations to the output file. Polygons are solid and may
    * have upto a specified number of verticies (minimum of 3)
    */
   public void generate(DataGenModel aModel) throws IOException
   {

      String parameter;

        int PolygonCNT;
        int NumVer, VerCNT;
        int i, j;
        double x, y, leftX, lowerY;
      String outFilename;
      FileWriter f = null;
      PrintWriter out = null;

      // do we wish polygons generated?
      if (aModel.theGeneratePolygonsFlag == false)
         return;
         
      // setup file output
      outFilename = aModel.theFilenamePrefix + "polygons.txt";
      f = new FileWriter(outFilename);
      out = new PrintWriter(f);

      // generate polygons
      System.out.println("  creating polygon datafile [" + outFilename + "]");

        double[] pointsX = new double[aModel.thePolygonMaxVertexCount + 1];
        double[] pointsY = new double[aModel.thePolygonMaxVertexCount + 1];

      int cnt, trialNum = (int)Math.pow((double)(aModel.thePolygonBBoxLength + 1), 2.0) / 2;

        PolygonCNT = 0;
        while (PolygonCNT < aModel.theNumberOfPolygons)
        {

            // the number of vertices of the polygon >= 3
            NumVer = (int)Math.round( Math.random() * (aModel.thePolygonMaxVertexCount - aModel.thePolygonMinVertexCount) + aModel.thePolygonMinVertexCount);

            // the least x & y of the bounding square
            leftX  = Math.random() * (aModel.theSceneLength - aModel.thePolygonBBoxLength);
            lowerY = leftX;

         // the vertex 0 of the polygon
         x = leftX + Math.random() * aModel.thePolygonBBoxLength;
         y = lowerY + Math.random() * aModel.thePolygonBBoxLength;
         pointsX[0] = x;
         pointsY[0] = y;

            while(true)
            {
            x = leftX + Math.random() * aModel.thePolygonBBoxLength;
            y = lowerY + Math.random() * aModel.thePolygonBBoxLength;

             if ( (x >= leftX && x <= (leftX+aModel.thePolygonBBoxLength)) && (y >= lowerY && y <= (lowerY+aModel.thePolygonBBoxLength))
                 // if (x, y) falls in the bounding square
                 && (x != pointsX[0] || y != pointsY[0]) )
            {
               pointsX[1] = x;
               pointsY[1] = y;
               break;
            }

            }


         // finish the polygon: a sequence of >= 3 vertices
         VerCNT = 2;
         while (VerCNT < NumVer)
         {
            // generate a randomly vertex
            cnt = 1;
            while (cnt <= trialNum)
            {
                 // give a candidate vertex
               x = leftX + Math.random() * aModel.thePolygonBBoxLength;
               y = lowerY + Math.random() * aModel.thePolygonBBoxLength;

                  // check the validness of the vertex
                if ( (x < leftX || x > (leftX+aModel.thePolygonBBoxLength)) || (y < lowerY || y > (lowerY+aModel.thePolygonBBoxLength)) )
                  //if (x, y) is outside the bounding square
                   ;

                else if ( InvalidVertex(x, y, pointsX, pointsY, VerCNT - 1) )
                        ;

                else
                {
                  // (x, y) is selected
                   pointsX[VerCNT] = x;
                   pointsY[VerCNT] = y;
                   break;
                }

                    cnt ++;

            }  // while (cnt <= trialNum)

            if (cnt > trialNum)
               break;
            VerCNT ++;
            if (VerCNT % 1000 == 0)
               System.out.println(VerCNT/1000);

         }  // while loop: finish the polygon

         // print out & draw the polygon
         //Old - POLYGON ((926 918) (941 903) (913 954) (882 881))
         //New - POLYGON ((926 918, 941 903, 913 954, 882 881))
            if (VerCNT >= 3)
            {
               NumVer = VerCNT;
               pointsX[NumVer] = pointsX[0];
               pointsY[NumVer] = pointsY[0];
               out.print("POLYGON ((");
               out.print(pointsX[0] + " " + pointsY[0] + ", ");
               for (i = 1; i <= NumVer-2; i++)
                  out.print(pointsX[i] +" " + pointsY[i] + ", ");
               out.print(pointsX[NumVer-1] +" " + pointsY[NumVer-1]);
               out.println("))");
            }
            PolygonCNT ++;
            if (PolygonCNT % 1000 == 0)
               System.out.println(PolygonCNT/1000);
        }  // while (PolygonCNT < PolygonNum)
        out.close();
        System.out.println("    " + aModel.theNumberOfPolygons + " polygons were generated.");
    }
    
    private boolean isCollinear(double pointsX, double pointsY, double pointsX2, double pointsY2, double x, double y)  {
         // check collinearity of three points (x1, y1), (x2, y2), & (x3, y3)

        // Case 1: two of the points overlap
        if ( (pointsX == pointsX2 && pointsY == pointsY2) || (pointsX2 == x && pointsY2 == y) || (x == pointsX && y == pointsY) )
         return true;

        // Case 2: the points are totally isolated
      double d1 = pointsX - x;
      double d2 = pointsX2 - x;
      if (d1 == 0 && d2 == 0)
         return true;
      else
      if (d1 != 0 && d2 != 0)
         if ( (pointsY - y)/d1 == (pointsY2 - y)/d2 )
            return true;

      return false;

    }

    private boolean passVertex(double pointsX, double pointsY, double x, double y, double[] pointsX2, double[] pointsY2, int start, int end)  {
      // detects if line segment (x1, y1) -> (x2, y2) passes thru any (X[i], Y[i]) for i = start ~ end

      for (int i = start; i <= end; i ++)
         if (isCollinear(pointsX2[i], pointsY2[i], pointsX, pointsY, x, y))
         {
             /* Case 1: (x1, y1) -> (x2, y2) is vertical */
            if (pointsX == x)
                if ( (pointsY <= pointsY2[i] && pointsY2[i] <= y) || (y <= pointsY2[i] && pointsY2[i] <= pointsY) )
                  return true;
             /* Case 2: (x1, y1) -> (x2, y2) is NOT vertical */
            else
                if ( (pointsX <= pointsX2[i] && pointsX2[i] <= x) || (x <= pointsX2[i] && pointsX2[i] <= pointsX) )
                  return true;
         }

        return false;
    }

    private boolean inSegment(double x, double y, double[] pointsX, double[] pointsY, int m)  {
      // detect if (x, y) lies in line segments (X[i-1], Y[i-1]) -> (X[i], Y[i]), for i = 1, 2, ..., m

        for (int i = 1; i <= m; i ++)
         if (isCollinear(x, y, pointsX[i-1], pointsY[i-1], pointsX[i], pointsY[i]))
         {
             /* Case 1: the segment is vertical */
            if (pointsX[i-1] == pointsX[i])
                if ( (pointsY[i-1] <= y && y <= pointsY[i]) || (pointsY[i] <= y && y <= pointsY[i-1]) )
                  return true;
             /* Case 2: the segment is NOT vertical */
            else
                if ( (pointsX[i-1] <= x && x <= pointsX[i]) || (pointsX[i] <= x && x <= pointsX[i-1]) )
                  return true;
         }

        return false;
    }


    private boolean Intersect(double pointsX, double pointsY, double x2, double y2, double pointsX2, double pointsY2, double pointsX3, double pointsY3)  {
      // detects if line segments S12 (x1, y1) -> (x2, y2) & S34 (x3, y3) -> (x4, y4) intersect
        // ATTENTION:  accuracy matters to calculation of slopes, and to solving equations

        double  x12min = Math.min(pointsX, x2),    x12max = Math.max(pointsX, x2);
        double  x34min = Math.min(pointsX2, pointsX3),    x34max = Math.max(pointsX2, pointsX3);
        double  y12min = Math.min(pointsY, y2),    y12max = Math.max(pointsY, y2);
        double  y34min = Math.min(pointsY2, pointsY3),    y34max = Math.max(pointsY2, pointsY3);

        double y, m12, b12, m34, b34;


        // S12 and S34 are vertical
        if (pointsX == x2 && pointsX2 == pointsX3)
            if (pointsX == pointsX2 && y12min <= y34max && y34min <= y12max)
               return true;

        // only S12 is vertical
        if (pointsX == x2 && pointsX2 != pointsX3)
        {
         m34 = (double)(pointsY3 - pointsY2) / (double)(pointsX3 - pointsX2);
         b34 = pointsY2 - m34*pointsX2;
         y = m34 * pointsX + b34;
            if ((x34min <= pointsX && pointsX <= x34max) && (y12min <= y && y <= y12max))
               return true;
        }

        // only S34 is vertical
        if (pointsX != x2 && pointsX2 == pointsX3)
        {
         m12 = (double)(y2 - pointsY) / (double)(x2 - pointsX);
         b12 = pointsY - m12*pointsX;
         y = m12 * pointsX2 + b12;
            if ((x12min <= pointsX2 && pointsX2 <= x12max) && (y34min <= y && y <= y34max))
               return true;
        }

        // neither of S12 & S34 is vertical
        if (pointsX != x2 && pointsX2 != pointsX3)
        {
         m12 = (double)(y2 - pointsY) / (double)(x2 - pointsX);
         b12 = pointsY - m12*pointsX;
         m34 = (double)(pointsY3 - pointsY2) / (double)(pointsX3 - pointsX2);
         b34 = pointsY2 - m34*pointsX2;

         // they have equal slopes
         if (m12 == m34)
                if (b12 == b34 && x12min <= x34max && x34min <= x12max)
                  return true;

            // they have different slopes
            if (m12 != m34)
                if (x12min <= x34max && x34min <= x12max);
                {
                	double x_highmin = Math.max(x12min, x34min);
                	double x_lowmax  = Math.min(x12max, x34max);
                	double x = (b34 - b12) / (m12 - m34);   // the x value of the intersection point
                	if (x_highmin <= x && x <= x_lowmax)
                		return true;
                }
        }

        return false;

    }

    private boolean InvalidVertex(double x, double y, double[] pointsX, double[] pointsY, int n)  {

    /* the coordinates of vertices 0 ~ n of the polygon are already stored by X and Y */

    // (1) check the invalidness of segment (X[n], Y[n]) -> (x, y)

        /* Case 1: (X[n-1], Y[n-1]), (X[n], Y[n]), and (x, y) are collinear */
        if ( isCollinear(pointsX[n-1], pointsY[n-1], pointsX[n], pointsY[n], x, y) )    return true;

        /* Case 2: (X[n], Y[n]) -> (x, y) passes thru one of vertices 0 ~ n - 1 of the polygon */
        if (passVertex(pointsX[n], pointsY[n], x, y, pointsX, pointsY, 0, n - 1))       return true;

        /* Case 3: (x, y) falls in one of the first n - 1 segments of the polygon */
        for(int i = 0; i < pointsX.length; i++) {
        	if(x == pointsX[i] && y == pointsY[i]) {
        		return true;
        	}
        }
        if (inSegment(x, y, pointsX, pointsY, n - 1))       return true;

      /* Case 4: (X[n], Y[n]) -> (x, y) intersects and overlaps (X[i-1], Y[i-1]) -> (X[i], Y[i]) */
        for (int i = n - 1; i > 0; i --)
         if ( Intersect(pointsX[n], pointsY[n], x, y, pointsX[i-1], pointsY[i-1], pointsX[i], pointsY[i]) )
            return true;

    // (2) check the invalidness of segment (x, y) -> (X[0], Y[0])

        /* Case 1: (X[n], Y[n]), (x, y), and (X[0], Y[0]) are collinear */
        if ( isCollinear(pointsX[n], pointsY[n], x, y, pointsX[0], pointsY[0]) )    return true;

        /* Case 2: (x, y) -> (X[0], Y[0]) passes thru one of vertices 1 ~ n of the polygon */
        if (passVertex(x, y, pointsX[0], pointsY[0], pointsX, pointsY, 1, n))       return true;

      /* Case 3: (x, y) -> (X[0], Y[0]) intersects and overlaps (X[i-1], Y[i-1]) -> (X[i], Y[i]) */
        for (int i = n; i > 0; i --)
         if ( Intersect(x, y, pointsX[0], pointsY[0], pointsX[i-1], pointsY[i-1], pointsX[i], pointsY[i]) )
            return true;


        return false;
    }
}