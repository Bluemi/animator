package drawobjects.containers;

import drawobjects.Container;
import drawobjects.drawobjects.DrawCloud;
import misc.Debug;
import misc.math.Vec3D;
import view.Cam;

public class DrawTrace extends Container
{
	private final int interval;
	private int counter;
	private Cam targetCam;
	private boolean active;

	public DrawTrace(String name, int interval, Cam cam)
	{
		super(name);
		this.interval = interval;
		counter = 0;
		this.targetCam = cam;
		active = true;
	}

	@Override public void initComponents() {} // Muss überschrieben werden
	@Override public void tick()
	{
		if (active)
		{
			if (counter <= 0)
			{
				addPoint();
				counter = interval;
			}
			counter--;
		}
	}

	@Override public void start() { active = true; }
	@Override public void stop() { active = false; }
	@Override public void change() { active = !active; }

	private void addPoint()
	{
		DrawCloud cloud = new DrawCloud("c" + getComponents().size(), new Vec3D(targetCam.getPosition()));
		getComponents().add(cloud);
	}
}
