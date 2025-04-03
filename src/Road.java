public class Road {
    int distance;
    int id;
    String pointA;
    String pointB;
    String original;

    // This is constructor of the Road class, there are several information as parameters.
    public Road(String pointA, String pointB, int distance, int id, String original)  {
        this.pointA = pointA;
        this.pointB = pointB;
        this.distance = distance;
        this.id = id;
        this.original = original;
    }

    /**
     * get method of pointA
     * @return    String type of pointA
     */
    public String getPointA()   {
        return pointA;
    }

    /**
     * set method of pointA
     * @param pointA    String type of pointB 
     */
    public void setPointA(String pointA)  {
        this.pointA = pointA;
    }

    /**
     * get method of pointB
     * @return    String type of pointB
     */
    public String getPointB()   {
        return pointB;
    }

    /**
     * set method of pointB
     * @param pointB      String type of pointB
     */
    public void setPointB(String pointB)  {
        this.pointB = pointB;
    }

    /**
     * get method of distance
     * @return      integer type of the road
     */
    public int getDistance()   {
        return distance;
    }

    /**
     * set method of distance
     * @param distance     String type of distance of the road
     */
    public void setDistance(int distance)  {
        this.distance = distance;
    }

    /**
     * get method of id
     * @return       integer type of the id of the road
     */
    public int getId()   {
        return id;
    }

    /**
     * set method of the id
     * @param id     integer type of the id of the road
     */
    public void setId(int id)  {
        this.id = id;
    }

    /**
     * get method of the original input line
     * @return     String type of the line of the road
     */
    public String getOriginal()  {
        return original;
    }

    /**
     * set method of the original input line
     * @param original     String type of the line of the road
     */
    public void setOriginal(String original)    {
        this.original = original;
    }
}


// In this project,  we didn't use the set methods caouse of the not necessary but i wanna write anyway.