package endercrypt.hotline.engine.sprite;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class SpriteManager
{
	private static Map<String, SpriteData> sprites = new HashMap<>();
	
	public static synchronized SpriteData registerImage(String path) throws SpriteException, IOException
	{
		if (Files.exists(Paths.get(path)) == false)
		{
			throw new FileNotFoundException("sprite " + path + " not found");
		}
		
		if (sprites.containsKey(path))
		{
			throw new SpriteException("Sprite already added");
		}
		
		SpriteData spriteData = new SpriteData(path);
		sprites.put(path, spriteData);
		return spriteData;
	}
	
	public static SpriteData getSpriteData(String path)
	{
		SpriteData image = sprites.get(path);
		if (image == null)
		{
			throw new SpriteException("Sprite not registered");
		}
		return image;
	}
}
