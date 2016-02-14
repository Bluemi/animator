package drawobjects.drawobjects;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import core.Screen;
import drawobjects.DrawObject;
import misc.math.Vec3D;
import misc.math.Point;
import misc.ImageLoader;
import view.Cam;

public class DrawPoint extends DrawObject
{
	private Vec3D position;
	private Color color;

	public DrawPoint(String name, Vec3D position)
	{
		super(name);
		this.position = new Vec3D(position);
		color = Color.WHITE;
	}

	@Override public void render(Cam cam)
	{
		if (getVisible())
		{
			Point point = from3Dto2D(position, cam);
			BufferedImage image = ImageLoader.PIXEL_IMAGE.getImage(getRenderColor(cam));
			Screen.g().drawImage(image, point.getX(), point.getY(), null);
		}
	}

	@Override public void changePosition(Vec3D diff)
	{
		position.addWith(diff);
	}

	protected Vec3D getPosition() { return position; }
	@Override public Vec3D getCenter() { return new Vec3D(getPosition()); }

	@Override public String[] getDescription()
	{
		return new String[] {"(DrawPoint) \t" + getName(), "Position : \t" + position};
	}

	@Override public Color getColor() { return color; }
	@Override public void setColor(Color c) { this.color = c; }
}
