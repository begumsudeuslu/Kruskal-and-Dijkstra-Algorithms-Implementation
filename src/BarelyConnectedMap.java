import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BarelyConnectedMap {
    
    /**
     *  This method calculate
     * @param graph  is type of Graph class
     * @return   type is List<Road> which minimum spanning tree as a list.
     */
    public static List<Road> calculateMST(Graph graph)  {
        List<Road> allRoads = new ArrayList<>();     // initialize the allRoad to togethering all roads.

        for (List<Road> roads: graph.adjList.values())  {       // for loop gethers all the roads from the adjList of the graph.
            allRoads.addAll(roads);
        }

        // We have to sort the roads according to distances and if distance are equal, according to id cause of the trying to minimum road. 
        allRoads.sort(Comparator.comparingInt(Road::getDistance).thenComparingInt(Road::getId));

        UnionFind unionFind = new UnionFind();     // create the unionFind object
        Set<String> points = new HashSet<>(graph.adjList.keySet());   // take the keys of adjList as set.

        for (String point : points)  {    // add each points in the unionFind object.
            unionFind.add(point);
        }

        List<Road> minSpanTree = new ArrayList<>();   //initialize the minSpanTree.

        for(Road road : allRoads)  {
            String pointA = road.getPointA();   // get the pointA of the road
            String pointB = road.getPointB();   // get the pointB of the road

            if(unionFind.find(pointA).equals(unionFind.find(pointB)))  {    // If they are in the same set, skipped that road cause of the we don't wanna create cycle.  
                continue;      // skipped the road.
            }

            minSpanTree.add(road);   // If they are in different sets, add road to minSpanTree.
            unionFind.union(pointA, pointB);   // merge the sets as using union method.

            if(minSpanTree.size()==points.size()-1)  {      // this loop continue until mst have point.size()-1 roads couse of the find minimum number of roads.
                break;
            }
        }
        return minSpanTree;
    }
}
