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
					console.endl();
					console.write(TAB + "Handle " + cam.getName() + " now");
					console.endl();
					return;
				}
			}
			console.endl();
			console.write("Cam \"" + args[0] + "\" not found");
			console.endl();
		}
		else
		{
			printUsage(console);
		}
	}

	private void printUsage(Console console)
	{
		console.endl();
		console.write("Usage");
		console.write(TAB + getName() + " <name>");
		console.endl();
	}

	@Override public String getName() { return "pc"; }
}
