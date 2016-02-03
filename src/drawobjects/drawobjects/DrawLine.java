package drawobjects.drawobjects;

import java.awt.Color;

import core.Screen;
import drawobjects.DrawObject;
import misc.math.Vec3D;
import misc.math.Point;
import view.Cam;

public class DrawLine extends DrawObject
{
	private Vec3D point1, point2;

	public DrawLine(String name, Vec3D point1, Vec3D point2)
	{
		super(name);
		this.point1 = point1;
		this.point2 = point2;
	}

	@Override public void render(Cam cam)
	{
		if (getVisible())
		{
			Point pointA = from3Dto2D(point1, cam);
			Point pointB = from3Dto2D(point2, cam);
			Screen.g().setColor(Color.WHITE);
			Screen.g().drawLine(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
		}
	}

	@Override public void changePosition(Vec3D diff)
	{
		point1.addWith(diff);
		point2.addWith(diff);
	}

	@Override public Vec3D getCenter()
	{
		Vec3D center = new Vec3D(point1);
		center.addWith(point2);
		center.scalWith(0.5);
		return center;
	}
	@Override public String[] getDescription() { return new String[] {"(DrawLine) : \t" + getName(), "Position1 :\t" + point1, "Position2 :\t" + point2}; }
}
