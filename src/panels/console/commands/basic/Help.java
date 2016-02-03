package panels.console.commands.basic;

import panels.console.commands.BasicCommand;
import panels.console.commands.GlobalCommand;
import panels.console.commands.ObjectCommand;
import panels.console.commands.CamCommand;
import panels.console.Console;

public class Help extends BasicCommand
{
	@Override public String getName() { return "help"; }
	@Override public void execute(Console console, String[] args)
	{
		console.endl();
		console.write("Basic-Commands:");
		for (BasicCommand bcommand : BasicCommand.getPossibleCommands())
		{
			console.write(TAB + bcommand.getName());
		}		
		console.endl();
		console.write("Global-Commands:");
		for (GlobalCommand gcommand : GlobalCommand.getPossibleCommands())
		{
			console.write(TAB + gcommand.getName());
		}
		if (args.length > 0)
		{
			if (args[0].equals("-a"))
			{
				console.endl();
				console.write("Object-Commands:");
				for (ObjectCommand ocommand : ObjectCommand.getPossibleCommands())
				{
					console.write(TAB + ocommand.getName());
				}
				console.endl();
				console.write("Cam-Commands:");
				for (CamCommand ccommand : CamCommand.getPossibleCommands())
				{
					console.write(TAB + ccommand.getName());
				}
			}
		}
		console.endl();
	}
}
