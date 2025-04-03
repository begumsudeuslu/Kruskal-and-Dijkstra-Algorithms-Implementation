import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class FastestRoute {

    /**
     * This method try to find shortest roads with using priority queue.
     * @param graph   type of Graph class to search.
     * @param start   string type of the start point.
     * @param end     string type of the end point.
     * @return
     */
    public static List<Road> calculateFastestRoute(Graph graph, String start, String end)  {
        
        Map<String, Integer> distances = new HashMap<>();     // Store the shortest distance to each point from the start as map.
        Map<String, Road> previousRoads = new HashMap<>();     //Store the road leading to each point as map.
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));    // queue in order of their current known shortest distance as map. 
        Set<String> visited = new HashSet<>();     // Strore the visited points as set.

        distances.put(start, 0);    // start with the beginning point
        queue.add(start);       // add start point to the queue

        while(!queue.isEmpty())  {        // The loop runs until there are no more points to explore.
           
            String current = queue.poll();      // Take and remove th epoint with the shortest known distance.
            
            if(visited.contains(current)) continue;       // If it is already visited, skip the point.
            
            visited.add(current);         // add this point as visited.

            if (current.equals(end))  break;        // If you reach the goal, finish the loop. 

            List<Road> roads = graph.getRoads(current);         // Take the list of the roads from the current point.
            roads.sort(Comparator.comparingInt(Road::getDistance).thenComparingInt(Road::getId));       // Sort the roads by distance. If distances are equal, sorth the these roads by id.

            for (Road road: roads)  {
                String next = road.getPointB();       // get the destination point
                int newDistance = distances.get(current) + road.getDistance();       //  calculate these two distance and get the new distance.

                if(!distances.containsKey(next) || newDistance < distances.get(next))   {      // if a shortest path is found
                    distances.put(next, newDistance);           // set the shortest distance to the destination point.
                    previousRoads.put(next, road);            // records the road leading to the destination point.
                    queue.add(next);                        // adds the destination point to the queue.
                }
            }
        }

        List<Road> path = new ArrayList<>();       // initialize the path for shortest path.
        String step = end;        // start from destination point.

        while (previousRoads.containsKey(step))  {      // trace the path back to the start point.
            Road road = previousRoads.get(step);       // gets the road leading to the current point.
            path.add(road);                         // adds the road to the path.
            step = road.getPointA();                  // move the previous point on the path.
        }

        Collections.reverse(path);         // reverse the path to start from the start point
        return path;       // return the constructed path.
    }

    /**
     * This method calculates the total distance of the given list of the roads as parameter. 
     * @param roads     type of list of roads forming a path.
     * @return       is integer as total distance.       
     */
    public static int getTotalDistance(List<Road> roads)  {
        int total = 0;
        for(Road road: roads)  {
            total += road.getDistance();      // add the distance of the road to the total distance.
        }
        return total;     // return total distance
    }

}
