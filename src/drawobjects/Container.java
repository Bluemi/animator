package drawobjects;

import java.util.LinkedList;

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

	@Override public void changePosition(Vec3D diff)
	{
		for (DrawObject component : getComponents())
		{
			component.changePosition(diff);
		}
	}
}
