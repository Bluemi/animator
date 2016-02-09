package panels.console.commands.object;

import java.awt.Color;

import drawobjects.DrawObject;
import panels.console.Console;
import panels.console.commands.ObjectCommand;

public class SetColor extends ObjectCommand
{
	@Override public void execute(Console console, String[] args, DrawObject object)
	{
		if (args.length == 1)
		{
			if (args[0].equals("-s"))
			{
				object.setColor(Color.WHITE);
			}
			else
			{
				printUsage(console);
			}
		}
		else if (args.length == 3)
		{
			object.setColor(new Color(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])));
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
		console.endl();
	}
}
