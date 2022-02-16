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

// Driver class
public class ShapesWindowTest extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1512880274055850660L;
	
	// Initializing window components
	ListList<Tile> tiles = new ListList<Tile>(); // TODO Convert to Tile[5][5]
	JFrame frame;
	WindowTest window;
	java.awt.Point grid = new java.awt.Point(1, 1);
	
	/*
	/**
	 * @Description The method constructor
	 * 
	 * @param shapes The three dimensional shapes
	 * @param roThread A {@code Thread} to run when the shapes rotate
	 * @param roDelay The delay between rotations, in milliseconds
	 * @param p The {@code JPanel}
	 * 
	 * /
	public ThreeDimShapesTest(List<ThreeDimShapes> shapes, Thread roThread, int roDelay, JPanel p)
	{
		// Adding panel components
		for (int i = 0; i < p.getComponentCount(); i++)
		{
			// todo Follow getComponent method usage guidelines:
			// "This method should be called under AWT tree lock."
			add(p.getComponent(i));
		}
		// (Needed to fix background texture bug in bottom-right hand corner)
		
		setBackground(p.getBackground());
		
		// Setting variable constructors (all but shapes are for clone method)
		this.shapes = shapes;
		this.roThread = roThread;
		this.roDelay = roDelay;
		this.p = p;
		
		// Setting up rotation timer
		Timer timer = new Timer(roDelay, e ->
		{
			roThread.run();
			repaint();
		});
		
		timer.start();
	}
	
	// Method constructor
	public ThreeDimShapesTest(List<ThreeDimShapes> shapes, JPanel p)
	{
		this(shapes, new Thread(), 0, p);
	}
	 */
	
	// Method constructor
	public ShapesWindowTest(JFrame f)
	{
		frame = f;
		window = new WindowTest(frame);
		
		tiles.add(new ArrayList<Tile>());
		addTile(0);
		
		// TESTING
//		addTile(0);
//		tiles.add(new ArrayList<Tile>());
//		addTile(1);
//		addTile(1);
//		grid.x = 2;
//		grid.y = 2;
		
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
		

		
		frame.add(this);
	}
	
	// Main method
	public static void main(String[] args)
	{
		// Setting up basic frame components
		JFrame f = new JFrame("Title");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(425, 600));
		f.pack();
		
		var w = new ShapesWindowTest(f);
		w.setBackground(Color.orange);
		
		f.validate();
		f.setVisible(true);
	}
	
	// Method constructor
	public ShapesWindowTest(JFrame f, ListList<Tile> tiles)
	{
		frame = f;
		this.tiles = tiles;
		window = new WindowTest(frame);
		
		frame.add(this, "Center");
	}
	
	// Add default tile
	public void addTile(int i)
	{
		Tile t = new Tile();
		t.shapes.add(new ThreeDimShapes(ThreeDimShape.CUBE));
		t.shapes.get(0).scale(100);
//		Point r = new Point(Math.PI / 180, 0.01);
//		
//		// Adding rotations
//		for (int u = i; u < t.shapes.get(i).vertices.size(); u++)
//		{
//			t.rotations.add(r.clone());
//		}
		
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
        		tiles.get(src.x, src.y).scale(newScroll);
        		tiles.get(src.x, src.y).lastScroll = newScroll;
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
				Tile t = tiles.get(src.x, src.y);
				Point dist = new Point((newMousePos.x - t.lastMousePos.x),
						(newMousePos.y - t.lastMousePos.y));
				// NOTE: Can divide dist coordinates to reduce mouse sensitivity
				
				// Rotating shapes accordingly
				t.rotate(dist);
//				t.rotate(dist.multiply(tiles.get(src.x, src.y).rotations));
				
				t.lastMousePos = newMousePos;
            }
			
        	@Override
        	public void mouseDragged(MouseEvent me)
        	{
        		mouseMoved(me);
        	}
		});
		
		addTile(i, t);
	}
	
	// Add existing tile
	protected void addTile(int index, Tile t)
	{
		tiles.get(index).add(t);
		add(tiles.get(index, tiles.get(index).size() - 1));
	}
	
	// Get source
	protected java.awt.Point getSrc(MouseWheelEvent mwe)
	{
		return getSrc((MouseEvent) mwe);
	}
	
	/**
	 * @Description Returns the {@code Tile}
	 * that a {@code MouseEvent} sources from
	 * @param me The sourced {@code MouseEvent}
	 * @return {@Link java.awt.Point} // TODO Figure out how to use the <code>@Link</code> annotation
	 */
	protected java.awt.Point getSrc(MouseEvent me)
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			for (int j = 0; j < tiles.get(i).size(); j++)
			{
				System.out.println(tiles.get(i, j));
				System.out.println(me.getSource());
				System.out.println();
				
				if (tiles.get(i, j) == me.getSource())
				{
					return new java.awt.Point(i, j);
				}
			}
		}
		
		return null;
		
//		return new java.awt.Point(0, 0);
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
	
	// Draw vertices and edges
	public void draw(Graphics2D g)
	{
		g.translate(getWidth() / 2, getHeight() / 2);
		
		for (List<Tile> tile : tiles)
		{
			for (Tile t : tile)
			{
				for (ThreeDimShapes s : t.shapes)
				{
					s.edges.pts.forEach(edge ->
					{
						Vertex edge_1 = s.vertices.get((int) edge.x);
						Vertex edge_2 = s.vertices.get((int) edge.y);
						
						g.drawLine
						(
							(int) Math.round(edge_1.getX()),
							(int) Math.round(edge_1.getY()),
							(int) Math.round(edge_2.getX()),
							(int) Math.round(edge_2.getY())
						);
					});
					
					// Filling in circles at vertices
					for (Vertex v : s.vertices)
					{
						g.fillOval((int) Math.round(v.getX()) - 4,
								(int) Math.round(v.getY()) - 4, 8, 8);
					}
				}
			}
		}
	}
	
	// Paint
	@Override
	public void paintComponent(Graphics gg)
	{
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D) gg;
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		draw(g);
	}
	
	/*
	// Clone
	@Override
	public ThreeDimShapesTest clone()
	{
		return new ThreeDimShapesTest(new ArrayList<ThreeDimShapes>(shapes),
				new Thread(roThread), roDelay, p);
	}
	 */
	
}
