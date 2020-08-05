package endercrypt.hotline.engine.graphics;


import java.awt.Color;
import java.awt.Graphics2D;

import endercrypt.hotline.engine.window.HotlinePanel;


public class HotlineGraphics extends SmartGraphics
{
	private final HotlinePanel panel;
	
	public HotlineGraphics(Graphics2D g2d, HotlinePanel panel)
	{
		super(g2d);
		this.panel = panel;
	}
	
	public HotlinePanel getPanel()
	{
		return panel;
	}
	
	// CLEAR //
	
	public void clear(Color color)
	{
		Color currentColor = getColor();
		setColor(color);
		clear();
		setColor(currentColor);
	}
	
	public void clear()
	{
		fillRect(0, 0, panel.getWidth(), panel.getHeight());
	}
}
