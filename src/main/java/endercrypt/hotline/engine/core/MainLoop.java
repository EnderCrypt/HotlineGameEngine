package endercrypt.hotline.engine.core;

class MainLoop
{
	private final HotlineGameEngine hotline;
	
	private Thread thread;
	private int fps = 60;
	
	public MainLoop(HotlineGameEngine hotline)
	{
		this.hotline = hotline;
		
		thread = new Thread(new FpsThread(), "Main loop thread");
	}
	
	public Thread getThread()
	{
		return thread;
	}
	
	public void setFps(int fps)
	{
		this.fps = fps;
	}
	
	public void shutdown()
	{
		thread.interrupt();
	}
	
	private class FpsThread implements Runnable
	{
		@Override
		public void run()
		{
			double frameDelta = 1000.0 / fps;
			double next = System.currentTimeMillis() + frameDelta;
			while (Thread.currentThread().isInterrupted() == false)
			{
				// sleep
				while (System.currentTimeMillis() <= next)
				{
					// wait
				}
				next += Math.ceil(System.currentTimeMillis() - next) * frameDelta; // catchup next to be infront of now
				
				// keyboard
				hotline.getKeyboard().triggerHeldKeys();
				
				// update
				hotline.getRoomManager().getRoom().ifPresent(r -> r.update());
				
				// draw
				hotline.getWindow().redraw();
			}
		}
	}
}
