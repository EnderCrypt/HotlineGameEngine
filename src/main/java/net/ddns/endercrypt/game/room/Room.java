package net.ddns.endercrypt.game.room;

import java.awt.Graphics2D;
import java.io.Serializable;

import net.ddns.endercrypt.game.entities.GameEntities;
import net.ddns.endercrypt.game.entities.GameEntity;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardEvent;

public class Room implements Serializable
{
	private static final long serialVersionUID = -1442867546520862802L;

	/**
	 * 
	 */

	private int framerate = 30;

	private GameEntities entities = new GameEntities(this);

	public Room()
	{

	}

	public Room(GameEntity initGameEntity)
	{
		this();
		entities.add(initGameEntity);
	}

	public GameEntities entities()
	{
		return entities;
	}

	public void triggerKeyEvent(KeyboardEvent keyboardEvent)
	{
		for (GameEntity gameEntity : entities.getAllEntities().toArray(GameEntity[]::new))
		{
			gameEntity.triggerKeyEvent(keyboardEvent);
		}
	}

	public void update()
	{
		for (GameEntity gameEntity : entities.getAllEntities().toArray(GameEntity[]::new))
		{
			gameEntity.update();
		}
	}

	public void draw(Graphics2D g2d)
	{
		for (GameEntity gameEntity : entities.getAllEntities().toArray(GameEntity[]::new))
		{
			gameEntity.draw(g2d);
		}
	}

	public void setFramerate(int framerate)
	{
		this.framerate = framerate;
	}

	public int getFramerate()
	{
		return framerate;
	}
}
