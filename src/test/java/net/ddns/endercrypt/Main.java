package net.ddns.endercrypt;

import net.ddns.endercrypt.gameengine.HotlineGameEngine;

public class Main
{
	private static HotlineGameEngine hotlineGameEngine;

	public static void main(String[] args)
	{
		hotlineGameEngine = new HotlineGameEngine("Lord Knows");
	}
}
