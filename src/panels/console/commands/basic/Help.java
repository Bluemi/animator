package panels.console.commands.basic;

import panels.console.commands.BasicCommand;
import panels.console.commands.GlobalCommand;
import panels.console.Console;

public class Help extends BasicCommand
{
	@Override public String getName() { return "help"; }
	@Override public void execute(Console console, String[] args)
	{
		console.endl();
		console.write("Basic-Commands:");
		for (BasicCommand command : BasicCommand.getPossibleCommands())
		{
			console.write(TAB + command.getName());
		}		
		console.endl();
		console.write("Global-Commands:");
		for (GlobalCommand gcommand : GlobalCommand.getPossibleCommands())
		{
			console.write(TAB + gcommand.getName());
		}
		console.endl();
	}
}
