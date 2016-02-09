package view;

import java.awt.Color;

import misc.math.Vec3D;
import misc.math.Angle;
import view.Cam;

public class True3DCam extends Cam
{
	public static float DIF = 0.15f;
	public static float DIS = 50.f;
	private Cam camRight;
	private Cam camLeft;

	public True3DCam(String name, Vec3D position)
	{
		super(name, position);
		camRight = new Cam(getName() + "[right]");
		camLeft = new Cam(getName() + "[left]");
		camRight.setColor(new Color(2, 2, 255));
		camLeft.setColor(new Color(255, 2, 2));
	}

	public Cam getRightCam()
	{
		Vec3D positiondiff = getDirectionLeft();
		positiondiff.scalWith(-DIF);
		camRight.setPosition(getPosition());
		camRight.changePosition(positiondiff);
		//camRight.setDirectionFront(getDirectionFront());
		//camRight.rotateZ(new Angle(SPREAD));
		camRight.focusPoint(getCoolPointOfFocus());
		return camRight;
	}

	public Cam getLeftCam()
	{
		Vec3D positiondiff = getDirectionLeft();
		positiondiff.scalWith(DIF);
		camLeft.setPosition(getPosition());
		camLeft.changePosition(positiondiff);
		//camLeft.setDirectionFront(getDirectionFront());
		//camLeft.rotateZ(new Angle(-SPREAD));
		camLeft.focusPoint(getCoolPointOfFocus());
		return camLeft;
	}

	public Vec3D getCoolPointOfFocus()
	{
		Vec3D vec = new Vec3D(getPosition());
		Vec3D dir = getDirectionFront();
		dir.scalWith(DIS);
		vec.addWith(dir);
		return vec;
	}

	@Override public boolean isTrue3D() { return true; }
	@Override public String[] getDescription()
	{
		return getLeftCam().getDescription();
	}
}
