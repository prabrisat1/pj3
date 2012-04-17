package graph;

/**
 *  An object created to store edges as part of Kruskal's algorithm.
 *  Stores the two vertices and the weight.
 */

public class Edge {

  public Object vert1; // Vertex 1
  public Object vert2; // Vertex 2
  public int weight; // The weight of the edge
  public Edge halfEdge; //the edge that is paired with this one except opposite

  public Edge(Object v1, Object v2, int w){
      vert1 = v1;
      vert2 = v2;
      weight = w;
  }

  public void changeHalfEdge(Edge a){
	  halfEdge = a;
  }
  
  public int getWeight(){
	  	return weight;
  }
  
  public Object getStart(){
	  return vert1;
  }
  
  public Object getEnd(){
	  return vert2;
  }
  
  public Edge getHalfEdge(){
	  return halfEdge;
  }
  
  public boolean isSelfEdge(){
	  return this == this.halfEdge;
  }
}
