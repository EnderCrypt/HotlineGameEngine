package net.ddns.endercrypt.game.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.ddns.endercrypt.game.room.RoomManager;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardManager;
import net.ddns.endercrypt.library.keyboardmanager.binds.AnyKey;

public class HotlineGameEngine
{
	private final JPanel panel;
	final JFrame frame;

	private final KeyboardManager keyboard;

	final RoomManager roomManager;

	private final Thread fpsThread;

	@SuppressWarnings("serial")
	public HotlineGameEngine(String title)
	{
		// room manager
		roomManager = new RoomManager();

		// frame
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

		// keyboard listener
		keyboard = new KeyboardManager();
		keyboard.install(frame);
		keyboard.getListenerGroups().global().bind(new AnyKey(), new HotlineKeyboardListener(roomManager));

		// fps thread
		fpsThread = new Thread(new FpsThread(frame, roomManager));
		fpsThread.start();
	}

	public RoomManager getRoomManager()
	{
		return roomManager;
	}
}
