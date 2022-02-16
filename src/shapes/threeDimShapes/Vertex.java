/**
 * Date: October 13, 2021
 * Description: A container for three-dimensional coordinates
 * @author Matt
 * 
 */

package shapes.threeDimShapes;

public class Vertex implements Cloneable
{
	public double[] vertex;
	
	// Method constructors
	public Vertex(double x, double y, double z)
	{
		vertex = new double[] {x, y, z};
	}
	public Vertex(double... vertex)
	{
		if (vertex.length == 3)
		{
			this.vertex = vertex;
		}
		else
		{
			throw new IllegalArgumentException("vertex length != 3");
		}
	}
	
	// Get vertex
	public double[] getVertex()
	{
		return vertex;
	}
	
	// Get vertex coordinates
	public double getX()
	{
		return vertex[0];
	}
	public double getY()
	{
		return vertex[1];
	}
	public double getZ()
	{
		return vertex[2];
	}
	public double get(int i)
	{
		return vertex[i];
	}
	
	// Set vertex coordinates
	public void setX(double x)
	{
		vertex[0] = x;
	}
	public void setY(double y)
	{
		vertex[1] = y;
	}
	public void setZ(double z)
	{
		vertex[2] = z;
	}
	public void set(int i, double coord)
	{
		vertex[i] = coord;
	}
	
	// Get vertex size
	public int size()
	{
		return vertex.length; // Should always return 3
	}
	
	// Scale vertex coordinates
	public void scale(double... s)
	{
		for (int i = 0; i < s.length; i++)
        	vertex[i] *= s[i];
	}
	
	// Clone vertex
	@Override
	public Vertex clone()
	{
		return new Vertex(vertex.clone());
	}
}
