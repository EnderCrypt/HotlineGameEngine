package endercrypt.hotline.engine.timer;


import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TimerInstance implements Comparable<TimerInstance>, Serializable
{
	private static final long serialVersionUID = 3314279794244268182L;
	
	/**
	 * 
	 */
	
	private static ExecutorService executor = Executors.newCachedThreadPool();
	
	private long frame;
	private EntityTimer entityTimer;
	
	public TimerInstance(long frame, EntityTimer entityTimer)
	{
		Objects.requireNonNull(entityTimer, "entityTimer");
		if (frame < 0)
		{
			throw new IllegalArgumentException("frame cannot be negative");
		}
		this.frame = frame;
		this.entityTimer = entityTimer;
	}
	
	public long getFrame()
	{
		return frame;
	}
	
	public EntityTimer getEntityTimer()
	{
		return entityTimer;
	}
	
	public void call()
	{
		executor.execute(this::callBlocking);
	}
	
	public void callBlocking()
	{
		try
		{
			entityTimer.call();
		}
		catch (Exception e)
		{
			System.err.println("Timer exception");
			e.printStackTrace();
		}
	}
	
	@Override
	public int compareTo(TimerInstance other)
	{
		return Long.compare(frame, other.frame);
	}
	
}
