package panels.console.commands.basic;

import panels.console.Console;
import panels.console.commands.BasicCommand;
import misc.math.Vec3D;
import view.Cam;
import view.True3DCam;

public class AddCam extends BasicCommand
{
	@Override public void execute(Console console, String[] args)
	{
		if (args.length == 1)
		{
			Cam cam = new Cam(args[0]);
			console.getAnimator().addCam(cam, true); // soll gleich active gemacht werden
		}
		else if (args.length == 2)
		{
			if (args[0].equals("-3D"))
			{
				True3DCam tcam = new True3DCam(args[1], new Vec3D(0, 0, 0));
				console.getAnimator().addCam(tcam, true);
			}
			else
			{
				printUsage(console);
			}
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "addCam"; }

	private void printUsage(Console console)
	{
		console.endl();
		console.write(TAB + "Usage");
		console.write(TAB + getName() + " <Name>");
		console.write(TAB + getName() + " -3D <Name>");
		console.endl();
	}
}
