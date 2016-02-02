package view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;

import core.Screen;
import misc.Nameable;
import misc.Debug;
import misc.PointMouseMotionListener;
import misc.math.Vec3D;
import misc.math.Point;
import misc.math.Vec3D;
import misc.math.Angle;

public class Cam implements KeyListener, PointMouseMotionListener, Nameable
{
	public static final float ACCELERATION = 0.8f;
	public static final float SHIFTACCELERATION = 3.4f;
	public static final float FRICTION = 11.f; // 10 is no friction
	public static final float ROTATION_SPEED = 0.1f;

	private Vec3D position;
	private Vec3D directionFront;
	private Vec3D directionLeft;
	private Vec3D directionTop;
	private Vec3D speed;
	private Vec3D PointOfFocus;
	private String name;

	private boolean wPressed, aPressed, sPressed, dPressed, shiftPressed, spacePressed, strgPressed, ePressed;

	// constructor
	public Cam(String name)
	{
		this(name, new Vec3D(0,0,0));
	}

	public Cam(String name, Vec3D position)
	{
		this.name = name;
		this.position = new Vec3D(position);
		directionFront = new Vec3D(1,0,0);
		directionLeft = new Vec3D();
		directionTop = new Vec3D();
		defineDirectionLeft();
		defineDirectionTop();
		speed = new Vec3D();
		PointOfFocus = new Vec3D(0,0,0);
		wPressed = aPressed = sPressed = dPressed = shiftPressed = spacePressed = strgPressed = false;
	}

	public void tick()
	{
		// getDirection* erstellt automatisch einen neuen Vektor, sodass hier kein Neuer Vec3D erzeugt werden muss.
		Vec3D vecFront = getDirectionFront();
		Vec3D vecLeft = getDirectionLeft();
		Vec3D vecTop = getDirectionTop();

		if (shiftPressed)
		{
			vecFront.scalWith(SHIFTACCELERATION/10);
			vecLeft.scalWith(SHIFTACCELERATION/10);
			vecTop.scalWith(SHIFTACCELERATION/10);
		}
		else
		{
			vecFront.scalWith(ACCELERATION/10);
			vecLeft.scalWith(ACCELERATION/10);
			vecTop.scalWith(ACCELERATION/10);
		}

		if (wPressed)
		{
			speed.addWith(vecFront);
			Debug.note("W Pressed", Debug.Tags.CAM_KEYS);
		}
		if (aPressed)
		{
			speed.addWith(vecLeft);
			Debug.note("A Pressed", Debug.Tags.CAM_KEYS);
		}
		if (sPressed)
		{
			speed.addWith(Vec3D.getNegative(vecFront));
			Debug.note("S Pressed", Debug.Tags.CAM_KEYS);
		}
		if (dPressed)
		{
			speed.addWith(Vec3D.getNegative(vecLeft));
			Debug.note("D Pressed", Debug.Tags.CAM_KEYS);
		}
		if (shiftPressed)
		{
			Debug.note("Shift Pressed", Debug.Tags.CAM_KEYS);
		}
		if (spacePressed)
		{
			speed.addWith(vecTop);
			Debug.note("Space Pressed", Debug.Tags.CAM_KEYS);
		}
		if (strgPressed)
		{
			speed.addWith(Vec3D.getNegative(vecTop));
			Debug.note("Strg Pressed", Debug.Tags.CAM_KEYS);
		}

		speed.scalWith(10/FRICTION);
		position.addWith(speed);

		if (ePressed)
		{
			focusPoint(PointOfFocus);
		}
	}

	@Override public void keyTyped(KeyEvent keyEvent) {}

