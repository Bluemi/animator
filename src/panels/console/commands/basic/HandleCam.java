package panels.console.commands.basic;

import panels.console.Console;
import panels.console.commands.BasicCommand;

public class HandleCam extends BasicCommand
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
