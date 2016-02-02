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
		Vec3D position = new Vec3D();
		Vec3D size = new Vec3D();
		String name = "";

		if (args.length == 1)
		{
			if (args[0].equals("-n"))
			{
				size = new Vec3D(1, 1, 1);
				name = "cube";
			}
		}
		else if (args.length == 7)
		{
			position = new Vec3D(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]));
			size = new Vec3D(Float.parseFloat(args[4]), Float.parseFloat(args[5]), Float.parseFloat(args[6]));
			name = args[0];
		}
		else
		{
			printUsage(console);
			return;
		}

		// Erstellen
		DrawCube cube = new DrawCube(name, position, size);
		console.getAnimator().getDrawObjects().add(cube);
		console.endl();
		for (String s : cube.getDescription())
		{
			console.write(s);
		}
		console.endl();
	}
	@Override public String getName() { return "cube"; }
	private void printUsage(Console console)
	{
		console.endl();
		console.write("Usage");
		console.write(TAB + getName() + " <Name> <X> <Y> <Z> <XSize> <YSize> <ZSize>");
		console.write(TAB + getName() + " -n");
		console.endl();
	}
}
