package drawobjects.drawobjects;

import java.awt.image.BufferedImage;

import core.Screen;
import drawobjects.drawobjects.DrawPoint;
import misc.Debug;
import misc.math.Vec3D;
import misc.math.Point;
import misc.ImageLoader;
import view.Cam;

public class DrawCloud extends DrawPoint
{
	public DrawCloud(String name, Vec3D position)
	{
		super(name, position);
	}

	@Override public void render(Cam cam)
	{
		if (getVisible())
		{
			BufferedImage image = ImageLoader.CLOUD_IMAGE.getImage(getRenderColor(cam));
			Debug.warnIf(image == null, "DrawCloud.render(): image == null");
			Point point = from3Dto2D(getPosition(), cam);
			double m = Vec3D.getFromTo(cam.getPosition(), getPosition()).getMagnitude();
			Point newSize = new Point((int) (image.getWidth()/m), (int) (image.getHeight()/m));
			point.subtract(newSize.getX() / 2, newSize.getY() / 2);
			Screen.g().drawImage(image, point.getX(), point.getY(), newSize.getX(), newSize.getY(), null);
		}
	}
}
