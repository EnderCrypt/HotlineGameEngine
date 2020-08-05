package endercrypt.hotline.engine.sprite;

@SuppressWarnings("serial")
public class SpriteException extends RuntimeException
{
	public SpriteException()
	{
		super();
	}
	
	public SpriteException(String message)
	{
		super(message);
	}
	
	public SpriteException(Throwable cause)
	{
		super(cause);
	}
	
	public SpriteException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public SpriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
