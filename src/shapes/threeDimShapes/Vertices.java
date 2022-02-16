/**
 * Date: June 26, 2021
 * Description: A class for syntactical sugar of a shape's vertices
 * @author Matt
 * 
 */

package shapes.threeDimShapes;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

// TODO Proper method documentation
public class Vertices extends AbstractList<Vertex> implements Cloneable
{
	public List<Vertex> vertices;
	
	// Default method constructor
	public Vertices()
	{
		vertices = new ArrayList<Vertex>();
	}
	
	// Method constructor
	public Vertices(int i)
	{
		vertices = new ArrayList<Vertex>(i);
	}
	
	// Method constructor
	public Vertices(Vertex... v)
	{
		this();
		
		// Making list mutable
		vertices.addAll(Arrays.asList(v));
	}
	
	// Method constructor
	public Vertices(Collection<Vertex> v)
	{
		vertices = (List<Vertex>) v;
	}
	
	// Method constructor
	public Vertices(double[][] vts)
	{
		this();
		
		for (double[] v : vts)
			vertices.add(new Vertex(v));
	}
	
	// Method constructor
	public Vertices(Vertex vertex)
	{
		this();
		
		vertices.add(vertex);
	}
	
	// Get vertex
	public Vertex get(int i)
	{
		return vertices.get(i);
	}
	
	// Add all vertices
	@Override
	public boolean add(Vertex v)
	{
		return vertices.add(v);
	}
	
	// Add all vertices
	public boolean addAll(Vertices v)
	{
		return vertices.addAll(v.vertices);
	}
	
	// Scale vertices
	public void scale(double scalar)
    {
        for (Vertex vertex : vertices)
        	vertex.scale(scalar, scalar, scalar);
    }
	
	@Override
	public Vertices clone()
	{
		Vertices out = new Vertices();
		
		out.addAll(vertices);
		
		return out;
		
//		return new Vertices(new ArrayList<Vertex>(vertices));
	}
	
	@Override
	public int size()
	{
		return vertices.size();
	}
}
