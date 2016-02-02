package panels.console.commands.basic;

import misc.math.Vec3D;
import misc.Debug;
import drawobjects.containers.DrawCube;
import panels.console.Console;
import panels.console.commands.BasicCommand;

public class Cube extends BasicCommand
{
	@Override public void execute(Console console, String[] args)
	{
		if (args.length < 1)
		{
			printUsage(console);
			return;
		}

		if (args.length == 7)
		{
			Vec3D position = new Vec3D(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]));
			Vec3D size = new Vec3D(Float.parseFloat(args[4]), Float.parseFloat(args[5]), Float.parseFloat(args[6]));

			DrawCube cube = new DrawCube(args[0], position, size);
			console.getAnimator().getDrawObjects().add(cube);
			console.endl();
			for (String s : cube.getDescription())
			{
				console.write(s);
			}
			console.endl();
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "cube"; }
	private void printUsage(Console console)
	{
		console.endl();
		console.write("Usage");
		console.write(TAB + getName() + " <Name> <X> <Y> <Z> <XSize> <ySize> <ZSize>");
		console.endl();
	}
}
