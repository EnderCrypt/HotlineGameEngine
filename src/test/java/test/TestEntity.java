package test;

import java.awt.event.KeyEvent;

import endercrypt.hotline.engine.entities.GameEntity;
import endercrypt.hotline.engine.sprite.Sprite;

public class TestEntity extends GameEntity
{
	private static final long serialVersionUID = -3101929237048563872L;

	/**
	 * 
	 */

	private static final double SPEED = 1.0;

	@Override
	protected void onCreate()
	{
		sprite = Sprite.get("res/science.png");

		position.x = 100;
		position.y = 100;
	}

	@Override
	protected void onKeyHold(int keyCode, boolean shift, boolean ctrl, boolean alt, boolean meta)
	{
		switch (keyCode)
		{
		case KeyEvent.VK_UP:
			motion.y -= SPEED;
			break;
		case KeyEvent.VK_DOWN:
			motion.y += SPEED;
			break;
		case KeyEvent.VK_LEFT:
			motion.x -= SPEED;
			break;
		case KeyEvent.VK_RIGHT:
			motion.x += SPEED;
			break;
		case KeyEvent.VK_SPACE:
			destroy();
			break;
		default:
			// ignore
			break;
		}
	}

	@Override
	public void onUpdate()
	{
		motion.multiplyLength(0.8);
		rotation += 2.5;

		// getRoomContext().getView().position().set(position.x, position.y);
	}
}
