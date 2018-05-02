package net.ddns.endercrypt;

import java.io.FileNotFoundException;

import net.ddns.endercrypt.gameengine.HotlineGameEngine;
import net.ddns.endercrypt.gameengine.entities.sprite.SpriteManager;

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
