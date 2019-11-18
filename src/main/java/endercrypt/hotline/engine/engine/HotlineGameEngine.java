package endercrypt.hotline.engine.engine;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import endercrypt.hotline.engine.room.RoomManager;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardManager;
import net.ddns.endercrypt.library.keyboardmanager.binds.AnyKey;


public class HotlineGameEngine
{
	private final JPanel panel;
	private final JFrame frame;
	
	private final KeyboardManager keyboard;
	
	private final RoomManager roomManager;
	
	private final Thread fpsThread;
	
	public HotlineGameEngine(String title)
	{
		// frame
		panel = new DrawJPanel();
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setSize(new Dimension(1000, 500));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// room manager
		roomManager = new RoomManager(frame);
		
		// keyboard listener
		keyboard = new KeyboardManager();
		keyboard.install(frame);
		keyboard.getListenerGroups().global().bind(new AnyKey(), new HotlineKeyboardListener(roomManager));
		
		// fps thread
		fpsThread = new Thread(new FpsThread(frame, roomManager, keyboard));
		fpsThread.start();
	}
	
	public RoomManager getRoomManager()
	{
		return roomManager;
	}
	
	public void setTitle(String title)
	{
		frame.setTitle(title);
	}
	
	public void save(File file) throws FileNotFoundException, IOException
	{
		getRoomManager().save(file);
	}
	
	public void load(File file) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		getRoomManager().load(file);
	}
	
	public void dispose()
	{
		frame.dispose();
		fpsThread.interrupt();
	}
	
	@SuppressWarnings({ "unused", "serial" })
	private class DrawJPanel extends JPanel
	{
		@Override
		protected void paintComponent(Graphics g)
		{
			Graphics2D g2d = (Graphics2D) g;
			if (roomManager != null)
			{
				roomManager.getRoom().ifPresent(r -> r.draw(g2d));
			}
		}
	};
}
