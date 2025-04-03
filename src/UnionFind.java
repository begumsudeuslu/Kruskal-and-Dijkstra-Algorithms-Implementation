import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parentMap = new HashMap<>();   // This map stores the parent of each roads.
    private final Map<String, Integer> rankMap = new HashMap<>();    // This map stores the rank of each roads.

    /**
     *  This method add string parameter to the parent as own and also add rank to 0.
     * @param s   type of String which is the string parameter to be added.
     */
    public void add(String s)   {
        parentMap.put(s,s);
        rankMap.put(s,0);
    }

    /**
     * This method try find the root of the set containing string parameters with roads as recursively.
     * @param s   type of String parameter is the elements whose set root is to be found.
     * @return   type is String root of the road.
     */
    public String find(String s)   {
        if(!parentMap.get(s).equals(s))  {    // check whether s is its parent or not.
            parentMap.put(s, find(parentMap.get(s)));    // if s is not own parent, try to find own parent as recursively.
        }
        return parentMap.get(s);     // if s is own parent return root of set or othersay road.
    }

   /**
    *  This method try to merges the sets containing s1 and s2 paramteres attaching the smaller tree under the root of the larger tree.
    * @param s1   type of String parameters of set are to be united or othersay merged.
    * @param s2   type of other String parameters of set are to be united or othersay merged.
    */
    public void union(String s1, String s2)    {
        String root1 = find(s1);        // take root of of the set cantainning s1
        String root2 = find(s2);        // take root of of the set cantainning s2

        if(!root1.equals(root2))   {       // Check whatever the s1 and s2 are different sets. If they are already in the same set, we don't have to do anything.
            int rank1 = rankMap.get(root1);         // Take rank of the root
            int rank2 = rankMap.get(root2);         // Take rank of the root

            if(rank1>rank2)   {
                parentMap.put(root2, root1);      // Attaches the root of the lower rank tree to the root of the higher ranked.
           
            }  else if (rank1 < rank2)  {         // Attaches the root of the higher rank tree to the root of the lower ranked.
                parentMap.put(root1, root2);
                
            }  else  {              // If both roots have the same rank,        
                parentMap.put(root2, root1);    // attach one tree to the other.
                rankMap.put(root1, rank1 + 1);  // increment the rank of the new root.
            }
        }
    }
}
