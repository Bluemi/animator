package misc.math;

import java.text.DecimalFormat;

import misc.math.Angle;
import misc.Debug;

public class Vec3D
{
	private double x;
	private double y;
	private double z;

	// contructor
	public Vec3D()
	{
		setAll(0,0,0);
	}

	public Vec3D(double x, double y, double z)
	{
		setAll(x,y,z);
	}

	public Vec3D(Vec3D vec)
	{
		copy(vec);
	}

	// Static Getter
	public static Vec3D getNegative(Vec3D vec)
	{
		Vec3D negVec = new Vec3D(vec);
		negVec.scalWith(-1);
		return negVec;
	}

	public static Vec3D getFromTo(Vec3D a, Vec3D b)
	{
		Vec3D vec = new Vec3D(b);
		vec.subWith(a);
		return vec;
	}

	public static double getScalarProduct(Vec3D vec1, Vec3D vec2)
	{
		return (vec1.getX() * vec2.getX()) + (vec1.getY() * vec2.getY()) + (vec1.getZ() * vec2.getZ());
	}

	public static Angle getAngleBetween(Vec3D vec1, Vec3D vec2)
	{
		Angle a = new Angle();
		double m = vec1.getMagnitude() * vec2.getMagnitude();
		if (m == 0)
		{
			Debug.warn("Vec3D.getAngleBetween(): Magnitude von mindestens einem Vektor == 0. Keine Winkel Bestimmung möglich.");
			a.setDegrees(0.0f);
			return a;
		}
		a.setRadians(Math.acos((Vec3D.getScalarProduct(vec1, vec2))/m));
		return a;
	}

	// Man denke sich eine Ebene E mit dem dem Normalen-Vektor <n> und eine Gerade g mit dem Richtungsvektor v. Diese Funktion returnt den Winkel zwischen E und g.
	public static Angle getAngleBetweenNormale(Vec3D v, Vec3D n)
	{
		Angle a = new Angle();
		double m = v.getMagnitude() * n.getMagnitude();
		if (m == 0)
		{
			Debug.warn("Vec3D.getAngleBetween(): Magnitude von mindestens einem Vektor == 0. Keine Winkel Bestimmung möglich.");
			a.setDegrees(0);
			return a;
		}
		a.setRadians(Math.asin((Vec3D.getScalarProduct(v, n))/m));
		return a;
	}

	public static Vec3D getCrossProduct(Vec3D a, Vec3D b)
	{
		Vec3D vec = new Vec3D();
		vec.setX(a.getY() * b.getZ() - a.getZ() * b.getY());
		vec.setY(a.getZ() * b.getX() - a.getX() * b.getZ());
		vec.setZ(a.getX() * b.getY() - a.getY() * b.getX());
		if (vec.isNullVec())
		{
			Debug.warn("Vec3D.getCrossProduct(): return null. Vecktoren nicht linear unabhängig!");
			return null;
		}
		return vec;
	}

	// Math
	public void normalize()
	{
		if (isNullVec())
		{
			setX(1);
			Debug.warn("Vec3D.normalize(): tries to normalize nullVec. Set to (1|0|0)");
			return;
		}
		scalWith(1/getMagnitude());
	}

	// Rotation
	public void rotateX(Angle a)
	{
		double yBackup = getY();
		double zBackup = getZ();
		setY(yBackup*Math.cos(a.getRadians()) - zBackup*Math.sin(a.getRadians()));
		setZ(yBackup*Math.sin(a.getRadians()) + zBackup*Math.cos(a.getRadians()));
	}

	public void rotateY(Angle a)
	{
		double xBackup = getX();
		double zBackup = getZ();
		setX(xBackup * Math.cos(a.getRadians()) + zBackup * Math.sin(a.getRadians()));
		setZ(xBackup * -Math.sin(a.getRadians()) + zBackup * Math.cos(a.getRadians()));
	}

	public void rotateZ(Angle a)
	{
		double xBackup = getX();
		double yBackup = getY();
		setX(xBackup*Math.cos(a.getRadians()) - yBackup*Math.sin(a.getRadians()));
		setY(xBackup*Math.sin(a.getRadians()) + yBackup*Math.cos(a.getRadians()));
	}

