package panels.console.commands.basic;

import panels.console.Console;
import panels.console.commands.BasicCommand;
import misc.math.Vec3D;
import drawobjects.drawobjects.DrawCloud;

public class CreateCloud extends BasicCommand
{
	@Override public void execute(Console console, String[] args)
	{
		String name = "";
		Vec3D position = new Vec3D();
		if (args.length == 4)
		{
			name = args[0];
			position = new Vec3D(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]));
			console.getAnimator().getDrawObjects().add(new DrawCloud(name, position));
			console.endl();
			console.write(TAB + "created new Cloud");
			console.endl();
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "cloud"; }
	public void printUsage(Console console)
	{
		console.endl();
		console.write(TAB + "Usage");
		console.write(TAB + getName() + " <Name> <X> <Y> <Z>");
		console.endl();
	}
}
