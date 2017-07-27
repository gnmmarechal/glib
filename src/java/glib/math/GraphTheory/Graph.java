package glib.math.GraphTheory;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class Graph
{
	private static double[][] costMatrix;
	private static List<String> matrixLabels;
	
	public Graph(double[][] costMatrix, String[] matrixLabels) throws Exception
	{
		if (this.costMatrix.length != matrixLabels.length) throw new IndexOutOfBoundsException("Cost and Label Matrix size mismatch!");
		if (hasDuplicates(matrixLabels)) throw new Exception("Labels are repeated!");
		this.costMatrix = costMatrix;
		List<String> tempMatrixLabels = new ArrayList<String>();
		for (int i = 0; i < matrixLabels.length; i++)
			tempMatrixLabels.add(matrixLabels[i]);
		
		
		this.matrixLabels = tempMatrixLabels;
	}

	public Graph(double[][] costMatrix)
	{
		this.costMatrix = costMatrix;
	}
		
	public Graph(int vertices)
	{
		costMatrix = new double[vertices][vertices];
		for (int i = 0; i < costMatrix.length; i++)
		{
			for (int j = 0; j < costMatrix.length; j++)
			{
				costMatrix[i][j] = Double.POSITIVE_INFINITY;
			}
		}
	}
	
	public static double[][] getCostMatrix()
	{
		return costMatrix;
	}
	
	public static int size()
	{
		return costMatrix.length;
	}
	public static boolean isConnected(int vertex1, int vertex2) throws Exception
	{
		if (vertex1 > costMatrix.length || vertex2 > costMatrix[0].length) throw new IndexOutOfBoundsException("vertices out of bounds!"); 
		if (costMatrix[vertex1-1][vertex2-1] != Double.POSITIVE_INFINITY) return true;
		return false;
	}
	
	public static double getCost(int vertex1, int vertex2) throws Exception
	{
		if (vertex1 > costMatrix.length || vertex2 > costMatrix[0].length) throw new IndexOutOfBoundsException("vertices out of bounds!"); 
		return costMatrix[vertex1 - 1][vertex2 - 1];
	}
	
	public static int getVertexNumber(String vertexLabel) throws Exception
	{
		if (!matrixLabels.contains(vertexLabel)) throw new Exception("Label doesn't exist!");
		return matrixLabels.indexOf(vertexLabel) - 1;
	}
	
	public static String getVertexLabel(int vertexNumber) throws Exception
	{
		if (vertexNumber != costMatrix.length) throw new Exception("Vertex doesn't exist!");
		return matrixLabels.get(vertexNumber - 1);
	}
	
	public static List<Integer> getShortestPath(int vertex1, int vertex2) throws Exception
	{
		List<Integer> retList = new ArrayList<Integer>();
		double[][] costs = new double[costMatrix.length][2]; // (cost, parent vertex)
		for (int i = 1; i < costs.length; i++)
		{
			costs[i][0] = Double.POSITIVE_INFINITY;
		}
		costs[0][0] = 0;
		costs[0][1] = -1;
		// Set the distance to all vertices
		List<Integer> vertices = new ArrayList<Integer>();
		for (int i = 0; i < costMatrix.length; i++)
		{
			vertices.add(i+1);
		}
		vertices.remove(vertex1);
		int curVertex = vertex1;
		int targetVertex = vertex2;
		int nextVertex = -1;
		while(!vertices.isEmpty())
		{
			for (int i = 0; i < costMatrix.length; i++)
			{
				if (vertices.contains(i) && getCost(curVertex, i) < costs[i][0])
				{
					//Add cost to array
					costs[i][0] = getCost(curVertex, i);
					costs[i][1] = curVertex;
				}
			}
			
			
		}
		
		
		return retList;
	}
	
	public static List<String> getShortestPath(String vertex1, String vertex2) throws Exception
	{
		List<Integer> tempList = getShortestPath(getVertexNumber(vertex1), getVertexNumber(vertex2));
		List<String> retList = new ArrayList<String>();
		for (int i = 0; i < tempList.size(); i++)
		{
			retList.add(getVertexLabel(tempList.get(i)));
		}
		return retList;
	}
	private static boolean hasDuplicates(final String[] list)
	{
		Set<String> lump = new HashSet<String>();
		for (String i : list)
		{
			if (lump.contains(i)) return true;
			lump.add(i);
		}
		return false;
	}	
}

class Dijkstra {

   // Dijkstra's algorithm to find shortest path from s to all other nodes
   public static int [] dijkstra (Graph G, int s) {
      final double [] dist = new double [G.size()];  // shortest known distance from "s"
      final int [] pred = new int [G.size()];  // preceeding node in path
      final boolean [] visited = new boolean [G.size()]; // all false initially

      for (int i=0; i<dist.length; i++) {
         dist[i] = Integer.MAX_VALUE;
      }
      dist[s] = 0;

      for (int i=0; i<dist.length; i++) {
         final int next = minVertex (dist, visited);
         visited[next] = true;

         // The shortest path to next is dist[next] and via pred[next].

         final int [] n = G.neighbors (next);
         for (int j=0; j<n.length; j++) {
            final int v = n[j];
            final double d = dist[next] + G.getCost(next,v);
            if (dist[v] > d) {
               dist[v] = d;
               pred[v] = next;
            }
         }
      }
      return pred;  // (ignore pred[s]==0!)
   }

   private static int minVertex (double [] dist, boolean [] v) {
      double x = Integer.MAX_VALUE;
      int y = -1;   // graph not connected, or no unvisited vertices
      for (int i=0; i<dist.length; i++) {
         if (!v[i] && dist[i]<x) {y=i; x=dist[i];}
      }
      return y;
   }

   public static void printPath (Graph G, int [] pred, int s, int e) {
      final java.util.ArrayList path = new java.util.ArrayList();
      int x = e;
      while (x!=s) {
         path.add (0, G.getVertexLabel(x));
         x = pred[x];
      }
      path.add (0, G.getVertexLabel(s));
      System.out.println (path);
   }

}
