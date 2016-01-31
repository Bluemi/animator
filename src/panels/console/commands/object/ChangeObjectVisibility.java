package panels.console.commands.object;

import drawobjects.DrawObject;
import misc.Debug;
import panels.console.Console;
import panels.console.commands.ObjectCommand;

public class ChangeObjectVisibility extends ObjectCommand
{
	@Override public void execute(Console console, String[] args, DrawObject object)
	{
		Debug.warnIf(object == null, "ChangeObjectVisibility(): object == null");
		object.setVisible(!object.getVisible());
	}

	@Override public String getName() { return "changeVisible"; }
}
