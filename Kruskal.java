/* Kruskal.java */

import java.util.Hashtable;
import graph.*;
import set.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   */
  public static WUGraph minSpanTree(WUGraph g){
	  return null;
  }

	// - Make new empty graph
	// - Get vertices, add them all to the new graph
	// - Use getNeighbors() to obtain all neighbors, get all edges from them
	//	-- Store edges in a list, probably a singly linked list
	// - Sort the edges with a sorting algorithm
	// - Use disjoint sets to find edges of minimum spanning tree
	//
	// Probably should read G&T's section Kruskal to get a better idea
	// of what to do
	

	// Make a new empty graph, T, for the minimum spanning tree
	WUGraph T = new WUGraph();

        // Create the list of edges from the original graph
	SList edges = new SList();	

	// Create a hash table to 


	Object[] vertices = g.getVertices();
	for(int i = 0; i < vertices.length; i++){
		// Add the vertices from the original graph into T
		T.addVertex(vertices[i]);

		// Add the edge to the list of edges
		Neighbors n = getNeighbors(vertices[i]);
		for(int j = 0; j < n.neighborList.length; j++){
			// If the edge is not already in the list,
			// add it to the list of edges
			if(     ){
				edges.insertBack(new Edge(vertices[i], n.neighborList[j], n.weightList[j]));
			}
		}

		// Mark this vertex as visited (to prevent duplicate edges
		// in the list of all of the edges)
	}



	// Sort edges


	// Use disjoint sets to find edges of minimum spanning tree
  }

  
}
