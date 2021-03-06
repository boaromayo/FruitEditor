package FruitEditor;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class FruitPanel extends JPanel {
	
	// FILES.
	private FruitEditor fruitEditor;
	
	// PANELS.
	private MapPanel mapPanel;
	private TilePanel tilePanel;
	
	// PANEL PANES.
	JScrollPane tileScrollPane;
	//JTabbedPane tileTabbedPane;
	private JScrollPane mapScrollPane;
	
	// MAIN PANELS.
	JPanel leftPanel;
	JPanel rightPanel;
	
	// SPLIT PANE.
	JSplitPane splitPane;
	
	public FruitPanel(FruitEditor f) {
		fruitEditor = f;
		
		setPreferredSize(new Dimension(
				FruitEditor.SCREEN_WIDTH, 
				FruitEditor.SCREEN_HEIGHT));
		setLayout(new BorderLayout());
		
		setupPanels();
	}
	
	private void setupPanels() {
		splitPane = new JSplitPane();
		
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		
		mapPanel = new MapPanel(fruitEditor);
		tilePanel = new TilePanel(fruitEditor);
		//mapListPanel = new MapListPanel();

		tileScrollPane = new JScrollPane(tilePanel);
		//tileTabbedPane = new JTabbedPane();
		mapScrollPane = new JScrollPane(mapPanel);
		
		// Make the scrolling faster for scroll panes. Set to an arbitrary speed.
		tileScrollPane.getVerticalScrollBar().setUnitIncrement(FruitEditor.SCROLL_SPEED);
		tileScrollPane.getHorizontalScrollBar().setUnitIncrement(FruitEditor.SCROLL_SPEED);
		mapScrollPane.getVerticalScrollBar().setUnitIncrement(FruitEditor.SCROLL_SPEED);
		mapScrollPane.getVerticalScrollBar().setUnitIncrement(FruitEditor.SCROLL_SPEED);
	
		mapPanel.setViewport(mapScrollPane.getViewport());
		tilePanel.setViewport(tileScrollPane.getViewport());
		
		leftPanel.setLayout(new BorderLayout());	
		rightPanel.setLayout(new BorderLayout());
		
		leftPanel.add(tileScrollPane);
		//leftPanel.add(tileTabbedPane);
		rightPanel.add(mapScrollPane);
		
		leftPanel.setEnabled(fruitEditor.isPanelActive() && fruitEditor.getMap() != null);
		rightPanel.setEnabled(fruitEditor.isPanelActive() && fruitEditor.getMap() != null);
		
		splitPane.setDividerLocation(FruitEditor.SCREEN_WIDTH / 4);
		splitPane.setEnabled(false); // Prevents resizing of splitpane.
		splitPane.setLeftComponent(leftPanel);
		splitPane.setRightComponent(rightPanel);
		
		add(splitPane, BorderLayout.CENTER);
		
		repaint();
	}
	
	public MapPanel getMapPanel() {
		if (mapPanel != null)
			return mapPanel;
		
		return null;
	}
	
	public TilePanel getTilePanel() {
		if (tilePanel != null)
			return tilePanel;
		
		return null;
	}
	
	/*public MapListPanel getMapListPanel() {
		if (mapListPanel != null)
			return mapListPanel;
		
		return null;
	}*/
	
	public void update() {
		mapPanel.update();
		tilePanel.update();
	}
}