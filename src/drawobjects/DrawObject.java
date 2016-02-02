/*
	Stellt ein Object das, dass gerendert werden kann.
	Unterklassen überschreiben die render Funktion.
	Zum rendern wird die Cam übergeben. Nicht der Screen -> man kann nur einen Screen benutzen
*/

package drawobjects;

import core.Screen;
import view.Cam;
import misc.Nameable;
import misc.math.Point;
import misc.math.Vec3D;
import misc.math.Angle;
import misc.Debug;

public abstract class DrawObject implements Nameable
{
	private String name;
	private Vec3D speed;
	private Vec3D drag;
	private boolean visible;

	protected DrawObject(String name)
	{
		this.name = name;
		speed = new Vec3D(); // Da in setSpeed 'speed.copy' aufgerufen wird, muss erstmal das Object erstellt werden
		drag = new Vec3D(); // Dito
		setSpeed(getStartSpeed());
		setDrag(getStartDrag());
		setVisible(getDefaultVisible());
	}

	public abstract void render(Cam cam);

	public void tick()
	{
		applyDrag();
		changePosition(getSpeed());
	}

	protected void applyDrag()
	{
		getSpeed().scalWith(getDrag());
	}

	protected Point from3Dto2D(Vec3D drawPoint, Cam cam)
	{
		Vec3D camToPoint = Vec3D.getFromTo(cam.getPosition(), drawPoint); // Vektor von der Cam zum Punkt, der gemalt werden soll
		Vec3D PPDash = new Vec3D(cam.getDirectionTop());

		Vec3D PDash = new Vec3D();

		double difEPDash = Vec3D.getScalarProduct(cam.getDirectionTop(), camToPoint);
		PPDash.setMagnitude(-difEPDash);
		PDash.copy(drawPoint);

		PDash.addWith(PPDash);

		Vec3D camToPDash = Vec3D.getFromTo(cam.getPosition(), PDash);

		double beta = Vec3D.getAngleBetween(camToPDash, cam.getDirectionFront()).getDegrees();

		Vec3D hilfsVec = new Vec3D();
		hilfsVec.copy(drawPoint);
		hilfsVec.subWith(cam.getPosition());

		if (Vec3D.getScalarProduct(hilfsVec, cam.getDirectionLeft()) > 0)
		{
			beta = -beta;
		}

		// Gamma berechnen
		//Angle gamma = new Angle();
		double gamma;

		if (!camToPoint.isNullVec())
		{
			gamma = Math.toDegrees((Math.asin(difEPDash / camToPoint.getMagnitude())));
		}
		else
		{
			gamma = 0;
		}

		return degToKoordinates(beta, gamma, cam);
	}

	private Point degToKoordinates(double beta, double gamma, Cam cam)
	{
		Point point = new Point();
		point.setX((int) ((Screen.WIDTH/(2*cam.getViewingRange().getX())) * beta + (Screen.WIDTH/2)));
		point.setY((int) (((-Screen.HEIGHT)/(2*cam.getViewingRange().getY())) * gamma + (Screen.HEIGHT/2)));
		return point;
	}

	public abstract void changePosition(Vec3D diff);

	// getter
	@Override public String getName() { return name; }
	public boolean getDefaultVisible() { return true; }
	public boolean getVisible() { return visible; }
	public Vec3D getSpeed() { return speed; }
	protected Vec3D getStartSpeed() { return new Vec3D(); } // Kann von den Unterklassen geändert werden
	protected Vec3D getStartDrag() { return new Vec3D(0.9, 0.9, 0.9); }
	protected Vec3D getDrag() { return drag; }
	public abstract String[] getDescription();

	// setter
	public void setVisible(boolean v) { visible = v; }
	public void setSpeed(Vec3D s)
	{
		Debug.warnIf(s == null, "DrawObject.setSpeed(): s == null");
		speed.copy(s);
	}
	public void setDrag(Vec3D d)
	{
		Debug.warnIf(d == null, "DrawObject.setDrag(): d == null");
		drag.copy(d);
	}
}
