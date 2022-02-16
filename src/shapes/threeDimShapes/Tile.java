/**
 * Date: December 24, 2020
 * Description: TODO Description
 * @author Matthew
 * 
 */

package shapes.threeDimShapes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import shapes.twoDimShapes.Point;
import shapes.twoDimShapes.Points;

public class Tile extends JPanel implements Cloneable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1471796157357491924L;
	
	// Variable constructors
	List<ThreeDimShapes> shapes = new ArrayList<ThreeDimShapes>();
	Points rotations = new Points();
	Point lastMousePos = new Point(0, 0);
	Double lastScroll = 0d;
	
	// TODO Proper method documentation
	// TODO Parameter descriptions
	
	// Method constructor
	public Tile() {}
	
	// Method constructor
	public Tile(int i)
	{
		shapes = new ArrayList<ThreeDimShapes>(i);
	}
	
	// Method constructor
	public Tile(ThreeDimShapes shape, Point rot)
	{
		add(shape, new Points(rot));
	}
	
	// Method constructor
	public Tile(ThreeDimShapes shape)
	{
		add(shape);
	}
	
	// Method constructor
	public Tile(List<ThreeDimShapes> shapes, Points rots)
	{
		shapes.addAll(shapes);
		addRotations(rots);
	}
	
	// Method constructor
	public Tile(List<ThreeDimShapes> shapes)
	{
		shapes.addAll(shapes);
		addRotations(new Points());
	}
	
	/**
	 * @Description Adds a new default tile to the {@code JPanel}
	 * @param shape
	 * @param rots
	 * 
	 */
	public void add(ThreeDimShapes shape, Points rots)
	{
		shapes.add(shape);
		addRotations(rots);
	}
	
	/**
	 * @Description Adds a new default tile to the {@code JPanel}
	 * @param shape
	 * 
	 */
	public void add(ThreeDimShapes shape)
	{
		shapes.add(shape);
		addRotations(new Points());
	}
	
	/**
	 * @Description Adds a new tile to the {@code JPanel}
	 * @param shape the {@code ThreeDimShapes} to be added to this {@code Tile}
	 * @param rots the angles by which the {@code ThreeDimShapes}
	 * will rotate by along the x and y axes
	 * 
	 */
	public void addAll(List<ThreeDimShapes> shapes, Points rots)
	{
		this.shapes.addAll(shapes);
		addRotations(rots);
	}
	
	/**
	 * @Description Adds a new tile to the {@code JPanel}
	 * @param shapes the shapes to be added to this {@code Tile}
	 * 
	 */
	public void addAll(List<ThreeDimShapes> shapes)
	{
		this.shapes.addAll(shapes);
		addRotations(new Points());
	}
	
	/**
	 * @Description Rotates the {@code ThreeDimShapes} by the specified quantities
	 * @param xIndex
	 * @param yIndex
	 * @param rots the angles by which the {@code ThreeDimShapes}
	 * will rotate by along the x and y axes
	 * 
	 */
	public void rotate(Point[] rots)
	{
		// Rotating shapes accordingly
		for (int i = 0; i < shapes.size() && rots.length >= i; i++)
		{
//			Point temp = rotations.get(i).multiply(rots[i]);
//			shapes.get(i).rotate(temp.x, temp.y);
			
			shapes.get(i).rotate(rotations.get(i).x, rotations.get(i).y);
		}
	}
	
	// Adding rotation values
	protected void addRotations(Points rots)
	{
		if (rots.pts.size() < shapes.size())
		{
			// Adding default rotation points
			for (int i = rots.pts.size(); i < shapes.size(); i++)
			{
				rotations.add(new Point(0, 0));
			}
		}
		else
		{
			// Adding rotation points
			for (int i = 0; i < shapes.size(); i++)
			{
				rotations.add(rots.pts.get(i));
			}
		}
	}
	
	/**
	 * @Description Rotates the shapes according to the specified {@code Point}
	 * @param rot the x and y angles for which the shapes will rotate
	 * 
	 */
	public void rotate(Point rot)
	{
		if (rot == null)
		{
			rot = new Point(0, 0);
		}
		
		for (int i = 0; i < shapes.size(); i++)
		{
			Point temp = rotations.get(i).multiply(rot);
			shapes.get(i).rotate(temp.x, temp.y);
		}
	}
	
	/**
	 * @Description Rotates the shapes according to the specified {@code Points}
	 * @param rots the x and y angles for which
	 * each individual shape will rotate
	 * 
	 */
	public void rotate(Points rots)
	{
		if (rots == null)
		{
			rots = new Points(0, 0);
		}
		
		if (rots.pts.size() < shapes.size())
		{
			for (int i = rots.pts.size(); i < shapes.size(); i++)
			{
				rots.add(new Point(0, 0));
			}
		}
		
		for (int i = 0; i < shapes.size(); i++)
		{
//			Point temp = rotations.get(i).multiply(rots.get(i));
//			shapes.get(i).rotate(temp.x, temp.y);
			
			shapes.get(i).rotate(rots.pts.get(i).x, rots.pts.get(i).y);
		}
	}
	
	/**
	 * @Description Scales the indexed shape by the specified scalar
	 * @param index the shape's index
	 * @param scalar the shape scalar
	 * 
	 */
	public void scale(int index, double scalar)
	{
		shapes.get(index).vertices.scale(scalar);
	}
	
	/**
	 * @Description Scales all shapes by the specified scalar
	 * @param scalar the shapes' scalar
	 * 
	 */
	public void scale(double scalar)
	{
		shapes.forEach(s ->
		{
			s.vertices.scale(scalar);
		});
	}
	
	// Clone Tile
	@Override
	public Tile clone()
	{
		List<ThreeDimShapes> shapes = new ArrayList<ThreeDimShapes>();
		
		// Cloning shapes
		this.shapes.forEach(s ->
		{
			shapes.add(s.clone());
		});
		
		Tile out = new Tile(shapes, rotations.clone());
		out.lastMousePos = lastMousePos.clone();
		out.lastScroll = (double) lastScroll;
		
		return out;
	}
	
}
