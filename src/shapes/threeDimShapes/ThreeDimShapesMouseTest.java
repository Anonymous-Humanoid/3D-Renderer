/**
 * Date: October 13, 2020
 * Description: Displays interactive three-dimensional polyhedra
 * @author Matt
 * 
 */

package shapes.threeDimShapes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import shapes.twoDimShapes.Point;
import shapes.twoDimShapes.Points;

// Driver class
public class ThreeDimShapesMouseTest extends ThreeDimShapesTest implements Cloneable
{
	/**
	 * @Description The class serial number
	 */
	private static final long serialVersionUID = -1693021691635259744L;
	
	// Variable constructors
	protected Points  rotations;
	protected Point   rotOrigin 	= new Point(0, 0);
	protected Point   lastMP 		= new Point(0, 0);
	
	/**
	 * @implNote {@code rotOrigin} won't reset to (0,0) if dynamicOrigin is disabled
	 */
	protected boolean dynamicOrigin = false;
	
	// Method constructor
	public ThreeDimShapesMouseTest(List<ThreeDimShapes> shps, Points rots, JPanel p)
	{
		super(shps, p);
		
		rotations = new Points();
		
		// Setting rotation values
		try
		{
			// Trying to add rotation points
			for (int i = 0; i < shapes.size(); i++)
				rotations.add(rots.pts.get(i));
		}
		catch (IndexOutOfBoundsException e)
		{
			// Adding default rotation points
			for (int i = rots.pts.size(); i < shapes.size(); i++)
				rotations.add(new Point(0, 0));
		}
		
		// Detecting scroll wheel movements TODO Check if mouse or trackpad used
		addMouseWheelListener(new MouseAdapter()
		{
        	@Override
        	public void mouseWheelMoved(MouseWheelEvent mwe) // TODO Change origin based on scroll
        	{
        		double newScroll = -3 * mwe.getPreciseWheelRotation();
        		// Returns plus-or-minus 0.75
        		// Multiplying by 3 to decrease zoom factor
        		// Multiplying by -1 to invert zoom direction
        		
        		// Altering scalar depending on scroll direction
        		if (newScroll < 0)
        		{
        			// Zoom out
        			newScroll = -1 / newScroll;
        		}
        		
        		// Scaling shapes according to mouse wheel movement
        		scale(newScroll);
        		
//        		// Scaling dynamic origin position // TODO dynamic origin
//        		rotOrigin.x *= newScroll;
//        		rotOrigin.y *= newScroll;
        	}
		});
		
		// Detecting mouse movements over panel
		addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent me)
			{
				// Allows the shape to be rotated smoothly by tracking
				// the mouse's position before shape is rotated
				lastMP = new Point(me.getPoint());
			}
			
