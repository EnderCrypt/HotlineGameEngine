package endercrypt.hotline.engine.room;


import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

import endercrypt.hotline.engine.core.HotlineGameEngine;
import endercrypt.hotline.engine.entities.EntityManager;
import endercrypt.hotline.engine.entities.GameEntity;
import endercrypt.hotline.engine.graphics.HotlineGraphics;
import endercrypt.hotline.engine.timer.TimerManager;
import net.ddns.endercrypt.library.keyboardmanager.KeyboardEvent;
import net.ddns.endercrypt.library.position.Position;


public class Room implements Serializable
{
	private static final long serialVersionUID = -1442867546520862802L;
	
	/**
	 * 
	 */
	
	private transient HotlineGameEngine hotline;
	
	private TimerManager timers = new TimerManager();
	private EntityManager entities = new EntityManager(this);
	
	private transient View view;
	
	private long frames = 0;
	
	protected final void attach(HotlineGameEngine hotline, View view)
	{
		this.hotline = hotline;
		this.view = view;
	}
	
	public HotlineGameEngine getHotline()
	{
		return hotline;
	}
	
	public TimerManager getTimers()
	{
		return timers;
	}
	
	public EntityManager getEntities()
	{
		return entities;
	}
	
	public View getView()
	{
		return view;
	}
	
	public long getFrames()
	{
		return frames;
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
		frames++;
		
		// update timers
		timers.update(frames);
		
		// update entities
		for (GameEntity gameEntity : entities.getAllEntities().toArray(GameEntity[]::new))
		{
			gameEntity.update();
		}
		
		// update view
		view.update();
	}
	
	public void draw(HotlineGraphics graphics)
	{
		// transform for view
		Position viewTranslation = view.getTranslation(getHotline());
		AffineTransform transform = new AffineTransform();
		transform.translate(viewTranslation.x, viewTranslation.y);
		graphics.setTransform(transform);
		
		// draw entities
		graphics.setColor(Color.WHITE);
		for (GameEntity gameEntity : entities.getAllEntitiesByDepth().toArray(GameEntity[]::new))
		{
			gameEntity.draw(graphics);
		}
		
		// draw hud
		graphics.resetTransform();
		graphics.setColor(Color.WHITE);
		for (GameEntity gameEntity : entities.getAllEntitiesByDepth().toArray(GameEntity[]::new))
		{
			gameEntity.drawHud(graphics);
		}
	}
}
