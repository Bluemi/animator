package drawobjects.drawobjects;

import drawobjects.drawobjects.DrawCloud;
import misc.math.Vec3D;

public class TwinklingDrawCloud extends DrawCloud
{
	private int counter;
	public static final int INTERVAL = 2;

	public TwinklingDrawCloud(String name, Vec3D position)
	{
		super(name, position);
		counter = INTERVAL;
	}

	@Override public void tick()
	{
		super.tick();
		if (counter <= 0)
		{
			counter = INTERVAL;
			setVisible(!getVisible());
		}
		counter--;
	}
}
