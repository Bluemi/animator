package panels.console.commands.cam;

import java.awt.Color;

import panels.console.Console;
import panels.console.commands.CamCommand;
import view.Cam;

public class SetColor extends CamCommand
{
	@Override public void execute(Console console, String[] args, Cam cam)
	{
		if (args.length == 1)
		{
			if (args[0].equals("-s"))
			{
				cam.setColor(Color.WHITE);
			}
			else
			{
				printUsage(console);
			}
		}
		else if (args.length == 3)
		{
			cam.setColor(new Color(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])));
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "color"; }

	private void printUsage(Console console)
	{
		console.endl();
		console.write(TAB + "Usage");
		console.write(TAB + getName() + " <R> <G> <B>");
	}
}
