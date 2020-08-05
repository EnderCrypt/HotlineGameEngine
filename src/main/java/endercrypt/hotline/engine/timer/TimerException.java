package endercrypt.hotline.engine.timer;

@SuppressWarnings("serial")
public class TimerException extends RuntimeException
{
	public TimerException()
	{
		super();
	}
	
	public TimerException(String message)
	{
		super(message);
	}
	
	public TimerException(Throwable cause)
	{
		super(cause);
	}
	
	public TimerException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public TimerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
