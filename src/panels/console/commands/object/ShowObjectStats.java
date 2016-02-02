package panels.console.commands.object;

import drawobjects.DrawObject;
import panels.console.commands.ObjectCommand;
import panels.console.Console;

public class ShowObjectStats extends ObjectCommand
{
	@Override public void execute(Console console, String[] args, DrawObject object)
	{
		for (String l : object.getDescription())
		{
			console.write(l);
		}
	}
	@Override public String getName() { return "stats"; }
}
