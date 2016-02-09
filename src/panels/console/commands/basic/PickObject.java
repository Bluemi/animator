package panels.console.commands.basic;

import java.util.LinkedList;

import drawobjects.DrawObject;
import panels.console.commands.BasicCommand;
import panels.console.Console;

public class PickObject extends BasicCommand
{
	@Override public void execute(Console console, String[] args)
	{
		if (args.length == 1)
		{
			LinkedList<DrawObject> objects = console.getAnimator().getDrawObjects();
			for (DrawObject object : objects)
			{
				if (object.getName().equals(args[0]))
				{
					console.handleObject(object);
					console.write(TAB + "handling \"" + object.getName() + "\" now");
					return;
				}
			}
			console.write("No object found with name \"" + args[0] + "\"");
		}
		else
		{
			printUsage(console);
		}
	}

	private void printUsage(Console console)
	{
		console.write(TAB + "usage");
		console.write(TAB + getName() + " <Name>");
		// console.write("       pickobject <id>"); // noch nicht umgesetzt
	}

	@Override public String getName() { return "pd"; }
}
