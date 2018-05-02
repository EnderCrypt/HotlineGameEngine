package net.ddns.endercrypt.gameengine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.ddns.endercrypt.gameengine.room.RoomManager;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardManager;

public class HotlineGameEngine
{
	private final JPanel panel;
	private final JFrame frame;

	private final KeyboardManager keyboard = new KeyboardManager();

	private final RoomManager roomManager = new RoomManager();

	private final Thread fpsThread;

	@SuppressWarnings("serial")
	public HotlineGameEngine(String title)
	{
		panel = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g)
			{
				Graphics2D g2d = (Graphics2D) g;
				roomManager.getRoom().ifPresent(r -> r.draw(g2d));
			}
		};
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setMinimumSize(new Dimension(1000, 500));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		keyboard.install(frame);

		fpsThread = new Thread(new FpsThread());
		fpsThread.start();
	}

	public KeyboardManager getKeyboard()
	{
		return keyboard;
	}

	public RoomManager getRoomManager()
	{
		return roomManager;
	}

	private class FpsThread implements Runnable
	{
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
					frame.repaint();
				}
			}
			catch (InterruptedException e)
			{
				return;
			}
		}
	}
}
