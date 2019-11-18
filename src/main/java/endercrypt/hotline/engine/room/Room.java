package endercrypt.hotline.engine.room;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

import endercrypt.hotline.engine.entities.GameEntities;
import endercrypt.hotline.engine.entities.GameEntity;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardEvent;
import net.ddns.endercrypt.library.position.Position;


public class Room implements Serializable
{
	private static final long serialVersionUID = -1442867546520862802L;
	
	/**
	 * 
	 */
	
	private GameEntities entities = new GameEntities(this);
	
	protected View view;
	
	private long frameCount = 0;
	
	public Room()
	{
		
	}
	
	public Room(GameEntity initGameEntity)
	{
		this();
		entities.add(initGameEntity);
	}
	
	public long getFrameCount()
	{
		return frameCount;
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
		frameCount++;
		for (GameEntity gameEntity : entities.getAllEntities().toArray(GameEntity[]::new))
		{
			gameEntity.update();
		}
	}
	
	public void draw(Graphics2D g2d)
	{
		AffineTransform defaultTransform = g2d.getTransform();
		
		Position viewTranslation = view.getTranslation();
		AffineTransform transform = new AffineTransform();
		transform.translate(viewTranslation.x, viewTranslation.y);
		g2d.setTransform(transform);
		
		// draw entities
		for (GameEntity gameEntity : entities.getAllEntitiesByDepth().toArray(GameEntity[]::new))
		{
			gameEntity.draw(g2d);
		}
		
		// draw hud
		g2d.setTransform(defaultTransform);
		for (GameEntity gameEntity : entities.getAllEntities().toArray(GameEntity[]::new))
		{
			gameEntity.drawHud(g2d);
		}
	}
}
