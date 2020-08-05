package endercrypt.hotline.engine.graphics;

public enum StringDirection
{
	LEFT(-1.0), CENTER(-0.5), RIGHT(0);
	
	private final double align;
	
	private StringDirection(double align)
	{
		this.align = align;
	}
	
	public double getAlign()
	{
		return align;
	}
}
