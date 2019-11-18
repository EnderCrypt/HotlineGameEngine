package test;


import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Paths;

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
		sprite = new Sprite("res/science.png");
		
		position.x = 100;
		position.y = 100;
	}
	
	@Override
	protected void onKeyHold(int keyCode, boolean shift, boolean ctrl, boolean alt, boolean meta)
	{
		switch (keyCode)
		{
		case KeyEvent.VK_W:
			motion.y -= SPEED;
			break;
		case KeyEvent.VK_S:
			motion.y += SPEED;
			break;
		case KeyEvent.VK_A:
			motion.x -= SPEED;
			break;
		case KeyEvent.VK_D:
			motion.x += SPEED;
			break;
		case KeyEvent.VK_SPACE:
			destroy();
			break;
		case KeyEvent.VK_K:
			spriteInfo.scale_y *= 1.1;
			break;
		case KeyEvent.VK_1:
			try
			{
				Main.hotlineGameEngine.save(Paths.get("test.sav"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			break;
		case KeyEvent.VK_2:
			try
			{
				Main.hotlineGameEngine.load(Paths.get("test.sav"));
			}
			catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}
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
		spriteInfo.rotation += 2.5;
	}
}
