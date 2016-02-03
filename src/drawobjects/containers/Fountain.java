package drawobjects.containers;

import drawobjects.containers.DrawCube;
import drawobjects.drawobjects.DrawCloud;
import misc.math.Vec3D;
import misc.Randomizer;

public class Fountain extends DrawCube
{
	private int interval, counter;

	public Fountain(String name, Vec3D position, Vec3D size, int interval)
	{
		super(name, position, size);
		this.interval = interval;
		counter = interval;
		
	}

	@Override public void tick()
	{
		super.tick();
		if (counter < 0)
		{
			createCloud();
			counter = interval;
		}
		else
		{
			counter--;
		}
	}

	private void createCloud()
	{
		DrawCloud cloud = new DrawCloud("c" + (getComponents().size() - 12), new Vec3D(getCenter()));
		cloud.setSpeed(new Vec3D(Randomizer.getDoubleBetween(-0.1, 0.1), Randomizer.getDoubleBetween(-0.1, 0.1), Randomizer.getDoubleBetween(1.0, 1.2)));
		getComponents().add(cloud);
	}
}
