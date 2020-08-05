package endercrypt.hotline.engine.room;


import java.awt.Dimension;
import java.io.Serializable;

import endercrypt.hotline.engine.core.HotlineGameEngine;
import net.ddns.endercrypt.library.position.Motion;
import net.ddns.endercrypt.library.position.Position;


public class View implements Serializable
{
	private static final long serialVersionUID = -1471793566825703083L;
	
	/**
	 * 
	 */
	
	private Position position = new Position();
	private Motion motion = new Motion();
	
	public Position position()
	{
		return position;
	}
	
	public Motion motion()
	{
		return motion;
	}
	
	public Position getTranslation(HotlineGameEngine hotline)
	{
		double x = 0;
		double y = 0;
		
		Dimension screenSize = hotline.getWindow().getDisplaySize();
		
		x -= position.x;
		y -= position.y;
		
		x += screenSize.width / 2;
		y += screenSize.height / 2;
		
		return new Position(x, y);
	}
	
	public void update()
	{
		position.add(motion);
		motion.multiplyLength(0.75);
	}
}
