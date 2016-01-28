package panels.console.commands;

import panels.console.Command;
import panels.console.Console;
import panels.console.Command;

public class Help extends Command
{
	@Override public String getName() { return "help"; }
	@Override public void execute(Console console, String[] args)
	{
		for (Command command : Command.getCommands())
		{
			console.write(command.getName());
		}		
	}
}
