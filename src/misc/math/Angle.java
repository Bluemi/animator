/*
	speichert einen Winkel mit minimal 0° und maximal 360°
*/

package misc.math;

public class Angle
{
	double angle;

	// constructor
	public Angle()
	{
		this(0);
	}

	public Angle(double a)
	{
		setDegrees(a);
	}

	// Math
	public void AddDegrees(double a)
	{
		setDegrees(a + getDegrees());
	}

	public void AddRadians(double a)
	{
		setRadians(a + getRadians());
	}

	// Setter
	public void setDegrees(double a)
	{
		while(a < 0)
		{
			a += 360;
		}

		while(a > 360)
		{
			a -= 360;
		}

		angle = a;
	}

	public void setRadians(double a)
	{
		setDegrees((a * 180) / Math.PI);
	}

	// Getter
	public double getDegrees() { return angle; }
	public double getDegrees180()
	{
		if (getDegrees() > 180)
		{
			return getDegrees() - 360;
		}
		else
		{
			return angle;
		}
	}
	public double getRadians() { return (Math.PI / 180) * getDegrees(); }
	public String toString() { return getDegrees() + "°"; }
}
