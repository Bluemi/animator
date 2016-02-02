package panels.console.commands.global;

import panels.console.Console;
import panels.console.commands.GlobalCommand;
import drawobjects.DrawObject;

public class ListObjects extends GlobalCommand
{
	@Override public String getName() { return "listObjects"; }
	@Override public void execute(Console console, String[] args)
	{
		console.endl();
		console.write(getName());
		if (console.getAnimator().getDrawObjects().size() == 0)
		{
			console.write(TAB + "No Objects found");
		}

		for (int i = 0; i < console.getAnimator().getDrawObjects().size(); i++)
		{
			console.write(TAB + i + " - " + console.getAnimator().getDrawObjects().get(i).getName());
		}
		console.endl();
	}
}
