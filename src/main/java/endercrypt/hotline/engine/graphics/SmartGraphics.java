package endercrypt.hotline.engine.graphics;


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class SmartGraphics extends Graphics2dDelegation
{
	public static final StringDirection defaultStringDirection = StringDirection.RIGHT;
	
	private final AffineTransform defaultTransform;
	
	public SmartGraphics(Graphics2D g2d)
	{
		super(g2d);
		this.defaultTransform = g2d.getTransform();
		
		setRenderingHint(
			RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	// TRANSFORM //
	
	public void resetTransform()
	{
		setTransform(defaultTransform);
	}
	
	// FONT //
	
	public void setFont(String name, int style, int size)
	{
		setFont(new Font(name, style, size));
	}
	
	// DEBUG //
	
	public void drawCrossHair(int x, int y)
	{
		final int margin = 3;
		final int size = 10;
		drawPixel(x, y);
		drawRect(x - margin, y - margin, 1 + (margin * 2), 1 + (margin * 2));
		drawLine(x - margin, y - margin, x - (margin + size), y - (margin + size));
		drawLine(x + margin, y - margin, x + (margin + size), y - (margin + size));
		drawLine(x + margin, y + margin, x + (margin + size), y + (margin + size));
		drawLine(x - margin, y + margin, x - (margin + size), y + (margin + size));
	}
	
	// SHAPES //
	
	public void drawPixel(int x, int y)
	{
		drawLine(x, y, x, y);
	}
	
	// IMAGE //
	
	public void drawImage(Image img, int x, int y)
	{
		drawImage(img, x, y, null);
	}
	
	public void drawImage(Image image, AffineTransform transform)
	{
		AffineTransform currentTransform = getTransform();
		setTransform(transform);
		drawImage(image, 0, 0);
		setTransform(currentTransform);
	}
	
	// STRING //
	
	public void drawString(String text, StringDirection direction, int x, int y)
	{
		FontMetrics fontMetrics = getFontMetrics();
		int width = fontMetrics.stringWidth(text);
		int offset = (int) (width * direction.getAlign());
		drawString(text, x + offset, y);
	}
	
	public void drawString(String text, StringDirection direction, AffineTransform transform)
	{
		AffineTransform currentTransform = getTransform();
		setTransform(transform);
		drawString(text, direction, 0, 0);
		setTransform(currentTransform);
	}
	
	public void drawString(String text, AffineTransform transform)
	{
		drawString(text, defaultStringDirection, transform);
	}
	
	public void drawWrappedString(String text, int x, int y, int width, int height)
	{
		drawWrappedString(text, defaultStringDirection, x, y, width, height);
	}
	
	public void drawWrappedString(String text, StringDirection direction, int x, int y, int width, int height)
	{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		SmartGraphics graphics = new SmartGraphics(image.createGraphics());
		graphics.setColor(getColor());
		graphics.setFont(getFont());
		FontMetrics fontMetrics = getFontMetrics();
		
		int px = (int) -(direction.getAlign() * width);
		int py = fontMetrics.getAscent();
		
		for (String line : wrapText(fontMetrics, text, width))
		{
			graphics.drawString(line, direction, px, py);
			py += fontMetrics.getHeight();
		}
		
		graphics.dispose();
		drawImage(image, x, y);
	}
	
	private static List<String> wrapText(FontMetrics fontMetrics, String text, int width)
	{
		StringWrapper stringWrapper = new StringWrapper(fontMetrics, text, width);
		stringWrapper.run();
		return stringWrapper.getLines();
	}
	
	private static class StringWrapper
	{
		private boolean DEBUG = false;
		
		private int last_space = 0;
		private int index = 0;
		private StringBuilder sb = new StringBuilder();
		private List<String> lines = new ArrayList<>();
		
		private FontMetrics fontMetrics;
		private String text;
		private int width;
		
		public StringWrapper(FontMetrics fontMetrics, String text, int width)
		{
			this.fontMetrics = fontMetrics;
			this.text = text;
			this.width = width;
		}
		
		private void println(String text)
		{
			if (DEBUG)
			{
				System.out.println(text);
			}
		}
		
		public void run()
		{
			println("initial: " + text);
			while (text.length() > 0 && index < text.length())
			{
				char c = text.charAt(index);
				println("char: " + c);
				int sbWidth = fontMetrics.stringWidth(sb.toString());
				println("width: " + sbWidth);
				if (sbWidth >= width)
				{
					if (last_space == 0)
					{
						last_space = index - 1;
					}
					wrapText(false);
					continue;
				}
				if (c == ' ')
				{
					last_space = index;
					println("space located!");
				}
				if (c == '\n')
				{
					wrapText(true);
					continue;
				}
				index++;
				sb.append(c);
			}
			
			lines.add(text);
		}
		
		private void wrapText(boolean hard)
		{
			println("index: " + index);
			println("last space: " + last_space);
			println("sentance cut!: " + sb.toString());
			println("leftover: " + text);
			
			String extracted = sb.toString();
			if (hard == false)
			{
				extracted = extracted.substring(0, last_space);
			}
			println("extracted: " + extracted);
			sb.setLength(0);
			
			lines.add(extracted);
			
			text = text.substring(hard ? index + 1 : last_space + 1);
			println("continuing at: " + text);
			index = 0;
			last_space = 0;
		}
		
		public List<String> getLines()
		{
			return lines;
		}
	}
}
