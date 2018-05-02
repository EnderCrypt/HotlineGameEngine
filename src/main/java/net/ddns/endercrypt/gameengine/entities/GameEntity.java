package net.ddns.endercrypt.gameengine.entities;

import java.awt.Graphics2D;
import java.io.Serializable;

import net.ddns.endercrypt.gameengine.entities.sprite.Sprite;
import net.ddns.endercrypt.gameengine.room.Room;
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
