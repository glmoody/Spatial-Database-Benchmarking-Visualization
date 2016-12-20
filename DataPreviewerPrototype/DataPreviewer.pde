/*
 * Spatial Data Previewer v0.5 - DataPreviewer
 * Authored by Gregory Lucas Moody
 * Prototype Spatial Data Previewer
 * Used for verification of Algorithms
 */
  
String[] data; //The array of strings read from inputed WKT file
SpatialData[] spatialData; //Array of spatial data parsed from data
int index = 0; //tracks which entry is being viewed

void setup() {
 size(1000, 1000); //Default size of the canvas
 //background(255); //Default Background color - White
 fill(0); //Default Drawing color - Black
 noLoop();
 
 //Loads strings from specified WKT file - each element is a WKT entry
 data = loadStrings("test.polygons.txt");
 //data = loadStrings("test.linestrings.txt");
 //data = loadStrings("test.conicSpiral.txt");
 //data = data = loadStrings("test.triangles.txt");
 //data = data = loadStrings("test.randomWalk.txt");
 spatialData = new SpatialData[data.length];
 
 //For each element of data, parse the WKT entry, and create a SpatialData Object
 for(int i = 0; i < data.length; i++){
    String[] pieces = parseWKT(data[i]);
    spatialData[i] = new SpatialData(pieces);
 }
 
}

//Draws current entry to the canvas
void draw() {
  background(255);
  Drawer preview = new Drawer();
  preview.drawSpatialData(spatialData[index]);
  text(spatialData[index].toString(), 20, 20 + 20);
}

//handles events on key presses
void mousePressed() {
  index++; 
  if (index >= spatialData.length) {
    index = 0;  // go back to the beginning
  } 
  redraw();
}
/* Parses the WKT  by triming the ")", "(", and ","s 
 * and returing the final String array
 */
String[] parseWKT(String dataWKT){
  String[] parsed = splitTokens(dataWKT, "( , ) ");
  return parsed;
}