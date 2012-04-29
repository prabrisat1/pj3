/* WUGraph.java */

package graph;
import hw5.list.*;
import dict.*;
import java.util.Hashtable;

/**
 * The WUGraph class represents a weighted, undirected graph. Self-edges are
 * permitted.
 */

public class WUGraph {
	//set of stored values
    private DList vertexList;
    private int numberOfVertexes;//grammar? nah, no vertices here
    private HashTableChained edgeHashTable;
    private HashTableChained vertexHashTable;
    private int numberOfEdges;
    
    /**
     * WUGraph() constructs a graph having no vertices or edges.
     *
     * Running time: O(1).
     */
  
    public WUGraph() {
	//everything starts out empty
	edgeHashTable = new HashTableChained();
	vertexHashTable = new HashTableChained();
	vertexList = new DList();
	numberOfEdges = 0;
	numberOfVertexes = 0;
    }

    /**
     * vertexCount() returns the number of vertices in the graph.
     *
     * Running time: O(1).
     */
    public int vertexCount(){
	return numberOfVertexes;
    }

    /**
     * edgeCount() returns the number of edges in the graph.
     *
     * Running time: O(1).
     */

    public int edgeCount() {
	return numberOfEdges;
    }


    /**
     * getVertices() returns an array containing all the objects that serve
     * as vertices of the graph. The array's length is exactly equal to the
     * number of vertices. If the graph has no vertices, the array has length
     * zero.
     *
     * (NOTE: Do not return any internal data structure you use to represent
     * vertices! Return only the same objects that were provided by the
     * calling application in calls to addVertex().)
     *
     * Running time: O(|V|).
     */
    public Object[] getVertices(){
	Object[] result;
	//creates an array with enough spaces to fit all the vertexes
	result = new Object[numberOfVertexes];
	//go through vertexList to get all the vertices
	try{
		//iteratres through the vertex DList adding each item into the returned array
	    DListNode tracker = (DListNode)vertexList.front();
	    for(int n = 0; n<numberOfVertexes; n++){
		result[n] = tracker.item();
		tracker = (DListNode)tracker.next();
	    }
	}catch(InvalidNodeException a){
	    System.err.println(a);
	}
	return result;
    }

    /**
     * addVertex() adds a vertex (with no incident edges) to the graph. The
     * vertex's "name" is the object provided as the parameter "vertex".
     * If this object is already a vertex of the graph, the graph is unchanged.
     *
     * Running time: O(1).
     */
    public void addVertex(Object vertex){
    //only does things if the vertex isnt already in the hash table
	if(vertexHashTable.find(vertex) == null) {
	    InternalVertex iVertex = new InternalVertex(vertex);
	    Entry a = vertexHashTable.insert(vertex, iVertex);
	    //a is never used, only there because the hashtable insert method returns an entry. might be unnecessary.
	    numberOfVertexes++;
	    //inserts vertex into the vertex DList for getvertices
	    vertexList.insertBack(vertex); 
	}
    }
    
    /**
     * removeVertex() removes a vertex from the graph. All edges incident on the
     * deleted vertex are removed as well. If the parameter "vertex" does not
     * represent a vertex of the graph, the graph is unchanged.
     *
     * Running time: O(d), where d is the degree of "vertex".
     */
    public void removeVertex(Object vertex){
    //checks to see if the vertex is in the hash table
	Entry a = vertexHashTable.find(vertex);
	if(a != null){
		//change counter for number of vertexes
	    numberOfVertexes--;
    
	    //removing vertex from vertexList
	    DListNode tracker = (DListNode) vertexList.front();
	    try {
		while(tracker.isValidNode()){
		    DListNode temp = (DListNode)tracker.next();
		    
		    if((tracker.item()).equals(vertex)){
			tracker.remove();
		    }
		    tracker = temp;
		}
	    }catch(InvalidNodeException error){
		System.err.println(error);
		System.err.println(error + "llamas"); //LLAMAS
	    }
	    
    
	    //removing edges from adjacency lists
	    if(a.value() != null){
		InternalVertex iVertex = (InternalVertex) a.value();
		DList adjacencyList = iVertex.getAdjacencyList();
		DListNode edgeTracker = (DListNode) adjacencyList.front();
		try{
		    while(edgeTracker.isValidNode()){
			DListNode temp = (DListNode)edgeTracker.next();
			Edge e = (Edge) edgeTracker.item();
			removeEdge(e.getStart(),e.getEnd());

			edgeTracker = temp;
		    }
		}
		catch(InvalidNodeException ex) {
		    System.err.println(ex);
		}
	    }
	    
	}
<<<<<<< HEAD
	//remove the vertex from the hash table
=======
	//finally remove it from the hash table
>>>>>>> 65227c0b365396e3a2f97bd802752938c22c29d9
	vertexHashTable.remove(vertex);
    }

