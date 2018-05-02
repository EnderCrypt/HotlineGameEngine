package net.ddns.endercrypt.game.room;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

import net.ddns.endercrypt.game.entities.GameEntities;
import net.ddns.endercrypt.game.entities.GameEntity;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardEvent;
import net.ddns.endercrypt.library.position.Position;

public class Room implements Serializable
{
	private static final long serialVersionUID = -1442867546520862802L;

	/**
	 * 
	 */

	private int framerate = 60;

	private GameEntities entities = new GameEntities(this);

	protected View view;

	public Room()
	{

	}

	public Room(GameEntity initGameEntity)
	{
		this();
		entities.add(initGameEntity);
	}

	public View getView()
	{
		return view;
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
		Position viewTranslation = view.getTranslation();
		AffineTransform transform = new AffineTransform();
		transform.translate(viewTranslation.x, viewTranslation.y);
		g2d.setTransform(transform);

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