	public boolean rotateUpDown(Angle a)
	{
		Vec3D around = Vec3D.getCrossProduct(this, new Vec3D(0,0,1));
		if (around == null) // Falls CrossProduct nicht möglich
		{
			Debug.warn("Vec3D.rotateUpDown(): Vec3D <around> == null");
			return false;
		}
		rotateAround(a, around);
		return true;
	}

	// Lässt den Vektor this um die Ursprungsgrade mit dem Übergebenen Richtungsvektor rotieren
	public void rotateAround(Angle a, Vec3D vec)
	{
		double xBackup = getX();
		double yBackup = getY();
		double zBackup = getZ();
		setX((xBackup * (vec.getX() * vec.getX() * (1 - Math.cos(a.getRadians())) + Math.cos(a.getRadians()))) + /**/ 
			(yBackup * (vec.getX() * vec.getY() * (1 - Math.cos(a.getRadians())) - vec.getZ() * Math.sin(a.getRadians()))) + /**/ 
			(zBackup * (vec.getX() * vec.getZ() * (1 - Math.cos(a.getRadians())) + vec.getY() * Math.sin(a.getRadians()))));

		setY((xBackup * (vec.getY() * vec.getX() * (1 - Math.cos(a.getRadians())) + vec.getZ() * Math.sin(a.getRadians()))) + /**/ 
			(yBackup * (vec.getY() * vec.getY() * (1 - Math.cos(a.getRadians())) + Math.cos(a.getRadians()))) + /**/ 
			(zBackup * (vec.getY() * vec.getZ() * (1 - Math.cos(a.getRadians())) - vec.getX() * Math.sin(a.getRadians()))));

		setZ((xBackup * (vec.getZ() * vec.getX() * (1 - Math.cos(a.getRadians())) - vec.getY() * Math.sin(a.getRadians()))) + /**/
			(yBackup * (vec.getZ() * vec.getY() * (1 - Math.cos(a.getRadians())) + vec.getX() * Math.sin(a.getRadians()))) + /**/
			(zBackup * (vec.getZ() * vec.getZ() * (1 - Math.cos(a.getRadians())) + Math.cos(a.getRadians()))));
		
	}

	// Addition
	public void addWith(Vec3D vec)
	{
		addXWith(vec.getX());
		addYWith(vec.getY());
		addZWith(vec.getZ());
	}

	public void addXWith(double x) { setX(getX() + x); }
	public void addYWith(double y) { setY(getY() + y); }
	public void addZWith(double z) { setZ(getZ() + z); }

	// Subtraction
	public void subWith(Vec3D vec)
	{
		setX(getX() - vec.getX());
		setY(getY() - vec.getY());
		setZ(getZ() - vec.getZ());
	}

	// Multiplikation
	public void scalWith(double k)
	{
		setX(getX() * k);
		setY(getY() * k);
		setZ(getZ() * k);
	}

	public void multiplyWith(Vec3D vec)
	{
		setX(getX() * vec.getX());
		setY(getY() * vec.getY());
		setZ(getZ() * vec.getZ());
	}

	public void copy(Vec3D vec)
	{
		setAll(vec.getX(), vec.getY(), vec.getZ());
	}

	// Setter
	public void setMagnitude(double m)
	{
		normalize();
		scalWith(m);
	}

	public void setAll(double x, double y, double z)
	{
		setX(x);
		setY(y);
		setZ(z);
	}

	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y;	}
	public void setZ(double z) { this.z = z;	}

	// Getter
	public double getX() { return x; }
	public double getY() { return y; }
	public double getZ() { return z; }

	public boolean isNullVec() { return getMagnitude() == 0; }

	public double getMagnitude()
	{
		return (double) Math.sqrt(x*x + y*y + z*z);
	}

	public String toString()
	{
		DecimalFormat df = new DecimalFormat("#.###");
		
		return ("( " + df.format(getX()) + " | " + df.format(getY()) + " | " + df.format(getZ()) + " )");
	}
}
