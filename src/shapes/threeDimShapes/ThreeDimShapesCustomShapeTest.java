/**
 * Date: February 16, 2022
 * Description: A system to visualize and interact
 * with coordinates within a three-dimensional space
 * @author Matt
 * 
 */

package shapes.threeDimShapes;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import shapes.twoDimShapes.Point;
import shapes.twoDimShapes.Points;

public class ThreeDimShapesCustomShapeTest
{
	// Driver code
	public static void main(String[] args)
	{
		// Initializing shapes
		List<ThreeDimShapes> shapes = new ArrayList<ThreeDimShapes>(1);
		shape.addVertices(coords);
//		shape.addEdges(Edge.ALL);
		shapes.add(shape);
		
		// Setting up frame components
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(640, 640));
		p.setBackground(Color.orange);
		
		// Initializing rotation scheme
		Points rots = new Points
		(
			new Point(Math.PI / 90, 0.02),
			new Point(Math.PI / 90, 0.02)
		);
		
		// Initializing automatic rotation thread
		var roThread = new javax.swing.Timer(17, e ->
		{
			for (int i = 0; i < shapes.size(); i++)
			{
				shapes.get(i).rotate(rots.get(i).x, rots.get(i).y);
				f.repaint();
			}
		});
		
		// Initializing visualization plugin
		var plugin = new ThreeDimShapesMouseTest(shapes, rots, p);
		
		// Setting up frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Cave System Visualizer");
		f.setPreferredSize(p.getPreferredSize());
		f.setResizable(true);
		f.add(plugin, BorderLayout.CENTER);
		f.pack();
		f.setLocationRelativeTo(null);
		
		// Initializing automatic rotation toggle
		f.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				// Toggling rotation upon spacebar press
				if (e.getKeyChar() == ' ')
				{
					if (roThread.isRunning())
					{
						roThread.stop();
					}
					else
					{
						roThread.start();
					}
				}
			}
		});
		
		// Displaying frame
		SwingUtilities.invokeLater(() -> f.setVisible(true));
	}
	
	private static ThreeDimShapes shape = new ThreeDimShapes();
	private static Vertices coords = new Vertices(new double[][]
	{
		{-781, 9, -588},
		{-535, 85, -137},
		{-508, 41, -127},
		{-465, 5, -520},
		{-465, 5, -233},
		{-423, 32, -289},
		{-419, 30, -295},
		{92, 69, -27},
		{277, 64, 213},
		{343, 56, 123},
		{359, 70, 190},
		{368, 48, -14},
		{396, 48, 14},
		{497, 66, -159},
		{505, 59, -129},
		{435, 24, -80},
		{380, 70, 190},
		{431, 48, 110},
		{467, 49, -191},
		{471, 21, -160},
		{476, 11, -167},
		{483, 19, -139},
		{483, 47, -188},
		{483, 53, -161},
		{487, 26, -126},
		{491, 50, -180},
		{495, 18, 157},
		{496, 21, -109},
		{512, 39, -149},
		{514, 39, -164},
		{516, 22, -158},
		{516, 20, -153},
		{517, 38, -167},
		{518, 15, -177},
		{518, 38, -165},
		{524, 12, -179},
		{528, 24, -161},
		{530, 11, -147},
		{532, 17, -98},
		{535, 15, -186},
		{536, 24, -192},
		{539, 8, -172},
		{546, 25, -100},
		{547, 22, -185},
		{550, 18, -179},
		{555, 24, -122},
		{557, 25, -137},
		{519, 11, -194},
		{553, 14, -165},
		{528, 9, -167},
		{502, 39, -176},
		{508, 33, -100}
	});
}
