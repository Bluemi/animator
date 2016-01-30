package panels.console.commands.object;

import drawobjects.DrawObject;
import panels.console.commands.ObjectCommand;
import panels.console.Console;

public class ExitObject extends ObjectCommand
{
	@Override public void execute(Console console, String[] args, DrawObject object)
	{
		console.handleObject(null);
		console.write("Doesn't handle \"" + object.getName() + "\" anymore");
	}

	@Override public String getName() { return "exit"; }
}
