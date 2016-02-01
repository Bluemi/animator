package panels.console.commands;

import java.util.LinkedList;

import panels.console.Console;
import panels.console.Command;
import panels.console.commands.global.*;

public abstract class GlobalCommand extends Command
{
	private static LinkedList<GlobalCommand> commands;

	static
	{
		commands = new LinkedList<GlobalCommand>();
		commands.add(new ListObjects());
		commands.add(new ListCams());
		commands.add(new Reset());
		commands.add(new SetFont());
		commands.add(new SetBackgroundColor());
	}

	public static GlobalCommand getCommandByText(String text)
	{
		for (GlobalCommand command : getPossibleCommands())
		{
			if (command.itsMe(text))
			{
				return command;
			}
		}
		// Wenn kein Command gefunden:
		return null;
	}

	public abstract void execute(Console console, String[] args);

	public static LinkedList<GlobalCommand> getPossibleCommands() { return commands; }
}
