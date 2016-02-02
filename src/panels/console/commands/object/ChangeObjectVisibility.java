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
		if (args.length == 1)
		{
			if (args[0].equals("-f"))
			{
				object.setVisible(false);
			}
			else if (args[0].equals("-t"))
			{
				object.setVisible(true);
			}
			else
			{
				printUsage(console);
			}
		}
		else if (args.length == 0)
		{
			object.setVisible(!object.getVisible());
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "visible"; }
	private void printUsage(Console console)
	{
		console.endl();
		console.write("Usage");
		console.write(TAB + getName() + " -f");
		console.write(TAB + getName() + " -t");
		console.endl();
	}
}
