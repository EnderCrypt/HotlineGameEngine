package net.ddns.endercrypt.game.engine;

class FpsThread implements Runnable
{
	/**
	 * 
	 */
	private final HotlineGameEngine FpsThread;

	/**
	 * @param hotlineGameEngine
	 */
	FpsThread(HotlineGameEngine hotlineGameEngine)
	{
		FpsThread = hotlineGameEngine;
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				// sleep
				int fps = 30;
				if (FpsThread.roomManager.hasRoom())
				{
					fps = FpsThread.roomManager.getRoom().get().getFramerate();
				}
				Thread.sleep((int) (1000.0 / fps));

				// update
				FpsThread.roomManager.getRoom().ifPresent(r -> r.update());

				// draw
				FpsThread.frame.repaint();
			}
		}
		catch (InterruptedException e)
		{
			return;
		}
	}
}