package misc;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.io.File;
import javax.imageio.ImageIO;

import misc.Debug;

public enum ImageLoader
{
	CLOUD_IMAGE("res/drawobjects/cloud.png");

	private String path;
	private HashMap<Color, BufferedImage> images;

	ImageLoader(String path)
	{
		this.path = path;
		images = new HashMap<Color, BufferedImage>();
	}

	public void load(Color color)
	{
		File f = new File(path);
		if (f.isFile())
		{
			try
			{
				BufferedImage image = ImageIO.read(f);
				image = getColoredImage(image, color); // Färbt das Image
				images.put(color, image);
				Debug.note("ImageLoader.load(): loaded new Image : " + f + " with Color " + color, Debug.Tags.IMAGE_LOADER);
			}
			catch (Exception e)
			{
				Debug.warn("ImageLoader.load(): Failed to load Image " + f);
			}
		}
		else
		{
			Debug.warn("ImageLoader.load(): File " + f + " is not a File");
		}
	}

	public BufferedImage getImage(Color color)
	{
		if (!isLoaded(color))
		{
			load(color);
		}
		return images.get(color);
	}

	public static BufferedImage getColoredImage(BufferedImage img, Color color)
	{
		BufferedImage image = copyImage(img);
		int pixel;
		int c = color.getRGB();
		int sum;
		for (int x = 0; x < image.getWidth(); x++)
		{
			for (int y = 0; y < image.getHeight(); y++)
			{
				pixel = image.getRGB(x, y);
				sum = pixel+c; // kp warum das funktioniert.
				if (((pixel>>24) & 0xff) == 0) // damit transparente Bereiche transparent bleiben
					sum = 255;
				image.setRGB(x, y, sum);
			}
		}
		return image;
	}

/*
	public BufferedImage getImageCopy()
	{
		return copyImage(getImage());
	}
*/
	public static BufferedImage copyImage(BufferedImage source)
	{
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}

	public boolean isLoaded(Color color)
	{
		return images.containsKey(color);
	}
}
