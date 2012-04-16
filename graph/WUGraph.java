/* WUGraph.java */

package graph;
import hw5.list.*;
import dict.*;
import java.util.Hashtable;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
<<<<<<< HEAD
	protected int vertexcount;
	protected int edgecount;
	protected DList valist;
=======

    private HashTableChained edgeHashTable;
    private HashTableChained vertexHashTable;
    private int numberOfEdges;
    
>>>>>>> 3f024bdbea9c1b357f93ae33e54626ac78cd5948
  /**
   * WUGraph() constructs a graph having no vertices or edges.
   *
   * Running time:  O(1).
   */
<<<<<<< HEAD
  public WUGraph(){
  }
  	
=======
    public WUGraph() {
	edgeHashTable = new HashTableChained();
	vertexHashTable = new HashTableChained();
	numberOfEdges = 0;

    }

>>>>>>> 3f024bdbea9c1b357f93ae33e54626ac78cd5948
  /**
   * vertexCount() returns the number of vertices in the graph.
   *
   * Running time:  O(1).
   */
  public int vertexCount(){
	  return 0;
  }

  /**
   * edgeCount() returns the number of edges in the graph.
   *
   * Running time:  O(1).
   */
<<<<<<< HEAD
  public int edgeCount(){
	  	return 0;
  }
=======
    public int edgeCount() {
	return numberOfEdges;
    }
>>>>>>> 3f024bdbea9c1b357f93ae33e54626ac78cd5948

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   *
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   *
   * Running time:  O(|V|).
   */
  public Object[] getVertices(){
	  return null;
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.  The
   * vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(1).
   */
  public void addVertex(Object vertex){
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex){
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   *
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex){
  	return false;
  }
  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   *
   * Running time:  O(1).
   */
  public int degree(Object vertex){
  		return 0;
  }
  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex){
  		return null;
  }
  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the edge is already
   * contained in the graph, the weight is updated to reflect the new value.
   * Self-edges (where u == v) are allowed.
   *
   * Running time:  O(1).
   */
<<<<<<< HEAD
  public void addEdge(Object u, Object v, int weight){
  }
  		
=======
    public void addEdge(Object u, Object v, int weight) {
	if(vertexHashTable.find(u) != null && vertexHashTable.find(v) != null) {
	    VertexPair vPair = new VertexPair(u,v);
	    edgeHashTable.insert(vPair,weight);
	    numberOfEdges++;
	}
    }

>>>>>>> 3f024bdbea9c1b357f93ae33e54626ac78cd5948
  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   *
   * Running time:  O(1).
   */
<<<<<<< HEAD
  public void removeEdge(Object u, Object v){
  }
=======
    public void removeEdge(Object u, Object v) {
	if(vertexHashTable.find(u) != null && vertexHashTable.find(v) != null) {
	    VertexPair vPair = new VertexPair(u,v);
	    edgeHashTable.remove(vPair);
	    numberOfEdges--;
	}
    }
>>>>>>> 3f024bdbea9c1b357f93ae33e54626ac78cd5948

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
<<<<<<< HEAD
  public boolean isEdge(Object u, Object v){
	  return false;
  }
=======
    public boolean isEdge(Object u, Object v) {
	VertexPair vPair = new VertexPair(u,v);
	return edgeHashTable.find(vPair) != null;
    }
>>>>>>> 3f024bdbea9c1b357f93ae33e54626ac78cd5948

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but
   * also more annoying.)
   *
   * Running time:  O(1).
   */
<<<<<<< HEAD
  public int weight(Object u, Object v){
	  return 0;
  }

=======
    public int weight(Object u, Object v) {
	VertexPair vPair = new VertexPair(u,v);
	int weight = edgeHashTable.find(vPair);
	if(weight == null) {
	    return 0;
	}
	else {
	    return weight;
	}
    }
>>>>>>> 3f024bdbea9c1b357f93ae33e54626ac78cd5948
}
