package endercrypt.hotline.engine.sprite;


import java.awt.geom.AffineTransform;
import java.io.Serializable;

import endercrypt.hotline.engine.graphics.AffineTransformBuilder;
import net.ddns.endercrypt.library.position.Position;


public class SpriteInfo implements Serializable
{
	private static final long serialVersionUID = -2583721968716006419L;
	/**
	 * 
	 */
	public double rotation = 0.0;
	public double scale_x = 1.0;
	public double scale_y = 1.0;
	
	protected AffineTransform generateTransform(Position position, SpriteData spriteData)
	{
		// clean
		rotation = rotation % 360.0;
		
		// variables
		Position origin = spriteData.getOrigin();
		
		// transform
		return new AffineTransformBuilder()
			.setOrigin(origin.x, origin.y)
			.setPosition(position.x, position.y)
			.setScale(scale_x, scale_y)
			.setRotationDegrees(rotation)
			.build();
	}
}
