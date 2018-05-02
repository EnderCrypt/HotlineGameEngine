package net.ddns.endercrypt;

import net.ddns.endercrypt.game.entities.GameEntity;
import net.ddns.endercrypt.game.sprite.Sprite;

public class TestEntity extends GameEntity
{
	private static final long serialVersionUID = -3101929237048563872L;

	/**
	 * 
	 */

	@Override
	protected void onCreate()
	{
		sprite = Sprite.get("res/science.png");

		position.x = 100;
		position.y = 100;
	}

	@Override
	public void update()
	{
		super.update();
		position.x += 1;
	}
}
