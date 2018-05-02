package net.ddns.endercrypt.game.engine;

import javax.swing.JFrame;

import net.ddns.endercrypt.game.room.RoomManager;

class FpsThread implements Runnable
{
	/**
	 * 
	 */
	private final JFrame jFrame;
	private final RoomManager roomManager;

	/**
	 * @param hotlineGameEngine
	 */
	FpsThread(JFrame jFrame, RoomManager roomManager)
	{
		this.jFrame = jFrame;
		this.roomManager = roomManager;
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
				if (roomManager.hasRoom())
				{
					fps = roomManager.getRoom().get().getFramerate();
				}
				Thread.sleep((int) (1000.0 / fps));

				// update
				roomManager.getRoom().ifPresent(r -> r.update());

				// draw
				jFrame.repaint();
			}
		}
		catch (InterruptedException e)
		{
			return;
		}
	}
}