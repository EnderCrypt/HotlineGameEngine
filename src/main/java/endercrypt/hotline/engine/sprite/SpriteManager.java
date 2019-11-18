package endercrypt.hotline.engine.sprite;


import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	private static GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
	private static GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
	
	private static ExecutorService executor = Executors.newCachedThreadPool();
	
	private static volatile boolean isLoading = false;
	private static BlockingQueue<Path> loadQueue = new ArrayBlockingQueue<>(100);
	
	private static Map<Path, BufferedImage> images = new HashMap<>();
	
	protected static Set<Path> validFiles = new HashSet<>();
	
	static
	{
		executor.execute(new SpriteLoader());
	}
	
	public static boolean isLoading()
	{
		return isLoading;
	}
	
	public static Sprite loadImage(String file) throws SpriteNotFoundException
	{
		return loadImage(Paths.get(file));
	}
	
	public static synchronized Sprite loadImage(Path file) throws SpriteNotFoundException
	{
		if (Files.exists(file) == false)
		{
			throw new SpriteNotFoundException(file.toString());
		}
		
		if (validFiles.contains(file) == false)
		{
			if (loadQueue.contains(file) == false)
			{
				validFiles.add(file);
				try
				{
					loadQueue.put(file);
				}
				catch (InterruptedException e)
				{
					return null;
				}
			}
		}
		
		return Sprite.get(file);
	}
	
	public static BufferedImage getImage(Path file)
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
					Path file = loadQueue.take();
					isLoading = true;
					BufferedImage image = null;
					try
					{
						image = ImageIO.read(file.toFile());
					}
					catch (IOException e)
					{
						e.printStackTrace();
						continue;
					}
					
					BufferedImage compatibleImage = graphicsConfiguration.createCompatibleImage(image.getWidth(), image.getHeight(), Transparency.TRANSLUCENT);
					Graphics2D g2d = compatibleImage.createGraphics();
					g2d.drawImage(image, 0, 0, null);
					g2d.dispose();
					
					images.put(file, compatibleImage);
				}
			}
			catch (InterruptedException e)
			{
				return;
			}
		}
	}
}
