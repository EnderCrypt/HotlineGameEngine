package endercrypt.hotline.engine.sprite;


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
		
		images.put(file, ImageIO.read(file.toFile()));
	}
	
	public static BufferedImage getSpriteContainer(Path file)
	{
		return images.get(file);
	}
}
