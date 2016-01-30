package panels.console.commands.basic;

import panels.console.Console;
import panels.console.commands.BasicCommand;
import view.Cam;

public class ListCams extends BasicCommand
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
