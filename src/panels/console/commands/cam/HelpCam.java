package panels.console.commands.cam;

import panels.console.Console;
import panels.console.commands.CamCommand;
import panels.console.commands.GlobalCommand;
import view.Cam;

public class HelpCam extends CamCommand
{
	@Override public void execute(Console console, String[] args, Cam cam)
	{
		console.endl();
		console.write("CamCommands:");
		for (CamCommand c : CamCommand.getPossibleCommands())
		{
			console.write(c.getName());
		}

		console.endl();
		console.write("Global-Commands:");
		for (GlobalCommand g : GlobalCommand.getPossibleCommands())
		{
			console.write(g.getName());
		}
		console.endl();
	}
	@Override public String getName() { return "help"; }
}
