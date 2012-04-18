package graph;
import hw5.list.*;

public class InternalVertex {
	//Stored Variables
	private Object vertex;
	private DList adjacencyList;
	
	//constructor 
	public InternalVertex(Object a){
		vertex = a;
		adjacencyList = new DList();
	}
	
	//returns the adjacencylist
	public DList getAdjacencyList(){
		return adjacencyList;
	}
	
	//return the vertex
	public Object getVertex(){
		return vertex;
	}
	
	//returns the size of the adjacencylist(the degree of the vertex)
	public int getAdjacencyListSize(){
		return adjacencyList.getSize();
	}
}
