package endercrypt.hotline.engine.window;


import java.awt.Dimension;

import javax.swing.JFrame;

import endercrypt.hotline.engine.core.HotlineGameEngine;


public class HotlineWindow
{
	private final HotlineGameEngine hotline;
	
	private final JFrame frame;
	private final HotlinePanel panel;
	
	public HotlineWindow(HotlineGameEngine hotline)
	{
		this.hotline = hotline;
		
		panel = new HotlinePanel(hotline);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		setSize(new Dimension(1000, 500));
		frame.setVisible(true);
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public void setTitle(String title)
	{
		frame.setTitle(title);
	}
	
	public void setSize(Dimension dimension)
	{
		frame.setSize(dimension);
		frame.setLocationRelativeTo(null);
	}
	
	public Dimension getDisplaySize()
	{
		return panel.getSize();
	}
	
	public void redraw()
	{
		panel.repaint();
	}
	
	public void shutdown()
	{
		frame.dispose();
	}
}
