package panels.console.commands.basic;

import panels.console.commands.BasicCommand;
import panels.console.Console;
import view.Cam;

public class PickCam extends BasicCommand
{
	@Override public void execute(Console console, String[] args)
	{
		if (args.length == 1)
		{
			for (Cam cam : console.getAnimator().getCams())
			{
				if (cam.getName().equals(args[0]))
				{
					console.setHandledCam(cam);
					console.write("Handle " + cam.getName() + " now");
					return;
				}
			}
			console.write("Cam \"" + args[0] + "\" not found");
		}
		else
		{
			printUsage(console);
		}
	}

	private void printUsage(Console console)
	{
		console.write("Usage");
		console.write(TAB + getName() + " <name>");
	}

	@Override public String getName() { return "pickCam"; }
}
