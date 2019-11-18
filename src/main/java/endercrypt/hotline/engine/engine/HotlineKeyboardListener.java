package endercrypt.hotline.engine.engine;

import endercrypt.hotline.engine.room.RoomManager;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardEvent;
import net.ddns.endercrypt.library.keyboardmanager.listener.KeyboardListener;

public class HotlineKeyboardListener implements KeyboardListener
{
	private final RoomManager roomManager;

	public HotlineKeyboardListener(RoomManager roomManager)
	{
		this.roomManager = roomManager;
	}

	@Override
	public void trigger(KeyboardEvent keyboardEvent)
	{
		roomManager.getRoom().ifPresent(r -> r.triggerKeyEvent(keyboardEvent));
	}
}
