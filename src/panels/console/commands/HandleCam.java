package panels.console.commands;

import panels.console.Console;
import panels.console.Command;

public class HandleCam extends Command
{
	@Override public String getName() { return "handleCam"; }
	@Override public void execute(Console console, String[] args)
	{
		String usage = "usage: handleCam [CamName]";

		if (args.length == 1)
		{
			console.setHandledCam(console.getAnimator().getCam(args[0]));
		}
		else
		{
			console.write(usage);
		}
	}
}
