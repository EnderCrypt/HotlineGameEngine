package endercrypt.hotline.engine.entities;


import java.io.Serializable;

import endercrypt.hotline.engine.core.HotlineGameEngine;
import endercrypt.hotline.engine.graphics.HotlineGraphics;
import endercrypt.hotline.engine.room.Room;
import endercrypt.hotline.engine.sprite.Sprite;
import endercrypt.hotline.engine.sprite.SpriteInfo;
import endercrypt.hotline.engine.timer.EntityTimer;
import endercrypt.hotline.engine.timer.TimerManager;
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
	
	private Room room = null;
	private EntityState entityState = EntityState.UNATTACHED;
	
	private long framesAlive = 0;
	private TimerManager timerManager = new TimerManager();
	
	protected Sprite sprite = null;
	protected final SpriteInfo spriteInfo = new SpriteInfo();
	protected int depth = 0;
	
	protected final Position position = new Position(0.0, 0.0);
	protected final Motion motion = new Motion(0.0, 0.0);
	
	public GameEntity()
	{
		
	}
	
	// INIT //
	
	protected final void attach(Room room)
	{
		if (entityState == EntityState.DEAD)
		{
			throw new EntityException("The entity " + this + " is dead");
		}
		if (entityState == EntityState.ATTACHED)
		{
			if (this.room == room)
			{
				throw new EntityException("The entity " + this + " is already attached to this room");
			}
			else
			{
				throw new EntityException("The entity " + this + " is already attached to room " + this.room);
			}
		}
		entityState = EntityState.ATTACHED;
		this.room = room;
		onCreate();
	}
	
	public EntityState getEntityState()
	{
		return entityState;
	}
	
	public Room getRoom()
	{
		return room;
	}
	
	public HotlineGameEngine getHotline()
	{
		return getRoom().getHotline();
	}
	
	// METHODS //
	
	public long getFramesAlive()
	{
		return framesAlive;
	}
	
	protected void setTimer(int frames, EntityTimer entityTimer)
	{
		if (frames <= 0)
		{
			throw new IllegalArgumentException("frames must be more than 0");
		}
		long targetFrame = framesAlive + frames;
		timerManager.add(targetFrame, entityTimer);
	}
	
	public void destroy()
	{
		if (entityState != EntityState.ATTACHED)
		{
			throw new EntityException("cant remove an entity thats " + entityState);
		}
		getRoom().getEntities().remove(this);
		room = null;
		entityState = EntityState.DEAD;
	}
	
	// EVENTS //
	
	protected abstract void onCreate();
	
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
	
	@SuppressWarnings("unused")
	protected void onKeyPress(int keyCode, boolean shift, boolean ctrl, boolean alt, boolean meta)
	{
		// ignore
	}
	
	@SuppressWarnings("unused")
	protected void onKeyHold(int keyCode, boolean shift, boolean ctrl, boolean alt, boolean meta)
	{
		// ignore
	}
	
	@SuppressWarnings("unused")
	protected void onKeyRelease(int keyCode, boolean shift, boolean ctrl, boolean alt, boolean meta)
	{
		// ignore
	}
	
	public final void update()
	{
		// frames
		framesAlive++;
		
		// timer //
		
		timerManager.update(framesAlive);
		
		// motion & position //
		position.add(motion);
		
		// update
		onUpdate();
	}
	
	public void onUpdate()
	{
		// do nothing
	}
	
	public void draw(HotlineGraphics graphics)
	{
		drawSelf(graphics);
	}
	
	protected void drawSelf(HotlineGraphics graphics)
	{
		if (sprite != null)
		{
			sprite.draw(graphics, position, spriteInfo);
		}
	}
	
	@SuppressWarnings("unused")
	public void drawHud(HotlineGraphics graphics)
	{
		// do nothing
	}
}
