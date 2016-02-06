package panels.console.commands.object;

import panels.console.Console;
import panels.console.commands.ObjectCommand;
import drawobjects.DrawObject;

public class ObjectStop extends ObjectCommand
{
	@Override public void execute(Console console, String[] args, DrawObject object)
	{
		object.stop();
	}
	@Override public String getName() { return "stop"; }
}
