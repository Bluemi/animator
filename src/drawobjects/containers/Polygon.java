/*
	Polygon stellt ein Objekt mit beliebig vielen Punkten dar, die mit beliebig vielen Linien (Lines) verbunden werden können.
*/

package drawobjects.containers;

import java.util.LinkedList;

import drawobjects.Container;
import drawobjects.drawobjects.DrawLine;
import misc.math.Vec3D;
import misc.Debug;

public abstract class Polygon extends Container
{
	private LinkedList<Vec3D> points;

	protected Polygon(String name)
	{
		super(name);
		points = new LinkedList<Vec3D>();
	}

	protected void addLine(int index1, int index2)
	{

		addLine(getPoints().get(index1), getPoints().get(index2));
	}

	protected void addLine(Vec3D point1, Vec3D point2)
	{
		addLine("line" + getPoints().size(), point1, point2);
	}

	protected void addLine(String name, Vec3D point1, Vec3D point2)
	{
		if (point1 == null)
		{
			Debug.warn("Polygon.addLine(): point1 == null");
			return;
		}
		if (point2 == null)
		{
			Debug.warn("Polygon.addLine(): point2 == null");
			return;
		}
		getComponents().add(new DrawLine(name, point1, point2));
	}

	protected LinkedList<Vec3D> getPoints() { return points; }
}
