package panels.console.commands.basic;

import panels.console.commands.BasicCommand;
import panels.console.Console;

public class Help extends BasicCommand
{
	@Override public String getName() { return "help"; }
	@Override public void execute(Console console, String[] args)
	{
		for (BasicCommand command : BasicCommand.getPossibleCommands())
		{
			console.write(command.getName());
		}		
	}
}
