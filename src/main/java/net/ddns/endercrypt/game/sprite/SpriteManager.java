package net.ddns.endercrypt.game.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

public class SpriteManager
{
	private static ExecutorService executor = Executors.newCachedThreadPool();

	private static volatile boolean isLoading = false;
	private static BlockingQueue<File> loadQueue = new ArrayBlockingQueue<>(100);

	private static Map<File, BufferedImage> images = new HashMap<>();

	protected static Set<File> validFiles = new HashSet<>();

	static
	{
		executor.execute(new SpriteLoader());
	}

	public static boolean isLoading()
	{
		return isLoading;
	}

	public static void loadImage(String file) throws SpriteNotFoundException
	{
		loadImage(new File(file));
	}

	public static void loadImage(File file) throws SpriteNotFoundException
	{
		if (file.exists() == false)
		{
			throw new SpriteNotFoundException(file.toString());
		}

		if (loadQueue.contains(file) == false)
		{
			try
			{
				validFiles.add(file);
				loadQueue.put(file);
			}
			catch (InterruptedException e)
			{
				return;
			}
		}
	}

	public static BufferedImage getImage(File file)
	{
		return images.get(file);
	}

	private static class SpriteLoader implements Runnable
	{
		@Override
		public void run()
		{
			try
			{
				while (true)
				{
					if (loadQueue.isEmpty())
					{
						isLoading = false;
					}
					File file = loadQueue.take();
					isLoading = true;
					BufferedImage image = null;
					try
					{
						image = ImageIO.read(file);
					}
					catch (IOException e)
					{
						e.printStackTrace();
						continue;
					}

					images.put(file, image);
				}
			}
			catch (InterruptedException e)
			{
				return;
			}
		}
	}
}
