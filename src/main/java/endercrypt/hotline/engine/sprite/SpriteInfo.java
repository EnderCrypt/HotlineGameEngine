package endercrypt.hotline.engine.sprite;


import java.awt.geom.AffineTransform;

import net.ddns.endercrypt.library.position.Position;


public class SpriteInfo
{
	public double rotation = 0.0;
	public double scale_x = 1.0;
	public double scale_y = 1.0;
	
	protected AffineTransform generateTransform(Position position, SpriteInfo spriteInfo, SpriteData spriteData)
	{
		// clean
		rotation = rotation % 360.0;
		
		// variables
		Position center = spriteData.getCenter();
		
		// transform
		AffineTransform transform = new AffineTransform();
		transform.translate(position.x - center.x, position.y - center.y);
		transform.rotate(Math.toRadians(rotation), scale_x * center.x, scale_y * center.y);
		transform.scale(scale_x, scale_y);
		return transform;
	}
}
