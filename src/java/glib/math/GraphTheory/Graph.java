package glib.math.GraphTheory;

public class Graph
{
	private double[][] costMatrix;
	
	public Graph(double[][] costMatrix)
	{
		costMatrix = this.costMatrix;
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
	
	public double[][] getCostMatrix()
	{
		return costMatrix;
	}
	
	public boolean isConnected(int vertex1, int vertex2) throws Exception
	{
		if (vertex1 > costMatrix.length || vertex2 > costMatrix[0].length) throw new IndexOutOfBoundsException("vertices out of bounds!"); 
		if (costMatrix[vertex1-1][vertex2-1] != Double.POSITIVE_INFINITY) return true;
		return false;
	}
	
	public double getCost(int vertex1, int vertex2) throws Exception
	{
		if (vertex1 > costMatrix.length || vertex2 > costMatrix[0].length) throw new IndexOutOfBoundsException("vertices out of bounds!"); 
		return costMatrix[vertex1 - 1][vertex2 - 1];
	}
}
