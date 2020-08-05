package endercrypt.hotline.engine.sprite;


import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.ddns.endercrypt.library.position.Position;


public class SpriteData
{
	private static GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
	private static GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
	
	private final String path;
	private final BufferedImage image;
	
	private Position origin = new Position(0, 0);
	
	public SpriteData(String path) throws IOException
	{
		this.path = path;
		
		BufferedImage rawImage = ImageIO.read(new File(path));
		image = graphicsConfiguration.createCompatibleImage(rawImage.getWidth(), rawImage.getHeight(), Transparency.TRANSLUCENT);
		Graphics2D g2d = image.createGraphics();
		g2d.drawImage(rawImage, 0, 0, null);
		g2d.dispose();
		
		setOriginCentered();
	}
	
	public String getPath()
	{
		return path;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public void setOriginCentered()
	{
		setOrigin(image.getWidth() / 2.0, image.getHeight() / 2.0);
	}
	
	public void setOrigin(double x, double y)
	{
		setOrigin(new Position(x, y));
	}
	
	public void setOrigin(Position origin)
	{
		this.origin = origin;
	}
	
	public Position getOrigin()
	{
		return origin;
	}
}
