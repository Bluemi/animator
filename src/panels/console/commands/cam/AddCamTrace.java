package panels.console.commands.cam;

import drawobjects.DrawObject;
import drawobjects.containers.DrawTrace;
import panels.console.Console;
import panels.console.commands.CamCommand;
import view.Cam;

public class AddCamTrace extends CamCommand
{
	public static final int INTERVAL = 1;

	@Override public void execute(Console console, String[] args, Cam cam)
	{
		if (args.length == 0)
		{
			DrawObject object = console.getAnimator().getObject(getTraceName(cam)); // Trace der Cam holen, falls schon existiert
			if (object == null) // Falls Trace für diese Cam noch nicht existiert
			{
				object = new DrawTrace(getTraceName(cam), INTERVAL, cam); // diese erstellen
				console.getAnimator().getDrawObjects().add(object); // und zu den Objecten hinzufügen
			}
			else // Falls die Trace für diese Cam schon existiert hatte
			{
				object.change();
			}
		}
		else
		{
			printUsage(console);
		}
	}
	@Override public String getName() { return "trace"; }

	private String getTraceName(Cam cam)
	{
		return cam.getName() + "[trace]";
	}

	private void printUsage(Console console)
	{
		console.endl();
		console.write("Usage");
		console.write(TAB + getName());
		console.endl();
	}
}
