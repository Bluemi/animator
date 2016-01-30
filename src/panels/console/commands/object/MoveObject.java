package panels.console.commands.object;

import panels.console.commands.ObjectCommand;
import panels.console.Console;
import drawobjects.DrawObject;

public class MoveObject extends ObjectCommand
{
	public void execute(Console console, String[] args, DrawObject handledObject)
	{
	}

	@Override public String getName() { return "move"; }
}
