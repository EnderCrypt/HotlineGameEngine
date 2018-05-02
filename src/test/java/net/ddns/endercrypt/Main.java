package net.ddns.endercrypt;

import java.io.FileNotFoundException;

import net.ddns.endercrypt.game.engine.HotlineGameEngine;
import net.ddns.endercrypt.game.sprite.SpriteManager;

public class Main
{
	private static HotlineGameEngine hotlineGameEngine;

	public static void main(String[] args) throws FileNotFoundException
	{
		SpriteManager.loadImage("res/science.png");

		hotlineGameEngine = new HotlineGameEngine("Game test");
		hotlineGameEngine.getRoomManager().startRoom(new TestEntity());
	}
}
