
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
		String name = "";
		int interval = 2;

		if (args.length == 1)
		{
			if (args[0].equals("-n"))
			{
				name = "fountain";
			}
		}
		else if (args.length == 5)
		{
			name = args[0];
			position = new Vec3D(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3]));
			interval = Integer.parseInt(args[4]);
			name = args[0];
		}
		else
		{
			printUsage(console);
			return;
		}

		// Erstellen
		Fountain fountain = new Fountain(name, position, interval);
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
		console.write(TAB + getName() + " <Name> <X> <Y> <Z> <Interval>");
		console.write(TAB + getName() + " -n");
		console.endl();
	}
}
