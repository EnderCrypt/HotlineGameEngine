package endercrypt.hotline.engine.room;

@SuppressWarnings("serial")
public class RoomException extends RuntimeException
{
	public RoomException()
	{
		super();
	}
	
	public RoomException(String message)
	{
		super(message);
	}
	
	public RoomException(Throwable cause)
	{
		super(cause);
	}
	
	public RoomException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public RoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
