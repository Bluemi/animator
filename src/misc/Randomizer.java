package misc;

import java.util.Random;

public class Randomizer
{
	private static Random random = new Random(System.currentTimeMillis());

	public static double nextDouble()
	{
		return random.nextDouble();
	}

	public static double getDoubleBetween(double min, double max)
	{
		double n = nextDouble();
		double diff = max - min;
		n *= diff;
		n += min;
		return n;
	}
}
