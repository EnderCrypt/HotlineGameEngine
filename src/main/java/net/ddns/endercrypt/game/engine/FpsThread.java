package net.ddns.endercrypt.game.engine;

import javax.swing.JFrame;

import net.ddns.endercrypt.game.room.RoomManager;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardManager;

class FpsThread implements Runnable
{
	private final JFrame jFrame;
	private final RoomManager roomManager;
	private final KeyboardManager keyboardManager;

	FpsThread(JFrame jFrame, RoomManager roomManager, KeyboardManager keyboardManager)
	{
		this.jFrame = jFrame;
		this.roomManager = roomManager;
		this.keyboardManager = keyboardManager;
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

				// keyboard
				keyboardManager.triggerHeldKeys();

				// update
				roomManager.getRoom().ifPresent(r -> r.update());

				// view
				roomManager.getRoom().ifPresent(r -> r.getView().update());

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