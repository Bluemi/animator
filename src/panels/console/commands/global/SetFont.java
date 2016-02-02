package panels.console.commands.global;

import java.awt.Font;

import panels.console.Console;
import panels.console.commands.GlobalCommand;

public class SetFont extends GlobalCommand
{
	@Override public String getName() { return "setfont"; }
	@Override public void execute(Console console, String[] args)
	{
		if (args.length != 1)
		{
			printUsage(console);
		}
		else
		{
			Console.FONT = new Font(args[0], Font.PLAIN, Console.TEXT_HEIGHT);
		}
	}
	private void printUsage(Console console)
	{
		console.endl();
		console.write("Usage");
		console.write(TAB + getName() + " <FontType>");
		console.endl();
	}
}
