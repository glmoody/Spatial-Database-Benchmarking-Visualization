/*
 * Spatial Data Previewer v0.5 - Point
 * Authored by Gregory Lucas Moody
 * Class for Spatial Data parsed from WKT
 */
class SpatialData{
  ArrayList<Point> points;  //List of points making up the data
  String id; //abritary id for use in visualizer
  String type; //type of spatial data - LineString, Polygon, etc
  
  /* Constructor
   * id     - needs to call id creation function
   * type   - takes the type from inputed string
   * points - reads in parsed floats and takes pairs to form coords
   */
  public SpatialData(String[] spData){
    id = "0";
    type = spData[0];
    points = new ArrayList<Point>();
    for(int i = 1; i < spData.length; i = i+2){
        points.add(new Point(float(spData[i]), float(spData[i+1]))); //<>//
    }
  }
  
  /* Returns a string repeseting the list of points
   * Mostly just a test function to make sure points are correct
   */
  public String stringPoints(){
     String strPnt = "(";
     for(int i = 0; i < this.points.size(); i++){
         if(i != this.points.size()-1){
           strPnt += this.points.get(i).x + " " + this.points.get(i).y + ", ";
         } else {
           strPnt +=  this.points.get(i).x + " " + this.points.get(i).y;
         }
     }
     strPnt += ")";
     return strPnt;
  }
  
  /*
   * Returns TYPE (POINT[0], POINT[1], POINT[2], ...)
   */
  public String toString(){
   return this.type + " " + this.stringPoints();
  }
}