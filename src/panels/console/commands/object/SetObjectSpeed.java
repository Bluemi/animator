package panels.console.commands.object;

import drawobjects.DrawObject;
import misc.math.Vec3D;
import panels.console.Console;
import panels.console.commands.ObjectCommand;

public class SetObjectSpeed extends ObjectCommand
{
	@Override public void execute(Console console, String[] args, DrawObject object)
	{
		if (args.length == 3)
		{
			Vec3D vec = new Vec3D(Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]));
			object.setSpeed(vec);
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "setSpeed"; }
	private void printUsage(Console console)
	{
		console.write("Usage");
		console.write("     " + getName() + " <X> <Y< <Z>");
	}
}
