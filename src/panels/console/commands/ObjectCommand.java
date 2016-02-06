package panels.console.commands;

import java.util.LinkedList;

import drawobjects.DrawObject;
import misc.Debug;
import panels.console.Command;
import panels.console.commands.object.*;
import panels.console.Console;

public abstract class ObjectCommand extends Command
{
	private static LinkedList<ObjectCommand> commands;

	static
	{
		// Hier werden alle commands angegeben
		commands = new LinkedList<ObjectCommand>();

		commands.add(new ExitObject());
		commands.add(new HelpObject());
		commands.add(new ChangeObjectVisibility());
		commands.add(new ChangePosition());
		commands.add(new SetObjectSpeed());
		commands.add(new ShowObjectStats());
		commands.add(new ObjectStart());
		commands.add(new ObjectStop());
	}

	public static ObjectCommand getCommandByText(String text)
	{
		for (ObjectCommand command : getPossibleCommands())
		{
			if (command.itsMe(text))
			{
				return command;
			}
		}
		// Wenn kein Command gefunden:
		return null;
	}

	public abstract void execute(Console console, String[] args, DrawObject handledObject); 

	public static LinkedList<ObjectCommand> getPossibleCommands() { return commands; }
}
