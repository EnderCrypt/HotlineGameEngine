package net.ddns.endercrypt.game.entities;

import java.awt.Graphics2D;
import java.io.Serializable;

import net.ddns.endercrypt.game.room.Room;
import net.ddns.endercrypt.game.sprite.Sprite;
import net.ddns.endercrypt.library.keyboardmanager.BindType;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardEvent;
import net.ddns.endercrypt.library.position.Motion;
import net.ddns.endercrypt.library.position.Position;

public abstract class GameEntity implements Serializable
{
	private static final long serialVersionUID = -92455754043759309L;

	/**
	 * 
	 */

	private Room roomContext = null;

	protected Sprite sprite = null;

	protected Position position;
	protected Motion motion;

	public GameEntity()
	{
		position = new Position(0.0, 0.0);
		motion = new Motion(0.0, 0.0);
	}

	protected abstract void onCreate();

	protected final void setRoomContext(Room room)
	{
		this.roomContext = room;
		onCreate();
	}

	protected Room getRoomContext()
	{
		return roomContext;
	}

	public final void triggerKeyEvent(KeyboardEvent event)
	{
		int keyCode = event.getKeyCode();
		boolean shift = event.isShiftHeld();
		boolean ctrl = event.isCtrlHeld();
		boolean alt = event.isAltHeld();
		boolean meta = event.isMetaHeld();
		switch (event.getBindType())
		{
		case PRESS:
			onKeyPress(keyCode, shift, ctrl, alt, meta);
			break;
		case HOLD:
			onKeyHold(keyCode, shift, ctrl, alt, meta);
			break;
		case RELEASE:
			onKeyRelease(keyCode, shift, ctrl, alt, meta);
			break;
		default:
			throw new RuntimeException("Unknown enum " + BindType.class.getSimpleName() + "." + event.getBindType().toString());
		}
	}

	protected void onKeyPress(int keyCode, boolean shift, boolean ctrl, boolean alt, boolean meta)
	{
		// ignore
	}

	protected void onKeyHold(int keyCode, boolean shift, boolean ctrl, boolean alt, boolean meta)
	{
		// ignore
	}

	protected void onKeyRelease(int keyCode, boolean shift, boolean ctrl, boolean alt, boolean meta)
	{
		// ignore
	}

	public void update()
	{
		position.add(motion);
	}

	public void draw(Graphics2D g2d)
	{
		if (sprite != null)
		{
			sprite.draw(g2d, position);
		}
	}
}
