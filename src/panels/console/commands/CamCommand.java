package panels.console.commands;

import java.util.LinkedList;

import panels.console.Command;
import panels.console.commands.cam.*;
import panels.console.Console;
import view.Cam;

public abstract class CamCommand extends Command
{
	private static LinkedList<CamCommand> commands;

	static
	{
		// Hier werden alle commands angegeben
		commands = new LinkedList<CamCommand>();

		commands.add(new MoveCam());
		commands.add(new HelpCam());
		commands.add(new ExitCam());
	}

	public static CamCommand getCommandByText(String text)
	{
		for (CamCommand command : getPossibleCommands())
		{
			if (command.itsMe(text))
			{
				return command;
			}
		}
		// Wenn kein Command gefunden:
		return null;
	}

	public abstract void execute(Console console, String[] args, Cam handledCam); 

	public static LinkedList<CamCommand> getPossibleCommands() { return commands; }
}
