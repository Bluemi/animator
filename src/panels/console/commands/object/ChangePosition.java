package panels.console.commands.object;

import drawobjects.DrawObject;
import misc.math.Vec3D;
import panels.console.Console;
import panels.console.commands.ObjectCommand;

public class ChangePosition extends ObjectCommand
{
	@Override public void execute(Console console, String[] args, DrawObject object)
	{
		if (args.length == 3)
		{
			Vec3D diff = new Vec3D(Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]));
			object.changePosition(diff);
			console.endl();
			console.write(TAB + "Object \"" + object.getName() + "\" moved " + diff);
			console.endl();
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "move";}
	private void printUsage(Console console)
	{
		console.endl();
		console.write("Usage:");
		console.write(TAB + getName() + " <X> <Y> <Z>");
		console.endl();
	}
}
