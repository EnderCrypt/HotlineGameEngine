package endercrypt.hotline.engine.timer;


import java.util.PriorityQueue;


public class TimerManager
{
	private PriorityQueue<TimerInstance> timers = new PriorityQueue<TimerInstance>();
	
	public void add(long frame, EntityTimer timer)
	{
		timers.add(new TimerInstance(frame, timer));
	}
	
	public void update(long frame)
	{
		while (true)
		{
			TimerInstance timerInstance = timers.peek();
			if (timerInstance == null)
			{
				break;
			}
			if (timerInstance.getFrame() < frame)
			{
				throw new TimerException("missed timer from " + timerInstance.getFrame() + " (current frame: " + frame + ")");
			}
			if (timerInstance.getFrame() > frame)
			{
				break;
			}
			if (timerInstance.getFrame() == frame)
			{
				timers.remove();
				timerInstance.call();
			}
		}
	}
}
