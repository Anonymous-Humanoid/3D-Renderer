/**
 * Date: February 13, 2022
 * Description: A class containing certain shape specifications
 * // TODO Update class description
 * @author Matt
 * 
 */

package shapes.threeDimShapes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import shapes.twoDimShapes.Points;

public class ThreeDimShapes implements Cloneable
{
	// Variable constructors
	Vertices vertices;
	Points edges;
	
	// Default method constructor
	public ThreeDimShapes()
	{
		vertices = new Vertices();
		edges = new Points();
	}
	
	// Method constructor
	public ThreeDimShapes(ThreeDimShape shape)
	{
		this();
		
		add(shape);
	}
	
	// Method constructor
	public ThreeDimShapes(int r1, int r2)
	{
		vertices = new Vertices(r1);
		edges = new Points(r2);
	}
	
	// Add shape
	public void add(ThreeDimShape shape)
	{
		addEdges(shape);
		addVertices(shape);
	}
	
	// Add edges
	public void addEdges(Edge edge, int size)
	{
		Points pts = new Points();
		
		switch (edge)
		{
			// Connecting all vertices
			case ALL:
		    	for (int i = 0; i < size; i++)
		    		for (int j = 0; j < size; j++)
			    		pts.add(new Point(i, j));
				
		    	break;
		    	
		    // Connecting vertices in order of appearance
			case SEQUENTIAL:
		    	for (int i = 0; i < size - 1; )
		    		pts.add(new Point(i, ++i));
		    	
		    	pts.add(new Point(size - 1, 0));
		    	
		    	break;
		    
		    default:
		    	return; // Shouldn't occur; does nothing
		}
		
		edges.addAll(pts);
	}
	
	// Add all edges
	public void addEdges(Edge edge)
	{
		addEdges(edge, vertices.size());
	}
	
	// Add shape vertices
	public void addVertices(Vertices v)
	{
		// Scaling vertices to 100% size and adding to list
		Vertices vts = v.clone();
		vts.scale(100d);
		vertices.addAll(vts);
	}
	
	// Add shape vertices
	public void addVertices(ThreeDimShape shape)
	{
		double[][] v;
		double p = (1 + Math.sqrt(5)) / 2; // Phi, the golden ratio
		
		switch (shape)
		{
			case CUBE:
				List<double[]> coords = new ArrayList<double[]>();
				
				for (int i = -1; i <= 1; i += 2)
					for (int j = -1; j <= 1; j += 2)
						for (int k = -1; k <= 1; k += 2)
							coords.add(new double[] {i, j, k});
				
				v = coords.toArray(new double[0][0]);
				
				break;
				
			case NESTED_CUBE:
				v = new double[27][3];
				
				for (int i = 0; i <= 2; i++)
					for (int j = 0; j <= 2; j++)
						for (int k = 0; k <= 2; k++)
							v[9 * i + 3 * j + k] = new double[] {i - 1, j - 1, k - 1};
				
				break;
				
			case HEXAGON:
				v = new double[][]
				{
					{0, 1, 0},
					{0.87, 0.5, 0},
					{0.87, -0.5, 0},
					{0, -1, 0},
					{-0.87, -0.5, 0},
					{-0.87, 0.5, 0}
				};
				
				break;
				
			case PYRAMID:
				v = new double[][]
				{
					{0, 1.5, 0},
					{1, -1.5, 1},
					{-1, -1.5, 1},
					{-1, -1.5, -1},
					{1, -1.5, -1}
				};
				
				break;
				
			case ICOSAHEDRON:
				v = new double[][]
				{
					{0, +1, +p},
					{0, -1, +p},
					{0, -1, -p},
					{0, +1, -p},
					{+1, +p, 0},
					{-1, +p, 0},
					{-1, -p, 0},
					{+1, -p, 0},
					{+p, 0, +1},
					{-p, 0, +1},
					{-p, 0, -1},
					{+p, 0, -1}
				};
				
				break;
				
//			case TRUNCATED_ICOSAHEDRON: // TODO Truncated icosahedron vertex coordinates
//				double pc = p * p * p;
//				v = new double[][]
//				{
//					{0, 1, 3 * p},
//					{0, 1, -3 * p},
//					{0, -1, 3 * p},
//					{0, -1, -3 * p},
//					{1, 2 + p, 2 * p},
//					{1, 2 + p, -2 * p},
//					{1, -2 - p, 2 * p},
//					{1, -2 - p, -2 * p},
//					{-1, 2 + p, 2 * p},
//					{-1, 2 + p, -2 * p},
//					{-1, -2 - p, 2 * p},
//					{-1, -2 - p, -2 * p},
//					{p, 2, pc},
//					{p, 2, -pc},
//					{p, -2, pc},
//					{p, -2, -pc},
//					{-p, 2, pc},
//					{-p, 2, -pc},
//					{-p, -2, pc},
//					{-p, -2, -pc},
//				};
//				
//				break;
				
			default: // Shouldn't occur
		    	v = new double[0][0];
		    	
		    	break;
		}
		
		Vertices vts = new Vertices(v);
		
		// Scaling vertices to 100% size
		vts.scale(100d);
		vertices.addAll(vts);
	}
	
