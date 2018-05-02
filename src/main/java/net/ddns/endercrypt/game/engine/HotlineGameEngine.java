package net.ddns.endercrypt.game.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.ddns.endercrypt.game.room.RoomManager;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardManager;

public class HotlineGameEngine
{
	private final JPanel panel;
	final JFrame frame;

	private final KeyboardManager keyboard = new KeyboardManager();

	final RoomManager roomManager = new RoomManager();

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

		fpsThread = new Thread(new FpsThread(this));
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
}
