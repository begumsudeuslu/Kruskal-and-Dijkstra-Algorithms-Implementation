import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MapAnalyzer {
    public static void main(String[] args)  {
        
        int line = 0;       // initialize the line count
        String start = null;        // initialize the start point
        String end = null;          // initialize the end point
        Graph graph = new Graph();       // create an object as graph

        String[] inputLines = FileInput.readFile(args[0], true, true);       // take input line list from the input txt with help of readFile method in FileInput class.

        for (String inputLine : inputLines) {           // do that for every input line
            line++;
            if (line == 1)  {               // first line have start and end point information.
                String[] information = inputLine.split("\t");
                start = information[0];         // first is start point
                end = information[1];           // second is end point
            
            } else  {
                String[] information = inputLine.split("\t");
                String pointA = information[0];           // first is start point
                String pointB = information[1];           // second is end point
                int distance = Integer.parseInt(information[2]);         // turn distance as integer from string 
                int id = Integer.parseInt(information[3]);                // turn id as integer from string 
                graph.addRoad(pointA, pointB, distance, id, inputLine);     // add these roads with helping addRoads
            }
        }

        // Then, write the wanted information to the output file.
        try(FileWriter writer = new FileWriter(args[1]))   {        // take ouptu file name form user.
           
            List<Road> fastestRoute = FastestRoute.calculateFastestRoute(graph, start, end);          // calculate fastest route
            int totalDistance= FastestRoute.getTotalDistance(fastestRoute);                     // calculate total distance
            writer.write("Fastest Route from " + start + " to " + end + " (" + FastestRoute.getTotalDistance(fastestRoute) + " KM):\n");     // write this information
            for (Road road :fastestRoute)  {   
                writer.write(road.getOriginal());      // write using roads
                writer.write("\n");
            }
            
            List<Road> minSpanTree = BarelyConnectedMap.calculateMST(graph);       // calculate barely connected route
            Graph mstGraph = new Graph();                // create graph object
            for (Road road : minSpanTree)  {           // add each roads in the mstGraph
                mstGraph.addRoad(road.getPointA(), road.getPointB(), road.getDistance(), road.getId(), road.getOriginal());      
            }
    
            writer.write("Roads of Barely Connected Map is:\n");
            for (Road road : minSpanTree)  {                // write this information
                writer.write(road.getOriginal());
                writer.write("\n");
            }
    
            List<Road> fastestRouteMst = FastestRoute.calculateFastestRoute(mstGraph, start, end);          // calculate the fastest route from start to end on mstGraoh
            int totalDistanceMst = FastestRoute.getTotalDistance(fastestRouteMst);                      // calculate total distance on the mstGraph
            writer.write("Fastest Route from " + start + " to " + end + " on Barely Connected Map (" + totalDistanceMst + " KM):\n");        // write this information
            for ( Road road: fastestRouteMst)  {
                writer.write(road.getOriginal());            // write using roads
                writer.write("\n");
            }
    
            int totalMaterial = graph.getTotalDistance();            // calculate the total distance of graph
            int totalMaterialMst = mstGraph.getTotalDistance();           // calculate the total distance of the mstGraph
    
            double ratioFastestRoute = (double) totalDistanceMst/ totalDistance;
            double ratioConstructionMaterial = (double) totalMaterialMst/totalMaterial;
    
            // write analysis as given and calculation information.
            writer.write("Analysis:\n");
            writer.write("Ratio of Construction Material Usage Between Barely Connected and Original Map: " + String.format("%.2f", ratioConstructionMaterial) + "\n");
            writer.write("Ratio of Fastest Route Between Barely Connected and Original Map: " + String.format("%.2f", ratioFastestRoute));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
