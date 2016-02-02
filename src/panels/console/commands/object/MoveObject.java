package panels.console.commands.object;

import panels.console.commands.ObjectCommand;
import panels.console.Console;
import drawobjects.DrawObject;

public class MoveObject extends ObjectCommand
{
	public void execute(Console console, String[] args, DrawObject handledObject)
	{
		console.endl();
		console.write(TAB + "Muss man noch programmieren ...");
		console.endl();
	}

	@Override public String getName() { return "move"; }
}
