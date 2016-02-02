package panels.console.commands.basic;

import drawobjects.drawobjects.DrawPoint;
import panels.console.Console;
import panels.console.commands.BasicCommand;
import misc.math.Vec3D;

public class CreateDrawPoint extends BasicCommand
{
	@Override public void execute(Console console, String[] args)
	{
		if (args.length == 4)
		{
			console.getAnimator().getDrawObjects().add(new DrawPoint(args[0], new Vec3D(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]))));
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "point"; }
	private void printUsage(Console console)
	{
		console.write("Usage");
		console.write("     " + getName() + " <Name> <X> <Y> <Z>");
	}
}