	       	@Override
        	public void mouseDragged(MouseEvent me)
        	{
	       		Point newMP = new Point(me.getPoint());
	       		Point dist  = new Point(newMP.x - lastMP.x, newMP.y - lastMP.y);
	       		
	       		// Shape rotation (Lmb drag mode)
	       		if (me.getModifiersEx() == 0x400 && lastMP != newMP) rotate(dist);
	       		
	       		// Shape xy-panning (Rmb drag mode)
	       		else if (me.getModifiersEx() == 0x1000)
       			{
					// Panning shapes along xy axes accordingly
					for (ThreeDimShapes s : shapes)
					{
						for (Vertex v : s.vertices)
						{
							v.vertex[0] += dist.x;
							v.vertex[1] += dist.y;
						}
					}
					
//					// Shifting origin
//					if (dynamicOrigin) rotOrigin = rotOrigin.add(dist); // TODO dynamic origin
       			}
       			/*
	       		// TODO Fix shape z-panning (replace with xz and yz panning?) and implement dynamic origin
	       		else if (me.getModifiersEx() == 0x1040) // Rmb with shift mask drag mode
       			{
					double shift = Math.sqrt(dist.x * dist.x + dist.y * dist.y);
					
					// Reversing pan direction
					if (dist.x < 0 || dist.y < 0) shift *= -1;
					
					// Panning shapes along z-axis by distance from origin
					for (ThreeDimShapes s : shapes)
					{
						for (Vertex v : s.vertices)
						{
							v.vertex[0] += shift;
							v.vertex[1] += shift;
						}
					}
					
					// Shifting origin
					if (dynamicOrigin)
					{
						rotOrigin.x += shift;
						rotOrigin.y += shift;
					}
       			}
	       		 */
	       		
	       		lastMP = newMP;
        	}
		});
	}
	
	// Driver code
	public static void main(String[] args)
	{
		// Declaring collection of 3D shapes
		List<ThreeDimShapes> shapes = new ArrayList<ThreeDimShapes>(3);
		
		// Initializing shapes
//		shapes.add(new ThreeDimShapes(ThreeDimShape.CUBE));
		shapes.add(new ThreeDimShapes(ThreeDimShape.ICOSAHEDRON));
//		shapes.add(new ThreeDimShapes(ThreeDimShape.PYRAMID));
//		
//		// Scaling shapes
//		shapes.get(0).vertices.scale(1d / 1);
//		shapes.get(1).vertices.scale(1d / 3);
//		shapes.get(2).vertices.scale(1d / 2);
//		
//		// Diagonally shifting pyramid
//		for (Vertex v : shapes.get(2).vertices)
//			for (int i = 0; i < v.size(); i++)
//				v.vertex[i] += 150;
//		
//		// Rotating shapes
//		for (ThreeDimShapes s : shapes)
//			s.rotate(Math.PI / 4, Math.PI / 2);
		
//		shapes.add(new ThreeDimShapes(ThreeDimShape.HEXAGON));
		
		// Setting up frame components
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(640, 640));
		p.setBackground(Color.orange);
		
		Points rots = new Points
		(
//			new Point(Math.PI / 180, 0.01),
//			new Point(Math.PI / 60, 0.075),
			new Point(Math.PI / 90, 0.02)
		);
		
		var plugin = new ThreeDimShapesMouseTest(shapes, rots, p);
//		plugin.dynamicOrigin = true;
		
		// Setting up frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Rotating Shape");
		f.setPreferredSize(p.getPreferredSize());
		f.setResizable(true);
		f.add(plugin, BorderLayout.CENTER);
		f.pack();
		f.setLocationRelativeTo(null);
		
		// Displaying frame
		SwingUtilities.invokeLater(() -> f.setVisible(true));
		
		/*
		// Initializing rotation thread
		var roThread = new javax.swing.Timer(17, e ->
		{
			for (int i = 0; i < shapes.size(); i++)
			{
				shapes.get(i).rotate(rots.get(i).x, rots.get(i).y);
				f.repaint();
			}
		});
		
		// Automatically rotating shapes
		roThread.start();
		*/
	}
	
	// Method constructor
	public ThreeDimShapesMouseTest(List<ThreeDimShapes> shps, JPanel p)
	{
		this(shps, new Points(), p);
	}
	
	// Method constructor
	public ThreeDimShapesMouseTest(JPanel p)
	{
		this(new ArrayList<ThreeDimShapes>(), new Points(), p);
	}
	
	// Scale shapes
	public void scale(double scalar)
	{
		for (ThreeDimShapes s : shapes)
			s.vertices.scale(scalar);
	}
	
	// Rotate shapes
	public void rotate(Point rot)
	{
		if (rotations == null) return;
//		else if (rotations.pts.contains(null)) return;
		
		for (int i = 0; i < rotations.size(); i++)
		{
			Point temp = rotations.get(i).multiply(rot);
			
			if (dynamicOrigin) // TODO dynamic origin
			{
				// Translating shapes for rotation about (0,0)
				shapes.get(i).vertices.forEach(v ->
				{
					v.vertex[0] -= rotOrigin.x;
					v.vertex[1] -= rotOrigin.y;
				});
				
				// Rotating shapes
				shapes.get(i).rotate(temp.x, temp.y);
				
				// Translating rotated shapes back to original position
				shapes.get(i).vertices.forEach(v ->
				{
					v.vertex[0] += rotOrigin.x;
					v.vertex[1] += rotOrigin.y;
				});
			}
			else
			{
				shapes.get(i).rotate(temp.x, temp.y);
			}
			
//			shapes.get(i).rotate(temp.x, temp.y); // Temporary until dynamic origin is implemented
		}
	}
	
	/**
	 * @Description Clones the <code>ThreeDimShapesMouseTest</code>
	 * 
	 */
	@Override
	public ThreeDimShapesMouseTest clone()
	{
		var temp = new ThreeDimShapesMouseTest(shapes, rotations, p);
		
		temp.lastMP = lastMP.clone();
		
		return temp;
	}
}
