package core;

import java.util.LinkedList;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.Robot;
import java.awt.AWTException;

import core.Screen;
import drawobjects.DrawObject;
import panels.Panel;
import panels.console.Console;
import misc.Debug;
import misc.math.Point;
import misc.math.Vec3D;
import view.Cam;

public class Animator implements MouseMotionListener, KeyListener
{
	private LinkedList<Cam> cams;
	private Cam activeCam;
	private LinkedList<DrawObject> drawObjects;
	private LinkedList<Panel> panels;
	private Panel activePanel;
	private static final Point stdMousePosition = new Point(Screen.WIDTH/2,Screen.HEIGHT/2);

	public Animator()
	{
		setMousePosition(stdMousePosition);

		// Cam
		cams = new LinkedList<Cam>();
		getCams().add(new Cam("cam1", new Vec3D(-1,0,0)));
		activeCam = getCams().get(0);

		// DrawObjects
		drawObjects = new LinkedList<DrawObject>();
		
		// Panels
		panels = new LinkedList<Panel>();
		activePanel = null;
		panels.add(new Console(this));
	}

	public void tick()
	{
		for (int i = 0; i < getDrawObjects().size(); i++)
		{
			getDrawObjects().get(i).tick();
		}

		for (int j = 0; j < getCams().size(); j++)
		{
			getCams().get(j).tick();
		}
	}

	public void render()
	{
		for (int i = 0; i < getDrawObjects().size(); i++)
		{
			getDrawObjects().get(i).render(getActiveCam());
		}
		if (activePanel != null)
			activePanel.render();
	}

	@Override public void mouseMoved(MouseEvent mouseEvent)
	{
		// Speichert die Änderung der Maus Position.
		Point mouseMove = new Point(mouseEvent.getXOnScreen() - stdMousePosition.getX(), mouseEvent.getYOnScreen() - stdMousePosition.getY());

		if (!mouseMove.isNullPoint())
		{		
			if (activePanel == null)
			{
				if (activeCam != null)
				{
					activeCam.mouseMoved(mouseMove);
				}
				else
				{
					Debug.warn("Animator.mouseMoved(): activeCam == null");
				}
			}
			setMousePosition(stdMousePosition);
		}
	}

	@Override public void mouseDragged(MouseEvent me) {}

	@Override public void keyPressed(KeyEvent keyEvent)
	{
		switch (keyEvent.getExtendedKeyCode())
		{
			case KeyEvent.VK_END: // Wenn die END Taste gedrückt wird -> Programm beenden
				System.exit(0);
				break;
			case KeyEvent.VK_ESCAPE:
				activePanel = null;
				break;
			case KeyEvent.VK_F1:
				activePanel = panels.get(0);
				break;
			default:
				if (activePanel != null)
				{
					activePanel.keyPressed(keyEvent);
				}
				else
				{
					if (activeCam != null)
					{
						activeCam.keyPressed(keyEvent);
					}
				}
				break;
		}
	}

	@Override public void keyReleased(KeyEvent keyEvent)
	{
		activeCam.keyReleased(keyEvent);
	}

	@Override public void keyTyped(KeyEvent key) {}

	public void setMousePosition(Point point)
	{
		try
		{
			Robot robot = new Robot();
			robot.mouseMove(point.getX(), point.getY());
		} catch (AWTException e) 
		{
			e.printStackTrace();
		}
	}

	// returnt die Cam mit dem übergebenen Namen
	public Cam getCam(String name)
	{
		for (Cam cam : getCams())
		{
			if (cam.getName().equals(name))
			{
				return cam;
			}
		}
		return null;
	}

	public void setActiveCam(Cam cam)
	{
		activeCam = cam;
	}

	public LinkedList<DrawObject> getDrawObjects() { return drawObjects; }
	public LinkedList<Cam> getCams() { return cams; }
	public Cam getActiveCam() { return activeCam; }
}
