import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    Map<String, List<Road>> adjList = new HashMap<>();        // This map represents the adjacency list of the graph.

    /**
     *  this method try to add a road between two points in the graph.
     * @param pointA    String type of the start point.    
     * @param pointB    String type of the end point.
     * @param distance     integer type of the distance of the road. 
     * @param id        integer type of the unique identifier of the road.
     * @param original        String type of the representation of the road for output.
     */
    public void addRoad(String pointA, String pointB, int distance, int id, String original)  {
        Road road = new Road(pointA, pointB, distance, id, original);         // create a road object.     
        adjList.computeIfAbsent(pointA, k-> new ArrayList<>()).add(road);           //This method checks, if the key exists in the map and add the road object to the list.
        adjList.computeIfAbsent(pointB, k-> new ArrayList<>()).add(new Road(pointB, pointA, distance, id, original));        // Also add reverse road according to the start and destination point.
    }

    /**
     * Take the list of the roads connected to a given point.
     * @param point     String type of connected roads are to be retrieved.
     * @return      list of roads associated with the given pointn. If the point doesn't exist in the map, it will return an empty list.
     */
    public List<Road> getRoads(String point)   {
        return adjList.getOrDefault(point, new ArrayList<>());
    }

    /**
     * This method calculate the total distance of all roads in the graph.
     * @return   integer of the distances of the roads.
     */
    public int getTotalDistance()  {
        int totalDistance = 0;    // initialize the total distance.

        // add all distance of the roads to the total distance
        for (List<Road> roads : adjList.values())  {
            for(Road road : roads)  {
                totalDistance += road.getDistance();
            }
        }
        return totalDistance/2;       // Each road is counted twice in the adjacency list, so total distacne is divided by 2 to get the correct sum.
    }
}
