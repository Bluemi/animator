package panels.console.commands;

import java.awt.Font;

import panels.console.Console;
import panels.console.Command;

public class SetFont extends Command
{
	@Override public String getName() { return "setfont"; }
	@Override public void execute(Console console, String[] args)
	{
		String usage = "usage: setfont [FontType]";

		for (int i = 0; i < args.length; i++)
		{
		}
		if (args.length != 1)
		{
			console.write(usage);
		}
		else
		{
			Console.FONT = new Font(args[0], Font.BOLD, Console.TEXT_HEIGHT-2);
		}
	}
}
