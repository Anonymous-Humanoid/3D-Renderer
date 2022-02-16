/**
 * Date: December 24, 2020
 * Description: Tests configuring a tiled window
 * @author Matthew
 * 
 */

package shapes.threeDimShapes;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import shapes.twoDimShapes.Point;
import shapes.twoDimShapes.Points;

// Driver class
public class Test
{
	// Initializing window components
	JPanel panel = new JPanel();
	WindowTest window;
	JFrame frame;
	
	// Initializing tile components
	List<ThreeDimShapes> shapes = new ArrayList<ThreeDimShapes>();
	Points rotations = new Points();
	Point lastMousePos = new Point(0, 0);
	Double lastScroll = 0d;
	
	// Method constructor
	public Test(JFrame f)
	{
		window = new WindowTest(new JFrame());
		frame = window.frame;
		frame.add(panel);
//		panel.add(new ThreeDimShapesTest(shapes, panel));
		
//		var timer = new javax.swing.Timer(17, e ->
//		{
//			for (List<Tile> tile : tiles)
//			{
//				for (Tile t : tile)
//				{
//					t.rotate(t.rotations);
//				}
//			}
//			
//			repaint();
//		});
//		
//		timer.start();
		
		// Detecting scroll wheel movements
		frame.addMouseWheelListener(new MouseAdapter()
		{
        	@Override
        	public void mouseWheelMoved(MouseWheelEvent mwe)
        	{
        		System.out.println("Mouse wheel moved");
        		
        		java.awt.Point src = getSrc(mwe);
        		
        		if (src == null)
        		{
        			return;
        		}
        		
        		double newScroll = 3 * mwe.getPreciseWheelRotation();
        		// Returns plus-or-minus 0.75
        		// Multiplying by 3 to decrease zoom factor
        		
        		// Altering scalar depending on scroll direction
        		if (newScroll < 0)
        		{
        			// Zoom out
        			newScroll = -1 / newScroll;
        		}
        		
        		// Scaling shapes according to scroll
        		for (ThreeDimShapes s : shapes)
        		{
        			s.scale(newScroll);
        		}
        		
        		lastScroll = newScroll;
        	}
		});
		
		// Detecting mouse movements over panel
		frame.addMouseMotionListener(new MouseAdapter()
		{
			@Override
            public void mouseMoved(MouseEvent me)
            {
				System.out.println("Mouse moved");
				
				java.awt.Point src = getSrc(me);
				
        		if (src == null)
        		{
        			return;
        		}
				
        		Point newMousePos = new Point(me.getPoint());
				Point dist = new Point((newMousePos.x - lastMousePos.x),
						(newMousePos.y - lastMousePos.y));
				// NOTE: Can divide dist coordinates to reduce mouse sensitivity
				
				// Rotating shapes accordingly
				rotate(dist.x, dist.y);
				
				lastMousePos = newMousePos;
            }
			
        	@Override
        	public void mouseDragged(MouseEvent me)
        	{
        		mouseMoved(me);
        	}
		});
	}
	
	// Main method
	public static void main(String[] args)
	{
		// Setting up basic frame components
		JFrame f = new JFrame("Title");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(425, 600));
		f.pack();
		
		new Test(f);
		f.validate();
		f.setVisible(true);
	}
	
	// Add default tile
	public void addTile()
	{
		shapes.add(new ThreeDimShapes(ThreeDimShape.CUBE));
		shapes.get(shapes.size() - 1).scale(100);
//		Point r = new Point(Math.PI / 180, 0.01);
//		
//		// Adding rotations
//		for (int u = i; u < t.shapes.get(i).vertices.size(); u++)
//		{
//			t.rotations.add(r.clone());
//		}
	}
	
	// Get source
	protected java.awt.Point getSrc(MouseWheelEvent mwe)
	{
		return getSrc((MouseEvent) mwe);
	}
	
	// Scale shapes
	public void scale(double scalar)
	{
		for (ThreeDimShapes s : shapes)
		{
			s.scale(scalar);
		}
	}
	
	// Rotate shapes
	public void rotate(double x, double y)
	{
		for (ThreeDimShapes s : shapes)
		{
			s.rotate(x, y);
		}
	}
	
	/**
	 * @Description Returns the {@code Tile}
	 * that a {@code MouseEvent} sources from
	 * @param me The sourced {@code MouseEvent}
	 * @return {@Link java.awt.Point} // TODO Figure out how to use the <code>@Link</code> annotation
	 */
	protected java.awt.Point getSrc(MouseEvent me)
	{
//		for (int i = 0; i < tiles.size(); i++)
//		{
//			for (int j = 0; j < tiles.get(i).size(); j++)
//			{
//				System.out.println(tiles.get(i, j));
//				System.out.println(me.getSource());
//				System.out.println();
//				
//				if (tiles.get(i, j) == me.getSource())
//				{
//					return new java.awt.Point(i, j);
//				}
//			}
//		}
		
		// TODO Insert todo comment
		if (me.getSource() == panel)
		{
			return new java.awt.Point(0, 0);
		}
		
		return null;
	}
	
//	// TODO Add tile column method
//	protected void addColumn()
//	{
//		grid.y++;
//		
//	}
//	
//	// TODO Remove tile column method
//	protected void rmvTile(int i, int j)
//	{
//		tiles.get(i).remove(j);
//		
//		/*
//		if (tiles.get(i).isEmpty())
//		{
//			
//		}
//		 */
//	}
//	
//	// Add tile row method TODO Test addRow method
//	protected void addRow()
//	{
//		// Adding tiles
//		for (int i = 0; i < grid.x; i++)
//		{
//			addTile(tiles.size());
//		}
//		
//		grid.x++;
//	}
//	
//	// TODO Remove tile column method
//	protected void rmvColumn(int y)
//	{
//		// Removing column tiles
//		for (int i = grid.y - 1; i < )
//		{
//			
//		}
//		
//		grid.y--;
//	}
//	
//	// TODO Remove tile row method
//	protected void rmvRow(int x) // x begins at 0
//	{
//		grid.x--;
//		
//		// Removing row tiles
//		for (int i = 0; i < grid.y; i++)
//		{
//			tiles.remove(grid.y * (x - 1));
//		}
//	}
	
}
