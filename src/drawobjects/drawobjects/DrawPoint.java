package drawobjects.drawobjects;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import core.Screen;
import drawobjects.DrawObject;
import misc.math.Vec3D;
import misc.math.Point;
import view.Cam;

public class DrawPoint extends DrawObject
{
	private Vec3D position;
	private Image image;

	public DrawPoint(String name, Vec3D position)
	{
		super(name);
		this.position = new Vec3D(position);
		image = Toolkit.getDefaultToolkit().getImage("res/drawobjects/Pixel.png");
	}

	@Override public void render(Cam cam)
	{
		if (getVisible())
		{
			Point point = from3Dto2D(position, cam);
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
}
