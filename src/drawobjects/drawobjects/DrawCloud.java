package drawobjects.drawobjects;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import core.Screen;
import drawobjects.drawobjects.DrawPoint;
import misc.Debug;
import misc.math.Vec3D;
import misc.math.Point;
import view.Cam;

public class DrawCloud extends DrawPoint
{
	private BufferedImage image;

	public DrawCloud(String name, Vec3D position)
	{
		super(name, position);
		File f = new File("res/drawobjects/cloud.png");
		if (f.isFile())
		{
			try
			{
				image = ImageIO.read(f);
			}
			catch (Exception e)
			{
				Debug.warn("DrawCloud<init>(): Failed to load Image");
			}
		}
		else
		{
			Debug.warn("DrawCloud<init>(): File " + f + " is not a File");
		}
	}

	@Override public void render(Cam cam)
	{
		if (getVisible())
		{
			Debug.warnIf(image == null, "DrawCloud.render(): image == null");
			Point point = from3Dto2D(getPosition(), cam);
			double m = Vec3D.getFromTo(cam.getPosition(), getPosition()).getMagnitude();
			Point newSize = new Point((int) (image.getWidth()/m), (int) (image.getHeight()/m));
			point.subtract(newSize.getX() / 2, newSize.getY() / 2);
			Screen.g().drawImage(image, point.getX(), point.getY(), newSize.getX(), newSize.getY(), null);
		}
	}
}
