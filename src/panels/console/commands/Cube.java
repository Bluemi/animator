package panels.console.commands;

import misc.math.Vec3D;
import misc.Debug;
import drawobjects.containers.DrawCube;
import panels.console.Console;
import panels.console.Command;

public class Cube extends Command
{
	@Override public void execute(Console console, String[] args)
	{
		String usage = getName() + " [x] [y] [z] [xSize] [ySize] [zSize]";
		if (args.length < 1)
		{
			console.write(usage);
			return;
		}

		if (args.length == 7)
		{
			Vec3D position = new Vec3D(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]));
			Vec3D size = new Vec3D(Float.parseFloat(args[4]), Float.parseFloat(args[5]), Float.parseFloat(args[6]));

			console.getAnimator().getDrawObjects().add(new DrawCube(args[0], position, size));

			console.write("[Cube] " + getName() + " : Position " + position + " | Size " + size);
		}
		else
		{
			console.write(usage);
		}
	}

	@Override public String getName() { return "cube"; }
}
