package panels.console.commands;

import panels.console.Console;
import panels.console.Command;
import view.Cam;

public class ListCams extends Command
{
	@Override public String getName() { return "listCams"; }
	@Override public void execute(Console console, String[] args)
	{
		for (Cam cam : console.getAnimator().getCams())
		{
			console.write(cam.getName());
		}
	}
}
