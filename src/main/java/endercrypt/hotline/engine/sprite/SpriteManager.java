package endercrypt.hotline.engine.sprite;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class SpriteManager
{
	private static Map<Path, SpriteData> sprites = new HashMap<>();
	
	public static SpriteData registerImage(String path) throws SpriteException, IOException
	{
		return registerImage(Paths.get(path));
	}
	
	public static synchronized SpriteData registerImage(Path path) throws SpriteException, IOException
	{
		if (Files.exists(path) == false)
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
	
	public static SpriteData getSpriteData(Path file)
	{
		SpriteData image = sprites.get(file);
		if (image == null)
		{
			throw new SpriteException("Sprite not registered");
		}
		return image;
	}
}
