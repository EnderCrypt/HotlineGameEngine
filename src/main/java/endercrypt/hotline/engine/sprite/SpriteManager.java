package endercrypt.hotline.engine.sprite;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class SpriteManager
{
	private static Map<Path, SpriteContainer> images = new HashMap<>();
	
	public static SpriteContainer registerImage(String file) throws SpriteException
	{
		return registerImage(Paths.get(file));
	}
	
	public static synchronized SpriteContainer registerImage(Path file) throws SpriteException
	{
		if (Files.exists(file) == false)
		{
			throw new SpriteException(file.toString());
		}
		
		if (images.containsKey(file))
		{
			throw new SpriteException("Sprite already added");
		}
		
		SpriteContainer spriteContainer = new SpriteContainer();
		images.put(file, spriteContainer);
		
		return spriteContainer;
	}
	
	public static SpriteContainer getSpriteContainer(Path file)
	{
		return images.get(file);
	}
}
