package endercrypt.hotline.engine.graphics;


import java.awt.geom.AffineTransform;


public class AffineTransformBuilder
{
	// POSITION //
	
	private double x = 0;
	private double y = 0;
	
	public AffineTransformBuilder setX(double x)
	{
		this.x = x;
		return this;
	}
	
	public AffineTransformBuilder setY(double y)
	{
		this.y = y;
		return this;
	}
	
	public AffineTransformBuilder setX(int x)
	{
		setX((double) x);
		return this;
	}
	
	public AffineTransformBuilder setY(int y)
	{
		setY((double) y);
		return this;
	}
	
	public AffineTransformBuilder setPosition(double x, double y)
	{
		setX(x);
		setY(y);
		return this;
	}
	
	public AffineTransformBuilder setPosition(int x, int y)
	{
		setX(x);
		setY(y);
		return this;
	}
	
	// ORIGIN //
	
	private double origin_x = 0;
	private double origin_y = 0;
	
	public AffineTransformBuilder setOriginX(double origin_x)
	{
		this.origin_x = origin_x;
		return this;
	}
	
	public AffineTransformBuilder setOriginY(double origin_y)
	{
		this.origin_y = origin_y;
		return this;
	}
	
	public AffineTransformBuilder setOriginX(int origin_x)
	{
		setOriginX((double) origin_x);
		return this;
	}
	
	public AffineTransformBuilder setOriginY(int origin_y)
	{
		setOriginY((double) origin_y);
		return this;
	}
	
	public AffineTransformBuilder setOrigin(double origin_x, double origin_y)
	{
		setOriginX(origin_x);
		setOriginY(origin_y);
		return this;
	}
	
	public AffineTransformBuilder setOrigin(int origin_x, int origin_y)
	{
		setOriginX(origin_x);
		setOriginY(origin_y);
		return this;
	}
	
	// SCALE //
	
	private double scale_x = 1;
	private double scale_y = 1;
	
	public AffineTransformBuilder setScaleX(double scale_x)
	{
		this.scale_x = scale_x;
		return this;
	}
	
	public AffineTransformBuilder setScaleY(double scale_y)
	{
		this.scale_y = scale_y;
		return this;
	}
	
	public AffineTransformBuilder setScaleX(int scale_x)
	{
		setScaleX((double) scale_x);
		return this;
	}
	
	public AffineTransformBuilder setScaleY(int scale_y)
	{
		setScaleY((double) scale_y);
		return this;
	}
	
	public AffineTransformBuilder setScale(double scale_x, double scale_y)
	{
		setScaleX(scale_x);
		setScaleY(scale_y);
		return this;
	}
	
	public AffineTransformBuilder setScale(int scale_x, int scale_y)
	{
		setScaleX(scale_x);
		setScaleY(scale_y);
		return this;
	}
	
	// ROTATION //
	
	private double rotation = 0; // radians
	
	public AffineTransformBuilder setRotationDegrees(int rotation)
	{
		setRotationDegrees((double) rotation);
		return this;
	}
	
	public AffineTransformBuilder setRotationDegrees(double rotation)
	{
		setRotationRadians(Math.toRadians(rotation));
		return this;
	}
	
	public AffineTransformBuilder setRotationRadians(int rotation)
	{
		setRotationRadians((double) rotation);
		return this;
	}
	
	public AffineTransformBuilder setRotationRadians(double rotation)
	{
		this.rotation = rotation;
		return this;
	}
	
	// BUILD //
	
	public AffineTransform build()
	{
		AffineTransform transform = new AffineTransform();
		transform.translate(x - (origin_x * scale_x), y - (origin_y * scale_y));
		transform.rotate(rotation, scale_x * origin_x, scale_y * origin_y);
		transform.scale(scale_x, scale_y);
		return transform;
	}
}
