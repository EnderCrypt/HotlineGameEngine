package test;

import java.io.IOException;

import endercrypt.hotline.engine.engine.HotlineGameEngine;
import endercrypt.hotline.engine.sprite.SpriteManager;

public class Main
{
	public static HotlineGameEngine hotlineGameEngine;

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException
	{
		SpriteManager.loadImage("res/bomb.png").setCenter(128, 128);
		SpriteManager.loadImage("res/science.png").setCenter(16, 16);

		hotlineGameEngine = new HotlineGameEngine("Game test");
		hotlineGameEngine.getRoomManager().startRoom(new TestEntity());
	}
}
