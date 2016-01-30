package panels.console.commands.global;

import panels.console.commands.GlobalCommand;
import panels.console.Console;

public class Reset extends GlobalCommand
{
	@Override public String getName() { return "reset"; }
	@Override public void execute(Console console, String[] args) { console.reset(); }
}
