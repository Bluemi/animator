package panels.console.commands;

import java.util.LinkedList;

import panels.console.Command;
import panels.console.commands.basic.*;
import panels.console.Console;

public abstract class BasicCommand extends Command
{
	private static LinkedList<BasicCommand> commands;

	static
	{
		// Hier werden alle commands angegeben
		commands = new LinkedList<BasicCommand>();

		commands.add(new Cube());
		commands.add(new CreateDrawPoint());
		commands.add(new Exit());
		commands.add(new HandleCam());
		commands.add(new Help());
		commands.add(new PickObject());
		commands.add(new PickCam());
	}

	public static BasicCommand getCommandByText(String text)
	{
		for (BasicCommand command : getPossibleCommands())
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

	public static LinkedList<BasicCommand> getPossibleCommands() { return commands; }
}
