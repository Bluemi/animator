package panels.console.commands;

import panels.console.Console;
import panels.console.Command;
import drawobjects.DrawObject;

public class ListObjects extends Command
{
	@Override public String getName() { return "listObjects"; }
	@Override public void execute(Console console, String[] args)
	{
		if (console.getAnimator().getDrawObjects().size() == 0)
		{
			console.write("No Objects found");
		}

		for (DrawObject drawObject : console.getAnimator().getDrawObjects())
		{
			console.write(drawObject.getName());
		}
	}
}
