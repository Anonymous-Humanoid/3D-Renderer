/**
 * Date: October 13, 2021
 * Description: A class containing a two-dimensional coordinate
 * @author Matthew
 * 
 */

package shapes.twoDimShapes;

public class Point implements Cloneable
{
	/**
	 * @Description The <code>Point</code>'s y-coordinate
	 * 
	 */
	public double x;
	
	/**
	 * @Description The <code>Point</code>'s x-coordinate
	 * 
	 */
	public double y;
	
	/**
	 * @Description The method constructor
	 * 
	 */
	public Point() {}
	
	/**
	 * @Description The method constructor
	 * @param pt
	 * 
	 */
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @Description The method constructor
	 * @param pt
	 * 
	 */
	public Point(java.awt.Point pt)
	{
		x = pt.x;
		y = pt.y;
	}
	
	/**
	 * @Description The method constructor
	 * @param pt
	 * 
	 */
	public Point(int[] pt)
	{
		x = pt[0];
		y = pt[1];
	}
	
	/**
	 * @Description The method constructor
	 * @param pt
	 * 
	 */
	public Point(java.awt.Dimension pt)
	{
		x = pt.getWidth();
		y = pt.getHeight();
	}
	
	/**
	 * @Description The method constructor
	 * @param pt
	 * 
	 */
	public <T extends Number> Point(T[] pt)
	{
		x = (double) pt[0];
		y = (double) pt[1];
	}
	
	/**
	 * @Description Scales the <code>Point</code>'s vector
	 * @param s The scalar
	 * 
	 */
	public void scale(double s)
	{
		x *= s;
		y *= s;
	}
	
	/**
	 * @Description Shifts the <code>Point</code>
	 * @param s1 The quantity to shift along the x-axis by
	 * @param s2 The quantity to shift along the y-axis by
	 * 
	 */
	public void shift(double s1, double s2)
	{
		x += s1;
		y += s2;
	}
	
	// TODO Proper method documentation
	
	// TODO Add/sub/etc in-place?
	
	// Add points
	public Point add(Point pt)
	{
		return new Point(x + pt.x, y + pt.y);
	}
	
	// Subtract points
	public Point subtract(Point pt)
	{
		return new Point(x - pt.x, y - pt.y);
	}
	
	// Multiply points
	public Point multiply(Point pt)
	{
		return new Point(x * pt.x, y * pt.y);
	}
	
	// Divide points
	public Point divide(Point pt)
	{
		return new Point(x / pt.x, y / pt.y);
	}
	
	/**
	 * @Description Clones the <code>Point</code>
	 * 
	 */
	@Override
	public Point clone()
	{
		return new Point(x, y);
	}
}
