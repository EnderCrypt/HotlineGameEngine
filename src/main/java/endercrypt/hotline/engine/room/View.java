package endercrypt.hotline.engine.room;


import java.awt.Dimension;
import java.util.function.Supplier;

import net.ddns.endercrypt.library.position.Motion;
import net.ddns.endercrypt.library.position.Position;


public class View
{
	private Supplier<Dimension> screenDimensionSupplier;
	
	private Position position = new Position();
	private Motion motion = new Motion();
	
	public View(Supplier<Dimension> screenDimensionSupplier)
	{
		this.screenDimensionSupplier = screenDimensionSupplier;
	}
	
	public Position position()
	{
		return position;
	}
	
	public Motion motion()
	{
		return motion;
	}
	
	public Position getTranslation()
	{
		double x = 0;
		double y = 0;
		
		Dimension screenSize = getDimension();
		
		x -= position.x;
		y -= position.y;
		
		x += screenSize.width / 2;
		y += screenSize.height / 2;
		
		return new Position(x, y);
	}
	
	public Dimension getDimension()
	{
		return screenDimensionSupplier.get();
	}
	
	public void update()
	{
		position.add(motion);
		motion.multiplyLength(0.75);
	}
}
