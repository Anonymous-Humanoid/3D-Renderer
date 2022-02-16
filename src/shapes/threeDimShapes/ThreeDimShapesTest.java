/**
 * Date: June 27, 2021
 * Description: Displays three dimensional polyhedra
 * @author Matt
 * 
 */

package shapes.threeDimShapes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

// Driver class
public class ThreeDimShapesTest extends JPanel implements Cloneable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1798687661008451106L;
	
	// Variable constructors
	protected List<ThreeDimShapes> shapes;
	protected Thread roThread;
	protected int roDelay;
	protected JPanel p;
	
	/**
	 * @Description The method constructor
	 * 
	 * @param shapes The three dimensional shapes
	 * @param roThread A <code>Thread</code> to run when the shapes rotate
	 * @param roDelay The delay between rotations, in milliseconds
	 * @param p The <code>JPanel</code>
	 * 
	 */
	public ThreeDimShapesTest(List<ThreeDimShapes> shapes, Thread roThread, int roDelay, JPanel p)
	{
		// Adding panel components
//		TODO synchronized (p.getTreeLock())
//		{
			for (int i = 0; i < p.getComponentCount(); i++)
				add(p.getComponent(i));
//		}
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
	
	// Driver code
	public static void main(String[] args)
	{
		// Declaring collection of 3D shapes
		List<ThreeDimShapes> shapes = new ArrayList<ThreeDimShapes>(3);
		
		// Initializing shapes
		shapes.add(new ThreeDimShapes(ThreeDimShape.CUBE));
		shapes.add(new ThreeDimShapes(ThreeDimShape.ICOSAHEDRON));
		shapes.add(new ThreeDimShapes(ThreeDimShape.PYRAMID));
		shapes.add(new ThreeDimShapes());
		shapes.add(new ThreeDimShapes());
		
		// Scaling shapes
		shapes.get(1).scale(0.3);
		shapes.get(2).scale(0.5);
		
		// Diagonally shifting pyramid
		for (Vertex v : shapes.get(2).vertices)
			for (int i = 0; i < v.size(); i++)
				v.vertex[i] += 150;
		
		// Rotating shapes
		for (ThreeDimShapes s : shapes)
			s.rotate(Math.PI / 4, Math.PI / 2);
		
		// Initializing custom shape (quadratic) vertices
		for (int i = -4; i <= 4; i++)
			shapes.get(3).vertices.add(new Vertex(i, Math.pow(i, 2), 20));
		
		// Initializing custom shape (circle) vertices
		for (double i = -1; i <= 1; i += 0.1)
			shapes.get(4).vertices.add(new Vertex(i, Math.sqrt(1 - i * i), 2));
		for (double i = 1; i >= -1; i -= 0.1)
			shapes.get(4).vertices.add(new Vertex(i, -Math.sqrt(1 - i * i), 2));
		
		// Scaling shapes
		shapes.get(3).vertices.scale(-10);
		shapes.get(4).vertices.scale(100);
		
		// Rotating shape
		shapes.get(3).rotate(Math.PI / 4, 0);
		
		// Initializing custom shapes' edges
		shapes.get(3).addEdges(Edge.SEQUENTIAL, shapes.get(3).vertices.size());
		shapes.get(4).addEdges(Edge.SEQUENTIAL, shapes.get(4).vertices.size());
		
		// Initializing rotation thread
		Thread roThread = new Thread(() ->
		{
			shapes.get(0).rotate(Math.PI / 180, 0.01);
			shapes.get(1).rotate(Math.PI / 60, 0.075);
			shapes.get(2).rotate(Math.PI / 90, 0.02);
			shapes.get(3).rotate(Math.PI / 90, 0.01);
			shapes.get(4).rotate(Math.PI / 90, 0.02);
		});
		
		// Initializing frame
		SwingUtilities.invokeLater(() ->
		{
			JFrame f = new JFrame();
			JPanel p = new JPanel();
			
			p.setPreferredSize(p.getPreferredSize());
			p.setBackground(Color.orange);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setTitle("Rotating Shape");
			f.setPreferredSize(new Dimension(640, 640));
			f.setResizable(true);
			
			ThreeDimShapesTest plugin = new ThreeDimShapesTest(shapes, roThread, 17, p);
			
			f.add(plugin, BorderLayout.CENTER);
			f.pack();
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		});
	}
	
	// Draw vertices and edges
	public void draw(Graphics2D g)
	{
		g.translate(getWidth() / 2, getHeight() / 2);
		
		for (ThreeDimShapes s : shapes)
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
	
	// Clone
	@Override
	public ThreeDimShapesTest clone()
	{
		return new ThreeDimShapesTest(new ArrayList<ThreeDimShapes>(shapes),
				new Thread(roThread), roDelay, p);
	}
}
