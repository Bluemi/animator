
package panels.console.commands.basic;

import misc.math.Vec3D;
import misc.Debug;
import drawobjects.containers.Fountain;
import panels.console.Console;
import panels.console.commands.BasicCommand;

public class CreateFountain extends BasicCommand
{
	@Override public void execute(Console console, String[] args)
	{
		Vec3D position = new Vec3D();
		Vec3D size = new Vec3D();
		String name = "";
		int interval = 2;

		if (args.length == 1)
		{
			if (args[0].equals("-n"))
			{
				size = new Vec3D(1, 1, 1);
				name = "fountain";
			}
		}
		else if (args.length == 8)
		{
			position = new Vec3D(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]));
			size = new Vec3D(Float.parseFloat(args[4]), Float.parseFloat(args[5]), Float.parseFloat(args[6]));
			interval = Integer.parseInt(args[7]);
			name = args[0];
		}
		else
		{
			printUsage(console);
			return;
		}

		// Erstellen
		Fountain fountain = new Fountain(name, position, size, interval);
		console.getAnimator().getDrawObjects().add(fountain);
		console.endl();
		for (String s : fountain.getDescription())
		{
			console.write(s);
		}
		console.endl();
	}
	@Override public String getName() { return "fountain"; }
	private void printUsage(Console console)
	{
		console.endl();
		console.write("Usage");
		console.write(TAB + getName() + " <Name> <X> <Y> <Z> <XSize> <YSize> <ZSize> <Interval>");
		console.write(TAB + getName() + " -n");
		console.endl();
	}
}
