package net.ddns.endercrypt.gameengine.entities;

import java.awt.Graphics2D;
import java.io.Serializable;

import net.ddns.endercrypt.gameengine.room.Room;

public abstract class GameEntity implements Serializable
{
	private static final long serialVersionUID = -92455754043759309L;

	/**
	 * 
	 */

	Room roomContext; // default

	public void update()
	{
		// TODO: implement
	}

	public void draw(Graphics2D g2d)
	{
		// TODO: implement
	}
}
