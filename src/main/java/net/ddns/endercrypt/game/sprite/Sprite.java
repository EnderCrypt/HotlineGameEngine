package net.ddns.endercrypt.game.sprite;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.ddns.endercrypt.library.position.Position;

public class Sprite implements Serializable
{
	private static final long serialVersionUID = -4503798745690910893L;
	/**
	 * 
	 */

	private static Map<File, Sprite> sprites = new HashMap<>();

	public static Sprite get(String file)
	{
		return get(new File(file));
	}

	public static Sprite get(File file)
	{
		Sprite sprite = sprites.get(file);
		if (sprite == null)
		{
			if (SpriteManager.validFiles.contains(file) == false)
			{
				throw new SpriteNotLoadedException("the file " + file + " has not been loaded");
			}
			sprite = new Sprite(file);
			sprites.put(file, sprite);
		}
		return sprite;
	}

	private File file;

	private Position center = new Position(0.0, 0.0);

	private transient BufferedImage image;

	private Sprite(File file)
	{
		this.file = file;
	}

	public File getFile()
	{
		return file;
	}

	public void setCenter(double x, double y)
	{
		center.x = x;
		center.y = y;
	}

	public Position getCenter()
	{
		return center;
	}

	public void draw(Graphics2D g2d, AffineTransform transform)
	{
		if (image == null)
		{
			image = SpriteManager.getImage(file);
		}
		else
		{
			g2d.drawImage(image, transform, null);
		}
	}

}
