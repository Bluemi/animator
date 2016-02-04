package misc;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import misc.Debug;

public enum ImageLoader
{
	CLOUD_IMAGE("res/drawobjects/cloud.png");

	private boolean loaded = false;
	private String path;
	private BufferedImage image;

	ImageLoader(String path)
	{
		this.path = path;
	}

	public void load()
	{
		File f = new File(path);
		if (f.isFile())
		{
			try
			{
				image = ImageIO.read(f);
				loaded = true;
				Debug.note("ImageLoader.load(): loaded new Image : " + f, Debug.Tags.IMAGE_LOADER);
			}
			catch (Exception e)
			{
				Debug.warn("ImageLoader.load(): Failed to load Image");
			}
		}
		else
		{
			Debug.warn("ImageLoader.load(): File " + f + " is not a File");
		}
	}

	public BufferedImage getImage()
	{
		if (!isLoaded())
		{
			load();
		}
		Debug.warnIf(image == null, "ImageLoader.getImage(): image == null");
		return image;
	}

	public boolean isLoaded() { return loaded; }
}
