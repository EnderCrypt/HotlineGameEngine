package endercrypt.hotline.engine.window;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import endercrypt.hotline.engine.core.HotlineGameEngine;
import endercrypt.hotline.engine.graphics.HotlineGraphics;


@SuppressWarnings("serial")
public class HotlinePanel extends JPanel
{
	private final HotlineGameEngine hotline;
	
	public HotlinePanel(HotlineGameEngine hotline)
	{
		this.hotline = hotline;
	}
	
	public Dimension getDisplaySize()
	{
		return getSize();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		// graphics
		Graphics2D g2d = (Graphics2D) g;
		HotlineGraphics graphics = new HotlineGraphics(g2d, this);
		
		// clear
		graphics.clear(Color.WHITE);
		
		// draw
		hotline.getRoomManager().getRoom().ifPresent(r -> r.draw(graphics));
	}
}
