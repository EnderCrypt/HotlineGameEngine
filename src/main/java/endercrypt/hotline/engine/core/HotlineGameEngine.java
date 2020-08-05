package endercrypt.hotline.engine.core;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import endercrypt.hotline.engine.room.RoomManager;
import endercrypt.hotline.engine.window.HotlineWindow;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardManager;
import net.ddns.endercrypt.library.keyboardmanager.binds.AnyKey;


public class HotlineGameEngine
{
	private final RoomManager roomManager;
	
	private final HotlineWindow window;
	
	private final KeyboardManager keyboard;
	
	private final MainLoop mainLoop;
	
	public HotlineGameEngine()
	{
		// room manager
		roomManager = new RoomManager(this);
		
		// window
		window = new HotlineWindow(this);
		
		// keyboard listener
		keyboard = new KeyboardManager();
		keyboard.install(window.getFrame());
		keyboard.getListenerGroups().global().bind(new AnyKey(), new HotlineKeyboardListener(roomManager));
		
		// main loop
		mainLoop = new MainLoop(this);
		mainLoop.getThread().start();
	}
	
	// getters
	public HotlineWindow getWindow()
	{
		return window;
	}
	
	public KeyboardManager getKeyboard()
	{
		return keyboard;
	}
	
	public RoomManager getRoomManager()
	{
		return roomManager;
	}
	
	public MainLoop getMainLoop()
	{
		return mainLoop;
	}
	
	// save & load
	public void save(Path path) throws FileNotFoundException, IOException
	{
		getRoomManager().save(path);
	}
	
	public void load(Path path) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		getRoomManager().load(path);
	}
	
	// close
	public void shutdown()
	{
		getWindow().shutdown();
		getMainLoop().shutdown();
	}
}
