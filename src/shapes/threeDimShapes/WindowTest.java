/**
 * Date: December 20, 2020
 * Description: Tests configuring a window
 * @author Matthew
 * 
 */

package shapes.threeDimShapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

// Driver class
public class WindowTest
{
	List<JMenuItem> menuItems = new ArrayList<JMenuItem>();
	JFrame frame;
	
	// Main method
	public static void main(String[] args)
	{
		// Initializing frame components
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		
		f.setTitle("Title");
		p.setBackground(Color.orange);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(425, 600));
		f.pack();
//		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		new WindowTest(f);
		
		f.add(p, "Center");
		f.validate();
		f.setVisible(true);
	}
	
	// Method constructor
	public WindowTest(JFrame f)
	{
		frame = f;
		
		JMenu 	   file      = new JMenu("File");
		JMenu 	   shapes    = new JMenu("Shapes");
		JMenu 	   tiles     = new JMenu("Tiles");
		JMenu 	   help 	 = new JMenu("Help");
		JMenu  	   detach    = new JMenu("Detach"); // TODO Add mouse listener OR add menu items
		
		JMenuBar mb 		 = new JMenuBar();
		
		// TODO Add to menu item list
		JMenuItem  saveAs    = new JMenuItem("Save As");
		JMenuItem  save      = new JMenuItem("Save");
		JMenuItem  load      = new JMenuItem("Load");
		
		JMenuItem  add 	     = new JMenuItem("Add");
		JMenuItem  edit      = new JMenuItem("Edit");
		JMenuItem  remove    = new JMenuItem("Remove");
		
		JMenuItem  addColumn = new JMenuItem("Add column");
		JMenuItem  addRow 	 = new JMenuItem("Add row");
		
		JMenuItem  menubar   = new JMenuItem("Using the menubar");
		JMenuItem  addShps   = new JMenuItem("Adding shapes");
		JMenuItem  editShps  = new JMenuItem("Editing shapes");
		JMenuItem  rmvShps   = new JMenuItem("Removing shapes");
		
		menubar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		file		.add(save);
		file		.add(saveAs);
		file		.add(load);
		
		shapes		.add(add);
		shapes		.add(edit);
		shapes		.add(remove);
		
		tiles		.add(addColumn);
		tiles		.add(addRow);
		
		help  		.add(menubar);
		help		.add(addShps);
		help		.add(editShps);
		help		.add(rmvShps);
		
		mb			.add(file);
		mb			.add(shapes);
		mb			.add(tiles);
		mb			.add(detach);
		mb			.add(help);
		
		frame		.add(mb, "North");
	}
	
}