	@Override public void keyPressed(KeyEvent keyEvent)
	{
		switch (keyEvent.getExtendedKeyCode())
		{
			case KeyEvent.VK_W:
				wPressed = true;
				break;
			case KeyEvent.VK_A:
				aPressed = true;
				break;
			case KeyEvent.VK_S:
				sPressed = true;
				break;
			case KeyEvent.VK_D:
				dPressed = true;
				break;
			case KeyEvent.VK_E:
				ePressed = true;
				break;
			case KeyEvent.VK_SHIFT:
				shiftPressed = true;
				break;
			case KeyEvent.VK_SPACE:
				spacePressed = true;
				break;
			case KeyEvent.VK_CONTROL:
				strgPressed = true;
				break;
			default:
				//Debug.warn("Cam.onKeyPressed(): undefined Key: " + keyEvent.getKeyChar());
				break;
		}
	}

	@Override public void keyReleased(KeyEvent keyEvent)
	{
		switch (keyEvent.getExtendedKeyCode())
		{
			case KeyEvent.VK_W:
				wPressed = false;
				break;
			case KeyEvent.VK_A:
				aPressed = false;
				break;
			case KeyEvent.VK_S:
				sPressed = false;
				break;
			case KeyEvent.VK_D:
				dPressed = false;
				break;
			case KeyEvent.VK_E:
				ePressed = false;
				break;
			case KeyEvent.VK_SHIFT:
				shiftPressed = false;
				break;
			case KeyEvent.VK_SPACE:
				spacePressed = false;
				break;
			case KeyEvent.VK_CONTROL:
				strgPressed = false;
				break;
			default:
				//Debug.warn("Cam.onKeyReleased(): undefined Key: " + keyEvent.getKeyChar(), Debug.Tags.CAM_KEYS);
				break;
		}
	}

	public void mouseMoved(Point mouseMove)
	{
		rotateZ(new Angle(mouseMove.getX() * ROTATION_SPEED));
		rotateUpDown(new Angle(-mouseMove.getY() * ROTATION_SPEED));
	}

	private void rotateZ(Angle a)
	{
		directionFront.rotateZ(a);
		directionFront.normalize();

		// Neu setzen von directionLeft und DirectionTop
		defineDirectionLeftTop();
	}

	private void rotateUpDown(Angle a)
	{
		if (directionLeft != null)
		{
			directionFront.rotateAround(a, directionLeft);
			defineDirectionLeftTop();
		}
		else
		{
			Debug.warn("Cam.rotateUpDown(): directionLeft == null");
		}
	}

	public void focusPoint(Vec3D point)
	{
		Vec3D vec = Vec3D.getFromTo(position, point);
		vec.normalize();
		directionFront.copy(vec);
		defineDirectionLeftTop();
	}

	private void defineDirectionLeftTop()
	{
		defineDirectionLeft();
		defineDirectionTop();
	}

	private void defineDirectionLeft()
	{
		directionLeft.setX(directionFront.getY());
		directionLeft.setY(-directionFront.getX());
		directionLeft.normalize();
	}

	// Greift auf directionLeft zu -> defineDirectionLeft vor defineDirectionTop aufrufen
	private void defineDirectionTop()
	{
		Vec3D vecTop = Vec3D.getCrossProduct(directionLeft, directionFront);
		vecTop.normalize();
		directionTop.copy(vecTop);
	}

	// Setter
	public void setFocusPoint(Vec3D point)
	{
		if (point != null)
		{
			PointOfFocus.copy(point);
		}
		else
		{
			Debug.warn("Cam.setFocusPoint(): point is null;");
		}
	}

	// Getter
	public Vec3D getPosition() { return new Vec3D(position); }
	public Vec3D getDirectionFront() { return new Vec3D(directionFront); }
	public Vec3D getDirectionLeft() { return new Vec3D(directionLeft); }
	public Vec3D getDirectionTop() { return new Vec3D(directionTop); }
	public Vec3D getSpeed() { return new Vec3D(speed); }

	public Point getViewingRange()
	{
		// TODO schöner machen
		Point point = new Point();
		point.setX(60);
		float x = point.getX();
		point.setY((int) ((x / Screen.WIDTH) * Screen.HEIGHT));
		return point;
	}
	@Override public String getName() { return name; }
}
