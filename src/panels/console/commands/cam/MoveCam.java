package panels.console.commands.cam;

import panels.console.commands.CamCommand;
import panels.console.Console;
import view.Cam;
import misc.math.Vec3D;

public class MoveCam extends CamCommand
{
	@Override public void execute(Console console, String[] args, Cam cam)
	{
		if (args.length == 3)
		{
			Vec3D vec = new Vec3D(Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]));
			cam.changePosition(vec);
			console.endl();
			console.write(TAB + "\"" + cam.getName() + "\" changed Position " + vec);
			console.endl();
		}
		else
		{
			printUsage(console);
		}
	}

	@Override public String getName() { return "move"; }
	private void printUsage(Console console)
	{
		console.endl();
		console.write(TAB + "Usage");
		console.write(TAB + getName() + " <X> <Y> <Z>");
		console.endl();
	}
}
