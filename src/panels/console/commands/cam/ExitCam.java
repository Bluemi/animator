package panels.console.commands.cam;

import panels.console.Console;
import panels.console.commands.CamCommand;
import view.Cam;

public class ExitCam extends CamCommand
{
	@Override public void execute(Console console, String[] args, Cam cam)
	{
		console.setHandledCam(null);
		console.endl();
		console.write(TAB + "Doesn't handle \"" + cam.getName() + "\" anymore");
		console.endl();
	}
	@Override public String getName() { return "exit"; }
}
