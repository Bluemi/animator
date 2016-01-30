package panels.console.commands.object;

import drawobjects.DrawObject;
import panels.console.commands.ObjectCommand;
import panels.console.commands.GlobalCommand;
import panels.console.Console;

public class HelpObject extends ObjectCommand
{
	@Override public void execute(Console console, String[] args, DrawObject object)
	{
		int c = 0;
		for (ObjectCommand command : ObjectCommand.getPossibleCommands())
		{
			console.write("     " + c + " - " + command.getName());
			c++;
		}
		for (GlobalCommand gcommand : GlobalCommand.getPossibleCommands())
		{
			console.write("     " + c + " - " + gcommand.getName());
			c++;
		}
	}

	@Override public String getName() { return "help"; }
}
