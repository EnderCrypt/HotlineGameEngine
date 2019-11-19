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
	
	private Position center = new Position(0, 0);
	
	public SpriteData(String path) throws IOException
	{
		this.path = path;
		
		BufferedImage defaultImage = ImageIO.read(new File(path));
		BufferedImage compatibleImage = graphicsConfiguration.createCompatibleImage(defaultImage.getWidth(), defaultImage.getHeight(), Transparency.TRANSLUCENT);
		Graphics2D g2d = compatibleImage.createGraphics();
		g2d.drawImage(defaultImage, 0, 0, null);
		g2d.dispose();
		
		image = compatibleImage;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public void setCenter(double x, double y)
	{
		setCenter(new Position(x, y));
	}
	
	public void setCenter(Position center)
	{
		this.center = center;
	}
	
	public Position getCenter()
	{
		return center;
	}
}
