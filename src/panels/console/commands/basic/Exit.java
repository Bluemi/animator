package panels.console.commands.basic;

import panels.console.commands.BasicCommand;
import panels.console.Console;

public class Exit extends BasicCommand
{
	@Override public String getName() { return "exit"; }
	@Override public void execute(Console console, String[] args)
	{
		System.exit(0);
	}
}
