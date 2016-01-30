package panels.console.commands.basic;

import panels.console.Console;
import panels.console.commands.BasicCommand;
import drawobjects.DrawObject;

public class ListObjects extends BasicCommand
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