    /**
     * isVertex() returns true if the parameter "vertex" represents a vertex of
     * the graph.
     *
     * Running time: O(1).
     */
    public boolean isVertex(Object vertex){
    //checks if the vertex is in the hash table
	return vertexHashTable.find(vertex) != null;
    }
    /**
     * degree() returns the degree of a vertex. Self-edges add only one to the
     * degree of a vertex. If the parameter "vertex" doesn't represent a vertex
     * of the graph, zero is returned.
     *
     * Running time: O(1).
     */
    public int degree(Object vertex){
<<<<<<< HEAD
    //finds the vertex in the hash table
=======
	//returns the size of its adjacencyList
>>>>>>> 65227c0b365396e3a2f97bd802752938c22c29d9
	Entry a = vertexHashTable.find(vertex);
	int result = 0;
	//returns the size of the adjacency list(the degree) of the vertex
	if(a != null){
	    InternalVertex iv = (InternalVertex) a.value();
	    result = iv.getAdjacencyListSize();
	}
	return result;
    }
    /**
     * getNeighbors() returns a new Neighbors object referencing two arrays. The
     * Neighbors.neighborList array contains each object that is connected to the
     * input object by an edge. The Neighbors.weightList array contains the
     * weights of the corresponding edges. The length of both arrays is equal to
     * the number of edges incident on the input vertex. If the vertex has
     * degree zero, or if the parameter "vertex" does not represent a vertex of
     * the graph, null is returned (instead of a Neighbors object).
     *
     * The returned Neighbors object, and the two arrays, are both newly created.
     * No previously existing Neighbors object or array is changed.
     *
     * (NOTE: In the neighborList array, do not return any internal data
     * structure you use to represent vertices! Return only the same objects
     * that were provided by the calling application in calls to addVertex().)
     *
     * Running time: O(d), where d is the degree of "vertex".
     */
    public Neighbors getNeighbors(Object vertex){
<<<<<<< HEAD
    //finds the vertex in the hash table
=======
	//goes through adjacencyList to find neighbors
>>>>>>> 65227c0b365396e3a2f97bd802752938c22c29d9
	Entry a = vertexHashTable.find(vertex);
	InternalVertex iv = (InternalVertex) a.value();
	DList adjacencyList = iv.getAdjacencyList();
	if(iv.getAdjacencyListSize() == 0) { //nothing adjacent, no neighbors
	    return null;
	}
	//keep track of what edge we're looking at
	DListNode edgeTracker = (DListNode) adjacencyList.front();
	Object[] neighborList = new Object[iv.getAdjacencyListSize()];
	int[] weightList = new int[iv.getAdjacencyListSize()];
	int n = 0;
	try {
		//loops through the adjacency list adding in each vertex which is connected by an edge to the inputted vertex
	    while(edgeTracker.isValidNode()) {
		DListNode temp = (DListNode)edgeTracker.next();
		Edge edge1 = (Edge) edgeTracker.item();
		neighborList[n] = edge1.getEnd(); //the end vertex is it's neighbor, the start vertex is itself (self edges work, because both are itself)
		VertexPair vpair = new VertexPair(edge1.getStart(), edge1.getEnd());
		Entry b = edgeHashTable.find(vpair);
		int weight1 = ((Edge)b.value()).getWeight();
		weightList[n] = weight1;

		edgeTracker = temp;
		n++;
	    }
	}catch(InvalidNodeException error){
	}
	Neighbors result = new Neighbors();
	result.neighborList = neighborList;
	result.weightList = weightList;
	return result;
    }
    /**
     * addEdge() adds an edge (u, v) to the graph. If either of the parameters
     * u and v does not represent a vertex of the graph, the graph is unchanged.
     * The edge is assigned a weight of "weight". If the edge is already
     1     * contained in the graph, the weight is updated to reflect the new value.
     * Self-edges (where u == v) are allowed.
     *
     * Running time: O(1).
     */