	// Add shape edges
	public void addEdges(ThreeDimShape shape)
	{
		int[][] pts = new int[0][0];
		
		switch (shape)
		{
			case NESTED_CUBE:
				addEdges(Edge.ALL, 27);
				
				break;
				
			case CUBE:
				pts = new int[][]
				{
					{0, 1},
					{1, 3},
					{3, 2},
					{2, 0},
					{4, 5},
					{5, 7},
					{7, 6},
					{6, 4},
					{0, 4},
					{1, 5},
					{2, 6},
					{3, 7}
				};
				
				break;
				
			case HEXAGON:
				addEdges(Edge.SEQUENTIAL, 6);
				
				break;
				
			case PYRAMID:
				pts = new int[][]
				{
					{0, 1},
					{0, 2},
					{0, 3},
					{0, 4},
					{1, 2},
					{2, 3},
					{3, 4},
					{4, 1}
				};
				
				break;
				
			case ICOSAHEDRON:
				pts = new int[][]
				{
					{0, 1},
					{0, 4},
					{0, 5},
					{0, 8},
					{0, 9},
					{1, 6},
					{1, 7},
					{1, 8},
					{1, 9},
					{2, 3},
					{2, 6},
					{2, 7},
					{2, 10},
					{2, 11},
					{3, 5},
					{3, 4},
					{3, 10},
					{3, 11},
					{4, 5},
					{4, 8},
					{4, 11},
					{5, 9},
					{5, 10},
					{6, 7},
					{6, 9},
					{6, 10},
					{7, 8},
					{7, 11},
					{8, 11},
					{9, 10}
				};
				
				break;
				
			case TRUNCATED_ICOSAHEDRON:
				// TODO
				
				break;
		}
		
		// Shifting edge vectors accordingly // TODO Make a Point(s) translation method
		for (int[] edge : pts)
			for (int i = 0; i < edge.length; i++)
				edge[i] += vertices.size();
		
		// Adding edges, if any
		if (pts.length != 0) edges.addAll(new Points(pts));
	}
	
	// Rotate vertex along an axis(s) about another vertex
	public void rotate(Vertex vtx, double x, double y, Vertex pt)
	{
		// Transforming about origin
		for (int i = 0; i < vtx.size(); i++)
			vtx.getVertex()[i] -= pt.get(i);
		
		double[] sin = {Math.sin(x), Math.sin(y)};
		double[] cos = {Math.cos(x), Math.cos(y)};
		
		Vertex tmp = vtx.clone();
		
		// Rotating transformed vertex
		vtx.setX(tmp.getX() * cos[0] - tmp.getZ() * sin[0]);
		tmp.setZ(tmp.getZ() * cos[0] + tmp.getX() * sin[0]);
		vtx.setY(tmp.getY() * cos[1] - tmp.getZ() * sin[1]);
		vtx.setZ(tmp.getZ() * cos[1] + tmp.getY() * sin[1]);
		
		// Transforming vertex about pt
		for (int i = 0; i < vtx.size(); i++)
			vtx.getVertex()[i] += pt.get(i);
	}
	
	// Rotate vertex along an axis(s) about the origin
	public void rotate(Vertex vtx, double x, double y)
	{
		rotate(vtx, x, y, new Vertex(0, 0, 0));
	}
	
	// Rotate shape along an axis(s)
	public void rotate(double x, double y)
	{
		vertices.forEach(v -> rotate(v, x, y));
	}
	
	// Rotate shape along an axis(s) about another vertex
	public void rotate(double x, double y, Vertex pt)
	{
		vertices.forEach(v -> rotate(v, x, y, pt));
	}
	
	// Scale shape
	public void scale(double scalar)
	{
		vertices.scale(scalar);
	}
	
	// Translate shape
	public void translate(int... coords)
	{
		for (Vertex v : vertices)
		{
			for (int i = 0; i < coords.length; i++)
			{
				v.getVertex()[i] += coords[i];
			}
		}
	}
	
	@Override
	public ThreeDimShapes clone()
	{
		ThreeDimShapes out = new ThreeDimShapes();
		
		out.vertices = vertices.clone();
		out.edges = edges.clone();
		
		return out;
	}
}
