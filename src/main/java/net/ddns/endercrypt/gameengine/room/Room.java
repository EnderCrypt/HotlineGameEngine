package net.ddns.endercrypt.gameengine.room;

import java.awt.Graphics2D;
import java.io.Serializable;

import net.ddns.endercrypt.gameengine.entities.GameEntities;
import net.ddns.endercrypt.gameengine.entities.GameEntity;

public class Room implements Serializable
{
	private static final long serialVersionUID = -1442867546520862802L;

	/**
	 * 
	 */

	private GameEntities entities = new GameEntities(this);

	public GameEntities entities()
	{
		return entities;
	}

	public void update()
	{
		for (GameEntity gameEntity : entities.getAllEntities())
		{
			gameEntity.update();
		}
	}

	public void draw(Graphics2D g2d)
	{
		for (GameEntity gameEntity : entities.getAllEntities())
		{
			gameEntity.draw(g2d);
		}
	}
}
