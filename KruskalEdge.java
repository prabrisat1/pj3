
import java.util.Hashtable;
import graph.*;
import set.*;

/**
 *  An object created to store edges as part of Kruskal's algorithm.
 *  Stores the two vertices and the weight.
 */

public class KruskalEdge implements Comparable{

    protected Object vert1; // Vertex 1
    protected Object vert2; // Vertex 2
    protected int weight; // The weight of the edge

    public KruskalEdge(Object v1, Object v2, int w){
	vert1 = v1;
	vert2 = v2;
	weight = w;
    }

    public int compareTo(Object obj){
	if(!(obj instanceof KruskalEdge)){
	    System.err.println("This isn't a KruskalEdge, what's wrong with you?!");
	}
	return this.weight - ((KruskalEdge) obj).weight;

    }
}
