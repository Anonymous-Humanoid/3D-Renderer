/**
 * Date: April 27, 2021
 * Description: A basic (WIP) CAD program involving
 * interactive three-dimensional shapes
 * @author Matt
 * 
 */

package shapes.threeDimShapes;

import java.awt.BorderLayout;
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
public class ThreeDimShapesWindowTest extends ThreeDimShapesTest
		implements Cloneable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 368995151749388231L;
	
	// Variable constructors
	protected ListList<Tile> tiles = new ListList<Tile>();
	protected ListList<Points> rotations = new ListList<Points>(); // TODO Use rotations variable
	protected ListList<Point> centers = new ListList<Point>(); // TODO Reinitialize center when either: window is resized or a tile(s) is added/removed
	protected ListList<Point> lastMousePos = new ListList<Point>();
	protected ListList<Double> lastScroll = new ListList<Double>();
	protected static ShapesWindowTest window
			= new ShapesWindowTest(new JFrame());
	
	// Method constructor
	public ThreeDimShapesWindowTest(List<ThreeDimShapes> shps, Points rots)
	{
		super(shps, new JPanel());
		
		// Initializing mouse-related components
		rotations	.add(new ArrayList<Points>());
		centers		.add(new ArrayList<Point>());
		lastMousePos.add(new ArrayList<Point>());
		lastScroll	.add(new ArrayList<Double>());
		rotations	.get(0).add(new Points());
		centers		.get(0).add(new Point(0, 0)); // TODO See if initializing a Point like this is functional
		lastMousePos.get(0).add(new Point(0, 0));
		lastScroll	.get(0).add(0d);
		
		// Adding scroll wheel listener
		addMouseWheelListener(new MouseAdapter()
		{
			@Override
			public void mouseWheelMoved(MouseWheelEvent mwe)
			{
				java.awt.Point src = getSrc(mwe);
				
				// Checking if source was a tile
				if (src == null)
				{
					return;
				}
				
        		double newScroll = 3 * mwe.getPreciseWheelRotation();
        		// newScroll == plus-or-minus 0.75
        		// Multiplying by 3 to decrease zoom factor
        		
        		// Altering scalar depending on scroll direction
        		if (newScroll < 0)
        		{
        			// Zoom out
        			newScroll = -1 / newScroll;
        		}
        		
        		// Scaling shapes
        		tiles.get(src.x, src.y).scale(newScroll);
        		lastScroll.set(src.x, src.y, newScroll);
			}
		});
		
		// Adding mouse motion listener
		addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				java.awt.Point src = getSrc(me);
				
				// Checking if source was a tile
				if (src == null)
				{
					return;
				}
				
				if (me.getButton() == MouseEvent.BUTTON1)
				{
					// TODO Left click
					System.out.printf("Tile[%d][%d] was left-clicked%n", src.x, src.y);
				}
				else if (me.getButton() == MouseEvent.BUTTON3)
				{
					// TODO Right click
					System.out.printf("Tile[%d][%d] was left-clicked%n", src.x, src.y);
				}
			}
			
			@Override
		    public void mouseMoved(MouseEvent me)
		    {
				java.awt.Point src = getSrc(me);
				
				// Checking if source was a tile
				if (src == null)
				{
					return;
				}
				
				Point lastMousePos = ThreeDimShapesWindowTest.this
						.lastMousePos.get(src.x).get(src.y);
				Point newMousePos = new Point(me.getPoint());
				Point dist = new Point((newMousePos.x - lastMousePos.x),
						(newMousePos.y - lastMousePos.y));
				// NOTE: Can divide dist coordinates to reduce mouse sensitivity
				
				// Rotating shapes accordingly
				tiles.get(src.x, src.y).rotate(dist);
				
				lastMousePos = newMousePos;
		    }
			
			@Override
			public void mouseDragged(MouseEvent me)
			{
				mouseMoved(me);
			}
		});
		
		// Setting up frame
		tiles.add(new ArrayList<Tile>());
		addNewTile(0);
		p.setLayout(new BorderLayout());
		p.add(tiles.get(0, 0));
		revalidate();
	}
	
	// Method constructor
	public ThreeDimShapesWindowTest()
	{
		// TODO Test for potential Exceptions
		this(new ArrayList<ThreeDimShapes>(), new Points());
	}
	
	// Main method
	public static void main(String[] args)
	{
		// Displaying frame
		SwingUtilities.invokeLater(() ->
		{
			var<JFrame> plugin = new ThreeDimShapesWindowTest();
			JFrame f = ThreeDimShapesWindowTest.window.frame;
			
			// Setting up frame
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setTitle("Rotating Shape");
			f.setResizable(true);
			f.setPreferredSize(new Dimension(425, 600));
			f.pack();
			f.setLocationRelativeTo(null);
			f.add(plugin, BorderLayout.CENTER);
			f.revalidate();
			f.setVisible(true);
		});
	}
	
	/**
	 * This method is only to be invoked when creating a new tile
	 * <p>
	 * 
	 * @Description Adds a new default tile to the <code>JPanel</code>
	 * @param xIndex
	 * 
	 */
	protected void addNewTile(int xIndex)
	{
		var cube = new ThreeDimShapes(ThreeDimShape.CUBE);
		
		// Adding new default tile
		tiles.get(xIndex).add(new Tile(cube));
		tiles.get(xIndex, tiles.size() - 1).scale(100d);
		
		// Adding default rotation
		rotations.get(xIndex).add(new Points(Math.PI / 4, Math.PI / 2)); // TODO Test if functional
		
		// Adding mouse-related components
		centers.get(xIndex, tiles.size() - 1).add(
				new Point(p.getSize().width / (2 * window.grid.x),
				p.getSize().height / (2 * window.grid.y)));
		lastMousePos.get(xIndex).set(tiles.size() - 1,
				centers.get(xIndex, tiles.size() - 1));
		lastScroll.add(new ArrayList<>());
		
	}
	
	// TODO Make ThreeDimShape methods that require tile size (e.g., scale)
	
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
				if (tiles.get(i, j) == me.getSource())
				{
					return new java.awt.Point(i, j);
				}
			}
		}
		
		return null;
	}
	
	// Deep clone
	@Override
	public ThreeDimShapesWindowTest clone()
	{
		var out = new ThreeDimShapesWindowTest();
		
		out.tiles = tiles.clone(t -> t.clone());
		out.centers = centers.clone(c -> c.clone());
		out.rotations = rotations.clone(r -> r.clone());
		out.lastScroll = lastScroll.clone(ls -> {return (double) ls;});
		out.lastMousePos = lastMousePos.clone(lmp -> lmp.clone());
		
		return out;
	}
	
}
