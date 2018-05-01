package net.ddns.endercrypt.gameengine.sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class Sprite implements Serializable
{
	private static final long serialVersionUID = -4503798745690910893L;
	/**
	 * 
	 */

	private File file;

	private transient BufferedImage image;

	public Sprite(File file)
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
