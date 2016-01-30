package panels.console.commands.basic;

import panels.console.commands.BasicCommand;
import panels.console.Console;

public class Reset extends BasicCommand
{
	@Override public String getName() { return "reset"; }
	@Override public void execute(Console console, String[] args) { console.reset(); }
}
