/*
 * Spatial Data Previewer v0.5 - DataPreviewer
 * Authored by Gregory Lucas Moody
 * Class of methods for drawing Spatial Data to the canvas
 */
class Drawer {
  
  //Contructor
  public Drawer(){
    
  }
  
  /* THIS IS THE FUNCTION TO CALL
   * Glorified switch statement to decided with function to call
   */
  public void drawSpatialData(SpatialData data){
   String type = data.type;
   
   if(type.equals("POINT")){
     drawPoint(data.points.get(0));
   } else if(type.equals("LINESTRING")){
     drawLineString(data);
   } else if(type.equals("TRIANGLE")){
     drawTriangle(data);
   } else if(type.equals("POLYGON")){
     drawPolygon(data);
   }
   
  }
  
  //Draws a singluar point on the canvas
  public void drawPoint(Point coord){
    ellipse(coord.x, coord.y, 5, 5);
  }
  
  //Draws all the points in a list of points
  public void drawPoints(ArrayList<Point> coords){
    for(Point e : coords){
      drawPoint(e);
    }
  }
  
  //Draws a line segment between two Points
  public void drawLineSegment(Point coord1, Point coord2){
    line(coord1.x, coord1.y,
         coord2.x, coord2.y);
  }
  
  //Draws a line string data type
  public void drawLineString(SpatialData lineString){
    drawPoints(lineString.points);
    
    for(int i = 1; i < lineString.points.size(); i++){
      drawLineSegment(lineString.points.get(i-1), lineString.points.get(i)); 
    }
  }
  
  //Draws a Triangle
  public void drawTriangle(SpatialData triangle){
    drawPoints(triangle.points);
    
    Point point1 = triangle.points.get(0);
    Point point2 = triangle.points.get(1);
    Point point3 = triangle.points.get(2);
    
    drawLineSegment(point1, point2);
    drawLineSegment(point2, point3);
    drawLineSegment(point3, point1);
  }
  
  //Draws a polygon
  public void drawPolygon(SpatialData polygon){
    //Draw a lineString using the polygon
    drawLineString(polygon);
    
    //Close the gap between the first and last points
    Point pointStart = polygon.points.get(0);
    Point pointEnd = polygon.points.get(polygon.points.size()-1);
    
    drawLineSegment(pointStart, pointEnd);
  }
  
}