package panels.console.commands;

import java.util.LinkedList;

import drawobjects.DrawObject;
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

	private static LinkedList<ObjectCommand> getPossibleCommands() { return commands; }
}
