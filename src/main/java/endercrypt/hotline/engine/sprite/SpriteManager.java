package endercrypt.hotline.engine.sprite;


import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class SpriteManager
{
	private static GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
	private static GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
	
	private static Map<Path, BufferedImage> images = new HashMap<>();
	
	public static void registerImage(String file) throws SpriteException, IOException
	{
		registerImage(Paths.get(file));
	}
	
	public static synchronized void registerImage(Path file) throws SpriteException, IOException
	{
		if (Files.exists(file) == false)
		{
			throw new FileNotFoundException("sprite " + file + " not found");
		}
		
		if (images.containsKey(file))
		{
			throw new SpriteException("Sprite already added");
		}
		
		BufferedImage image = ImageIO.read(file.toFile());
		BufferedImage compatibleImage = graphicsConfiguration.createCompatibleImage(image.getWidth(), image.getHeight(), Transparency.TRANSLUCENT);
		Graphics2D g2d = compatibleImage.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		
		images.put(file, compatibleImage);
	}
	
	public static BufferedImage getImage(Path file)
	{
		return images.get(file);
	}
}
