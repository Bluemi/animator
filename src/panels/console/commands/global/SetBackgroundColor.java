package panels.console.commands.global;

import java.awt.Color;

import core.Screen;
import panels.console.commands.GlobalCommand;
import panels.console.Console;

public class SetBackgroundColor extends GlobalCommand
{
	@Override public void execute(Console console, String[] args)
	{
		if (args.length == 3)
		{
			Screen.setBackgroundColor(new Color(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])));
			console.endl();
			console.write(TAB + "YEAH - New BackgroundColor :D");
			console.endl();
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "setBackgroundColor"; }
	private void printUsage(Console console)
	{
		console.endl();
		console.write("Usage");
		console.write(TAB + getName() + " <R> <G> <B>");
		console.endl();
	}
}
