package endercrypt.hotline.engine.engine;


import javax.swing.JFrame;

import endercrypt.hotline.engine.room.RoomManager;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardManager;


class FpsThread implements Runnable
{
	private static final double FPS = 60;
	
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
		double frameDelta = 1000.0 / FPS;
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
			keyboardManager.triggerHeldKeys();
			
			// update
			roomManager.getRoom().ifPresent(r -> r.update());
			
			// draw
			jFrame.repaint();
		}
	}
}
