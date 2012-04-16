
import java.util.Hashtable;
import graph.*;
import set.*;

/**
 *  An object created to store edges as part of Kruskal's algorithm.
 *  Stores the two vertices and the weight.
 */

public class Edge {

  public Object vert1; // Vertex 1
  public Object vert2; // Vertex 2
  public int weight; // The weight of the edge

  public Edge(Object v1, object v2, int w){
      vert1 = v1;
      vert2 = v2;
      weight = w;
  }


}
