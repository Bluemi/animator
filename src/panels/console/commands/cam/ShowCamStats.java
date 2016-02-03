package panels.console.commands.cam;

import panels.console.Console;
import panels.console.commands.CamCommand;
import view.Cam;

public class ShowCamStats extends CamCommand
{
	@Override public void execute(Console console, String[] args, Cam cam)
	{
		console.endl();
		for (String s : cam.getDescription())
		{
			console.write(TAB + s);
		}
		console.endl();
	}
	@Override public String getName() { return "stats"; }
}
