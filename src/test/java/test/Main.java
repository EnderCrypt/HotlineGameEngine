package test;


import java.io.IOException;

import endercrypt.hotline.engine.core.HotlineGameEngine;
import endercrypt.hotline.engine.sprite.SpriteException;
import endercrypt.hotline.engine.sprite.SpriteManager;


public class Main
{
	public static HotlineGameEngine hotline;
	
	public static void main(String[] args) throws SpriteException, IOException
	{
		hotline = new HotlineGameEngine();
		hotline.getWindow().setTitle("Test game");
		
		SpriteManager.registerImage("res/bomb.png");
		SpriteManager.registerImage("res/science.png");
		
		hotline.getRoomManager().startRoom(new TestEntity());
	}
}
