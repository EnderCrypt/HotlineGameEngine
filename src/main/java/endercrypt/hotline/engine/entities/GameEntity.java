package endercrypt.hotline.engine.entities;


import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import endercrypt.hotline.engine.room.Room;
import endercrypt.hotline.engine.sprite.Sprite;
import endercrypt.hotline.engine.sprite.SpriteInfo;
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
	
	protected Sprite sprite = null;
	protected final SpriteInfo spriteInfo = new SpriteInfo();
	protected int depth = 0;
	
	protected Position position;
	protected Motion motion;
	
	private long framesAlive = 0;
	private Map<Long, Queue<EntityTimer>> timers = new HashMap<>();
	
	public GameEntity()
	{
		position = new Position(0.0, 0.0);
		motion = new Motion(0.0, 0.0);
	}
	
	// INIT //
	
	protected final void attach(Room room)
	{
		if (this.room == room)
		{
			throw new EntityAlreadyUsedException("The entity " + this + " is already attached to this room");
		}
		if (this.room != null)
		{
			throw new EntityAlreadyUsedException("The entity " + this + " is already attached to room " + this.room);
		}
		this.room = room;
		onCreate();
	}
	
	public Room getRoom()
	{
		return room;
	}
	
	// METHODS //
	
	public long getFramesAlive()
	{
		return framesAlive;
	}
	
	private Queue<EntityTimer> getTimerQueue(long frame)
	{
		Queue<EntityTimer> queue = timers.get(frame);
		if (queue == null)
		{
			queue = new ArrayDeque<>();
			timers.put(frame, queue);
		}
		return queue;
	}
	
	protected void setTimer(int frames, EntityTimer entityTimer)
	{
		long targetFrame = framesAlive + frames;
		getTimerQueue(targetFrame).add(entityTimer);
	}
	
	public void destroy()
	{
		getRoom().entities().remove(this);
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
		// frames & timer //
		framesAlive++;
		Queue<EntityTimer> queue = getTimerQueue(framesAlive);
		if (queue != null)
		{
			for (EntityTimer entityTimer : queue)
			{
				try
				{
					entityTimer.call();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			timers.remove(framesAlive);
		}
		
		// motion & position //
		position.add(motion);
		
		// update
		onUpdate();
	}
	
	public void onUpdate()
	{
		// ignore
	}
	
	public void draw(Graphics2D g2d)
	{
		if (sprite != null)
		{
			sprite.draw(g2d, position, spriteInfo);
		}
	}
	
	@SuppressWarnings("unused")
	public void drawHud(Graphics2D g2d)
	{
		// ignore
	}
}
