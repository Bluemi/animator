package drawobjects.containers;

import drawobjects.Container;
import drawobjects.drawobjects.DrawCloud;
import misc.math.Vec3D;
import misc.Randomizer;

public class Fountain extends Container
{
	private static final double SPREAD = 0.3;
	private int interval, counter;
	private Vec3D position;

	public Fountain(String name, Vec3D position, int interval)
	{
		super(name);
		this.interval = interval;
		counter = interval;
		this.position = new Vec3D(position);
	}

	@Override public void initComponents() { }

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
		for (int i = 0; i < 10; i++)
		{
			DrawCloud cloud = new DrawCloud("c" + getComponents().size(), new Vec3D(position));
			cloud.setSpeed(new Vec3D(Randomizer.getDoubleBetween(-SPREAD, SPREAD), Randomizer.getDoubleBetween(-SPREAD, SPREAD), Randomizer.getDoubleBetween(1.0, 1.2)));
			getComponents().add(cloud);
		}
	}
}
