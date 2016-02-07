package drawobjects.containers;

import drawobjects.Container;
import drawobjects.drawobjects.DrawCloud;
import misc.math.Vec3D;
import misc.Randomizer;

public class GlobeCreator extends Container
{
	private static final double SPREAD = 0.3;
	private int interval, counter;
	private Vec3D position;
	private boolean active;

	public GlobeCreator(String name, Vec3D position, int interval)
	{
		super(name);
		this.interval = interval;
		counter = interval;
		this.position = new Vec3D(position);
		active = true;
	}

	@Override public void initComponents() { }
	@Override public void stop() { active = false; }
	@Override public void start() { active = true; }

	@Override public void tick()
	{
		super.tick();
		if (active)
		{
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
	}

	private void createCloud()
	{
		for (int i = 0; i < 10; i++)
		{
			DrawCloud cloud = new DrawCloud("c" + getComponents().size(), new Vec3D(position));
			double x = Randomizer.getDoubleBetween(-SPREAD, SPREAD);
			double y = Randomizer.getDoubleBetween(-SPREAD, SPREAD);;
			double z = Randomizer.getDoubleBetween(-SPREAD, SPREAD);;
			//cloud.setSpeed(new Vec3D(Randomizer.getDoubleBetween(-SPREAD, SPREAD), Randomizer.getDoubleBetween(-SPREAD, SPREAD), Randomizer.getDoubleBetween(1.0, 1.2)));
			//cloud.setSpeed(new Vec3D(x, y, Randomizer.getDoubleBetween(1.0, 1.2)));
			Vec3D vec = new Vec3D(x, y, z);
			vec.setMagnitude(0.4);
			cloud.setSpeed(vec);
			getComponents().add(cloud);
		}
	}
}