    public void addEdge(Object u, Object v, int weight) {
	//first make sure the vertices exist
	if(vertexHashTable.find(u) != null && vertexHashTable.find(v) != null) {
	    VertexPair vPair = new VertexPair(u,v);
	    //remove the old edge entry and store it so we can update the weight and reinsert it
	    Entry old = edgeHashTable.remove(vPair); 
	    //make new edges, will be overwritten if there was an old edge
	    Edge edge1 = new Edge(u,v,weight);
	    Edge edge2 = new Edge(v,u,weight);
	    edge1.changeHalfEdge(edge2);
	    edge2.changeHalfEdge(edge1);
	    if(old != null) {//if the old edge existed
		//it was removed so --
		numberOfEdges--;
		edge1 = (Edge)old.value();//make the new edge = old edge
		try {
		    if(edge1.node != null) {
			//if it was in an adjacency list, remove it from it, will be reinserted later
			edge1.node.remove();
		    }
		    if(edge1.getHalfEdge().node != null) {
			//also remove its halfedge counterpart from its adjacency list
			edge1.getHalfEdge().node.remove();
		    }
		}
		catch(InvalidNodeException e) {
		    System.err.println(e + "penguins"); //PENGUINS
		}
		edge1.weight = weight; //update the weight
	    }

	    //update adjacency list of the vertices
	    Entry uEntry = vertexHashTable.remove(u);
	    Entry vEntry = vertexHashTable.remove(v);
	    InternalVertex uIV = new InternalVertex(u);
	    InternalVertex vIV = new InternalVertex(v);

	    if(uEntry != null) {
		uIV = (InternalVertex)uEntry.value();
	    }
	    //insert the new edge with the updated weight
	    uIV.adjacencyListInsert(edge1);
	    vertexHashTable.insert(u,uIV);

	    if(vEntry != null) {
		vIV = (InternalVertex)vEntry.value();
	    }
	    if(!u.equals(v)) {
		//if it wasn't a self edge, do the same for its halfedge
		//it it was a self edge, it should already be in there
		//because u and v are the same vertex
		vIV.adjacencyListInsert(edge2);
		vertexHashTable.insert(v,vIV);
	    }
	    
	    //reinsert it with new value
	    edgeHashTable.insert(vPair,edge1);
	    numberOfEdges++;
	}
    }

    /**
     * removeEdge() removes an edge (u, v) from the graph. If either of the
     * parameters u and v does not represent a vertex of the graph, the graph
     * is unchanged. If (u, v) is not an edge of the graph, the graph is
     * unchanged.
     *
     * Running time: O(1).
     */

    public void removeEdge(Object u, Object v) {
	//make sure vertices exist
	if(vertexHashTable.find(u) != null && vertexHashTable.find(v) != null) {
	    VertexPair vPair = new VertexPair(u,v);
	    //remove the edge from the hash table, store it so we can remove it from adjacency lists as well
	    Entry old = edgeHashTable.remove(vPair);
	    Edge oldEdge = null;
	    if(old != null) {
		//if it existed, it was removed
		numberOfEdges--;
		//and it exists so no null pointers here
		oldEdge = (Edge)old.value();
	    }
	    try {
		if(oldEdge != null && oldEdge.node != null) {
		    //if it exists and was in an adjacency list remove it
		    if(oldEdge.node.isValidNode()) { //if it's valid of course
			oldEdge.node.remove();
		    }
		}
		if(oldEdge != null && oldEdge.getHalfEdge() != null && oldEdge.getHalfEdge().node != null) {
		    //same thing for the half edge
		    if(oldEdge.getHalfEdge().node.isValidNode()) {
			oldEdge.getHalfEdge().node.remove();
		    }
		}
	    }
	    catch(InvalidNodeException e) {
		System.err.println(e + "turtles"); //TURTLES
	    }
	}
    }

    /**
     * isEdge() returns true if (u, v) is an edge of the graph. Returns false
     * if (u, v) is not an edge (including the case where either of the
     * parameters u and v does not represent a vertex of the graph).
     *
     * Running time: O(1).
     */

    public boolean isEdge(Object u, Object v) {
	VertexPair vPair = new VertexPair(u,v);
	return edgeHashTable.find(vPair) != null;
    }
    /**
     * weight() returns the weight of (u, v). Returns zero if (u, v) is not
     * an edge (including the case where either of the parameters u and v does
     * not represent a vertex of the graph).
     *
     * (NOTE: A well-behaved application should try to avoid calling this
     * method for an edge that is not in the graph, and should certainly not
     * treat the result as if it actually represents an edge with weight zero.
     * However, some sort of default response is necessary for missing edges,
     * so we return zero. An exception would be more appropriate, but
     * also more annoying.)
     *
     * Running time: O(1).
     */

    public int weight(Object u, Object v) {
	VertexPair vPair = new VertexPair(u,v);
	Entry entry = edgeHashTable.find(vPair);
	//return null if it doesn't exist
	if(entry == null || entry.value() == null) {
	    return 0;
	}
	//otherwise return the weight
	else {
	    return ((Edge)entry.value()).getWeight();
	}
    }
}