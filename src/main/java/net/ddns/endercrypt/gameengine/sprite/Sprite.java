package net.ddns.endercrypt.gameengine.sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Sprite implements Serializable
{
	private static final long serialVersionUID = -4503798745690910893L;
	/**
	 * 
	 */

	private Map<File, Sprite> sprites = new HashMap<>();

	public Sprite get(String file)
	{
		return get(new File(file));
	}

	public Sprite get(File file)
	{
		Sprite sprite = sprites.get(file);
		if (sprite == null)
		{
			SpriteManager.loadImage(file);

			sprite = new Sprite(file);
			sprites.put(file, sprite);
		}
		return sprite;
	}

	private File file;

	private transient BufferedImage image;

	private Sprite(File file)
	{
		this.file = file;
	}

	public File getFile()
	{
		return file;
	}

	public void draw(Graphics2D g2d, int x, int y)
	{
		if (image == null)
		{
			image = SpriteManager.getImage(file);
		}
		else
		{
			g2d.drawImage(image, x, y, null);
		}
	}
}
