/**
 * Date: October 13, 2021
 * Description: // TODO Descriptions and summaries
 * @author Matt
 * 
 */

package shapes.twoDimShapes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// TODO Parameter descriptions
public class Points implements Cloneable // TODO Inherit List<? extends Point>
{
	/**
	 * @Description The <code>Point</code> container
	 * 
	 */
	public List<Point> pts;
	
	/**
	 * @Description Initializes the <code>Point</code> container
	 * @param pts
	 * 
	 */
	public Points()
	{
		pts = new ArrayList<Point>();
	}
	
	/**
	 * @Description The method constructor
	 * @param pts
	 * 
	 */
	public Points(int index)
	{
		pts = new ArrayList<Point>(index);
	}
	
	/**
	 * @Description The method constructor
	 * @param pts
	 * 
	 */
	public Points(double x, double y)
	{
		this();
		pts.add(new Point(x, y));
	}
	
	/**
	 * @Description The method constructor
	 * @param pts
	 * 
	 */
	public Points(java.awt.Point... pts)
	{
		this(pts.length);
		
		for (java.awt.Point pt : pts)
		{
			this.pts.add(new Point(pt));
		}
	}
	
	/**
	 * @Description The method constructor
	 * @param pts
	 * 
	 */
	public Points(Point... pts)
	{
		this(pts.length);
		
		for (Point pt : pts)
		{
			this.pts.add(pt);
		}
	}
	
	/**
	 * @Description The method constructor
	 * @param pts
	 * 
	 */
	public Points(Collection<Point> pts)
	{
		this.pts = new ArrayList<Point>(pts);
	}
	
	/**
	 * @Description The method constructor
	 * @param pts
	 * 
	 */
	public Points(double[][] pts)
	{
		this(pts.length * pts[0].length);
		
		for (double[] pt : pts)
			add(new Point(pt[0], pt[1]));
	}
	
	/**
	 * @Description The method constructor
	 * @param pts
	 * 
	 */
	public Points(int[][] pts)
	{
		this(pts.length * pts[0].length);
		
		for (int[] pt : pts)
			add(new Point(pt[0], pt[1]));
	}
	
	/**
	 * @Description The method constructor
	 * @param pts
	 * 
	 */
	public Points(Point pt)
	{
		this();
		
		add(pt);
	}
	
	/**
	 * @Description Returns the <code>Point</code> at the entered index
	 * @param index
	 * 
	 */
	public Point get(int index)
	{
		return pts.get(index);
	}
	
	/**
	 * @Description Adds the entered point to the
	 * <code>Collection</code> of these
	 * @param pt
	 * 
	 */
	public void add(Point pt)
	{
		pts.add(pt);
	}
	
	/**
	 * @Description Adds the entered point to the
	 * <code>Collection</code> of these
	 * @param pts
	 * 
	 */
	public void add(java.awt.Point pt)
	{
		pts.add(new Point(pt));
	}
	
	/**
	 * @Description Adds the entered points to the
	 * <code>Collection</code> of these
	 * @param pts
	 * 
	 */
	public void addAll(Points pts)
	{
		this.pts.addAll(pts.pts);
	}
	
	/**
	 * @Description Adds the entered points to the
	 * <code>Collection</code> of these
	 * @param pts
	 * 
	 */
	public void addAll(Collection<Point> pts)
	{
		this.pts.addAll(pts);
	}
	
	/**
	 * @Description Scales the <code>Points</code>
	 * about the origin by a factor of the scalar
	 * <code>s</code> of the <code>Points</code>
	 * @param s
	 * 
	 */
	public void scale(int s)
	{
		for (Point pt : pts)
		{
			pt.x *= s;
			pt.y *= s;
		}
	}
	
	/**
	 * @Description Fills in line points
	 * @param x
	 * @param y
	 * 
	 */
	public void fillLinePoints(Point x, Point y) // TODO Test fillLinePoints method
	{
		double xdiff = y.x - x.x;
		double ydiff = y.y - x.y;
		double slope = xdiff == 0 ? 0 : ydiff / xdiff;
		boolean check = Math.abs(slope) < 1;
		
		if (check)
		{
			fillLinePoints(new Point(1 / y.x, 1 / y.y),
					new Point(1 / x.x, 1 / x.y));
		}
		
		for (int i = 0; i < (check ? xdiff : ydiff); i++)
		{
			pts.add(new Point(Math.round(slope * i) + Math.min(x.x, y.x),
					Math.round(check ? (slope * i + Math.min(x.y, y.y)) : i)));
		}
	}
	
	/**
	 * @Description Returns the quantity of points stored
	 * 
	 */
	public int size()
	{
		return pts.size();
	}
	
	/**
	 * @Description Deep clones the <code>Points</code>
	 * and each <code>Point</code> it contains
	 * 
	 */
	@Override
	public Points clone()
	{
		Points out = new Points();
		
		// Transferring points
		pts.forEach(p ->
		{
			out.add(p.clone());
		});
		
		return out;
	}
}
