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
		console.endl();
		console.write("Object-Commands:");
		for (ObjectCommand command : ObjectCommand.getPossibleCommands())
		{
			console.write(TAB + c + " - " + command.getName());
			c++;
		}
		console.endl();
		console.write("Global-Commands:");
		for (GlobalCommand gcommand : GlobalCommand.getPossibleCommands())
		{
			console.write(TAB + c + " - " + gcommand.getName());
			c++;
		}
		console.endl();
	}

	@Override public String getName() { return "help"; }
}
