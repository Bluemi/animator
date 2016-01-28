package panels.console.commands;

import panels.console.Command;
import panels.console.Console;

public class Reset extends Command
{
	@Override public String getName() { return "reset"; }
	@Override public void execute(Console console, String[] args) { console.reset(); }
}
