package drawobjects;

import java.util.LinkedList;
import java.awt.Color;

import drawobjects.DrawObject;
import misc.Debug;
import misc.math.Vec3D;
import view.Cam;

public abstract class Container extends DrawObject
{
	private LinkedList<DrawObject> components;

	public Container(String name)
	{
		super(name);
		components = new LinkedList<DrawObject>();
		initComponents();
	}

	@Override public void tick()
	{
		super.tick();
		for (int i = 0; i < getComponents().size(); i++)
		{
			getComponents().get(i).tick();
		}
	}

	// Wird von den Unterklassen überschrieben, um die components zu erstellen
	protected abstract void initComponents();

	@Override public void render(Cam cam)
	{
		if (getVisible())
		{
			for (DrawObject component : getComponents())
			{
				component.render(cam);
			}
		}
	}

	protected final LinkedList<DrawObject> getComponents()
	{
		Debug.warnIf(components == null, "DrawObjectContainer.getComponents(): components == null; return null");
		return components;
	}

	@Override public Vec3D getCenter()
	{
		Vec3D center = new Vec3D();
		for (int i = 0; i < getComponents().size(); i++)
		{
			center.addWith(getComponents().get(i).getCenter());
		}
		center.scalWith(1/getComponents().size());
		return center;
	}

	@Override public void changePosition(Vec3D diff)
	{
		for (DrawObject component : getComponents())
		{
			component.changePosition(diff);
		}
	}

	@Override public String[] getDescription() { return new String[] { "     " + getName() + " (Container):", "     Has " + components.size() + " Components"}; }

	@Override public Color getColor()
	{
		int c = 0;
		for (DrawObject component : getComponents())
		{
			c += component.getColor().getRGB();
		}
		return new Color(c);
	}

	@Override public void setColor(Color color)
	{
		for (DrawObject component : getComponents())
		{
			component.setColor(color);
		}
	}
}
