package panels.console.commands.cam;

import panels.console.commands.CamCommand;
import panels.console.Console;
import view.Cam;

public class MoveCam extends CamCommand
{
	@Override public void execute(Console console, String[] args, Cam handledCam)
	{
	}

	@Override public String getName() { return "move"; }
}
