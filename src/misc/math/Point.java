package misc.math;

public class Point
{
	private int x, y;

	public Point()
	{
		this(0,0);
	}

	public Point(int x, int y)
	{
		setX(x);
		setY(y);
	}

	public Point(Point point)
	{
		setX(point.getX());
		setY(point.getY());
	}

	public boolean equals(Point point)
	{
		return (getX() == point.getX() && getY() == point.getY());
	}

	public void add(Point point)
	{
		setX(getX() + point.getX());
		setY(getY() + point.getY());
	}

	public void subtract(Point point)
	{
		setX(getX() - point.getX());
		setY(getY() - point.getY());
	}

	public void subtract(int x, int y)
	{
		setX(getX() - x);
		setY(getY() - y);
	}

	public Point plus(Point point)
	{
		return new Point(getX() + point.getX(), getY() + point.getY());
	}

	public Point minus(Point point)
	{
		return new Point(getX() - point.getX(), getY() - point.getY());
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public String toString() { return "(" + getX() + "|" + getY() + ")"; }
	public boolean isNullPoint() { return ((getX() == 0) && (getY() == 0)); }

	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
}
