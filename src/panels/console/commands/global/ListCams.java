package panels.console.commands.global;

import panels.console.Console;
import panels.console.commands.GlobalCommand;
import view.Cam;

public class ListCams extends GlobalCommand
{
	@Override public String getName() { return "listCams"; }
	@Override public void execute(Console console, String[] args)
	{
		console.endl();
		console.write("ListCams:");
		for (Cam cam : console.getAnimator().getCams())
		{
			console.write(TAB + cam.getName());
		}
		console.endl();
	}
}
