package glib.math.GraphTheory;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

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
