package panels.console.commands;

import panels.console.Command;
import panels.console.Console;

public class Exit extends Command
{
	@Override public String getName() { return "exit"; }
	@Override public void execute(Console console, String[] args)
	{
		System.exit(0);
	}
}
